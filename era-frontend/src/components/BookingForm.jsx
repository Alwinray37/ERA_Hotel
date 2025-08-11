import React, {useState} from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

export default function BookingForm() {
    const location = useLocation();
    const navigate = useNavigate();

    const room = location.state?.room; // Get the room data passed from ViewRooms
    const searchStart = location.state?.searchStart; // Get the search start date
    const searchEnd = location.state?.searchEnd; // Get the search end date

    // function to determine # of days between searchStart and searchEnd
    const calculateDays = (start, end) => {
        if (!start || !end) return 0;
        const startDate = new Date(start);
        const endDate = new Date(end);
        const timeDiff = endDate - startDate;
        return (Math.ceil(timeDiff / (1000 * 3600 * 24))); // convert ms to days
    };
    const days = calculateDays(searchStart, searchEnd);

    const reservationDetails = {
        room: room,
        name: '',
        email: '',
        phone: '',
        startDate: searchStart,
        endDate: searchEnd,
        totalCost: room.price * days
    }

    return (
        <div className='main-content'>
            <h2>Booking For Room {room.roomNumber}</h2>
            <p><strong>Price:</strong> ${room.price} per night</p>
            <p><strong>Number of Nights:</strong> {days}</p>
            <p><strong>Total Cost:</strong> ${(room.price * days).toFixed(2)}</p>

            {/* add form for user to input their details */}
            <form className='guest-booking-form' onSubmit={(e) => {
                e.preventDefault();
                // handle form submission logic here
                console.log('Booking confirmed for:', room.roomNumber);
                navigate('/'); // redirect to home page after booking
            }}>
                {/* create form */}
                <h3>Guest Information</h3>
                <div>
                    <input type="text" required placeholder='Enter your name'/>
                </div>
                <div>
                    <input type="email" required placeholder='Enter your email'/>
                </div>
                <div>
                    <input type="tel" required placeholder='Enter your phone number'/>
                </div>
                <button type="submit" >Confirm Booking</button>
            </form> 
        </div>
    );
}; 

// Guest inputs their name, email and phone number
// when submitted, create a guest object in database 