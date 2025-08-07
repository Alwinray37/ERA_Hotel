import React, { useState } from 'react';
import Navbar from './components/navbar/Navbar';
import Hero from './components/hero/Hero';
import BookingSearch from './components/search/BookingSearch';
import Footer from './components/footer/footer';
import './App.css';
import ViewRooms from './components/viewRooms';
import dummyRooms from './assets/dummyRoomData.json';

function App() {
	const [filteredRooms, setFilteredRooms] = useState([]); // Start with no rooms
	const [searchMade, setSearchMade] = useState(false);
 

  	const handleSearch = (dateRange) => {
    // search date range selected by user
    const [searchStart, searchEnd] = dateRange; 
    // If no dates selected, return early
    if (!searchStart || !searchEnd) return;

    // filter logic to find available rooms
    const availableRooms = dummyRooms.filter(room => {
		// if no reservations, reservations is an empty array, then room is available
		if (!room.reservations || room.reservations.length === 0) return true;

		// check for the overlap with any reservation start/end. 
		return room.reservations.every(reservation => {
		const resStart = new Date(reservation.start);
		const resEnd = new Date(reservation.end);
		const noOverlap = (searchEnd < resStart || searchStart > resEnd);
		console.log(
			'Room number', room.roomNumber,
			'\nsearch:', searchStart, searchEnd,
			'\nreservation:', resStart, resEnd,
			'\nNo Overlap:', noOverlap
		); // debug log
		// if no overlap, search ends before reservation starts or search starts after reservation ends
		return noOverlap;
		});
    });

    setFilteredRooms(availableRooms);
    setSearchMade(true);
	console.log('Available Rooms:', availableRooms);
  };

  return (
    <>
      <Navbar />
      <Hero>
        <BookingSearch onSearch={handleSearch} />
      </Hero>

      {searchMade && <ViewRooms rooms={filteredRooms} />}
      
      <Footer />
    </>
  );
}

export default App;
