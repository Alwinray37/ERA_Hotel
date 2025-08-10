import React, {useState} from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

export default function BookingForm() {
    const location = useLocation();
    const navigate = useNavigate();

    const room = location.state?.room; // Get the room data passed from ViewRooms
    // If no room data is passed, show a message or redirect
    if (!room) {
        return (
            <div>
                <h2>Error</h2>
                <p>No room data available for booking.</p>
                <button onClick={() => navigate('/')}>Go Back</button>
            </div>
        );
    }



    return (
        <div className='main-content'>
            <h2>Booking Form</h2>
            {/* Booking form fields go here */}
        </div>
    );
}; 