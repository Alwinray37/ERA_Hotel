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
    const filterRooms = (sStart, sEnd) => {
        const filteredRooms = rooms.filter(room => {
            if (room.roomReservations.length === 0) {
                return true; // room is available if it has no reservations
            }

            // filter logic for rooms with reservations
            return room.roomReservations.every(reservation => {
                const resStart = new Date(reservation.startDate);
                const resEnd = new Date(reservation.endDate);

                // debug log
                console.log(
                    'Comparing: ' + room.roomNumber,
                    '\nreservation dates: ', resStart, ' to ', resEnd,
                    '\nsearch dates: ', sStart, ' to ', sEnd
                );
                return sEnd < resStart || sStart > resEnd;
            });
        });
        setAvailableRooms(filteredRooms);
        console.log('Filtered rooms:', filteredRooms);
    }
    return (
        <div>
            <Navbar />
            {location.pathname === '/' && ( <BookingSearch handleSearch={handleSearch} /> )}

            <Routes>
                <Route path="/" element={<ViewRooms availableRooms={availableRooms} sStart={searchStart} sEnd={searchEnd} />} />
                <Route path="/book/:roomId" element={<BookingForm />} />
                <Route path="/about" element={<AboutUs />} />
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