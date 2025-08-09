import React, { use, useEffect, useState } from 'react';
import Navbar from './components/navbar/Navbar';
import BookingSearch from './components/search/BookingSearch';
import Footer from './components/footer/footer';
import './App.css';
import ViewRooms from './components/viewRooms';
import { listRooms } from './service/RoomService';

function App() {
    const [rooms, setRooms] = useState([]); // all rooms fetched from the server
    const [availableRooms, setAvailableRooms] = useState([]);
    const [searchStart, setSearchStart] = useState(null);
    const [searchEnd, setSearchEnd] = useState(null);

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


    const handleSearch =  (dateRange) => {
        const [start, end] = dateRange;
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

            <BookingSearch handleSearch={handleSearch} />
            <ViewRooms availableRooms={availableRooms} />

            <Footer />
        </div>
    );
}

export default App;