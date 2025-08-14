import React, { use, useEffect, useState } from 'react';
import Navbar from './components/Navbar';
import BookingSearch from './components/search/BookingSearch';
import Footer from './components/Footer';
import './App.css';
import ViewRooms from './components/viewRooms';
import { listRooms } from './service/RoomService';
import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import BookingForm from './components/BookingForm';
import AboutUs from './components/AboutUs';
import ViewReservations from './components/ViewReservations';
import Confirmation from './components/Confirmation';
import { getReservationById } from './service/ReservationService';
import ViewGuestRes from './components/ViewGuestReservation';
import AdminLogin from './components/AdminLogin';
// Added for admin dashboard functionality
import AdminDashboard from './components/AdminDashboard';

function AppContent() {
    const [rooms, setRooms] = useState([]); // all rooms fetched from the server
    const [availableRooms, setAvailableRooms] = useState([]);
    const [searchStart, setSearchStart] = useState(null); // start date for search
    const [searchEnd, setSearchEnd] = useState(null); // end date for

    const location = useLocation();

    // retreive all rooms from the server when the component mounts
    useEffect(() => {
        listRooms()
            .then(data => {
                setRooms(data);
                console.log('Fetched rooms:', data);
            })
            .catch(error => {
                console.error('Error fetching rooms:', error);
            });
    }, []);

    // handle search function that will be passed to BookingSearch component
    // takes dateRange as an argument from BookingSearch
    // and sets the searchStart and searchEnd state variables
    const handleSearch =  (dateRange) => {
        const [start, end] = dateRange;

        // passing these values to ViewRooms component
        setSearchStart(start);
        setSearchEnd(end);

        // debug log
        console.log(
            "Handle search:",
            "\nSearchStart:", start,
            "\nSearchEnd:", end
        )

        // filter rooms based on search criteria
        filterRooms(start, end);
    };

    // filter function to find available rooms based on search criteria
    // is called in handleSearch ^
    const filterRooms = async (sStart, sEnd) => {
        if (!sStart || !sEnd) {
            setAvailableRooms([]);
            return;
        }

        const searchStart = new Date(sStart);
        const searchEnd = new Date(sEnd);

        const filteredRooms = [];

        for (const room of rooms) {
            // If no reservations, room is available
            if (!room.roomReservations || room.roomReservations.length === 0) {
                filteredRooms.push(room);
                continue;
            }

            // Fetch all reservation details for this room in parallel
            const reservations = await Promise.all(
                room.roomReservations.map(resId => getReservationById(resId))
            );

            // Check for overlap with any reservation
            const hasOverlap = reservations.some(reservation => {
                const resStart = new Date(reservation.startDate);
                const resEnd = new Date(reservation.endDate);

                // Overlap logic: (searchStart <= resEnd) && (searchEnd >= resStart)
                return (searchStart <= resEnd) && (searchEnd >= resStart);
            });

            // Room is available if there is NO overlap
            if (!hasOverlap) {
                filteredRooms.push(room);
            }
        }

        setAvailableRooms(filteredRooms);
        console.log('Filtered rooms:', filteredRooms);
    };

    return (
        <div className='app'>
            <Navbar />

            <Routes>
                <Route path="/" element={
                    <div className='content'>
                        <BookingSearch handleSearch={handleSearch} />
                        <ViewRooms availableRooms={availableRooms} sStart={searchStart} sEnd={searchEnd} />
                    </div>
                    } />
                <Route path="/book/:roomId" element={<BookingForm />} />
                <Route path="/about" element={<AboutUs />} />
                <Route path="/view-reservation" element={<ViewReservations />} />
                <Route path="/confirmation" element={<Confirmation />} />
                <Route path='/view-guest-reservation' element={<ViewGuestRes />} />
                <Route path="/admin-login" element={<AdminLogin />} />
                {/* Admin dashboard route */}
                <Route path="/admin-dashboard" element={<AdminDashboard />} />
            </Routes>

            <Footer />
        </div>
    );
}

// Main App component that wraps AppContent with Router
function App(){
    return (

        <Router>
            <AppContent />
        </Router>

    )
}
export default App;