import React, { useState } from 'react';
import Navbar from './components/navbar/Navbar';
import Hero from './components/hero/Hero';
import BookingSearch from './components/search/BookingSearch';
import Footer from './components/footer/footer';
import './App.css';
import ViewRooms from './components/viewRooms';

function App() {
    const [searchMade, setSearchMade] = useState(false); // this determines if the search button has been clicked with valid arguments (search dates)
    const [searchStart, searchEnd] = useState([null, null]);

    return (
        <>
            <Navbar />
            <Hero>
                <BookingSearch setSearchMade={setSearchMade} />
            </Hero>
            {searchMade && (
            <ViewRooms searchStart={new Date(searchStart)} searchEnd={new Date(searchEnd)} />
            )}

            {/* routing for when user clicks "book now" on a room */}

            <Footer />
        </>
    );
}

export default App;