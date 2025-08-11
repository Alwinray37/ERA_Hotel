import React, {useState} from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { getGuestByEmail } from '../service/GuestService';

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

    const [formData, setFormData] = useState({
        name: '',
        email: '',
        phone: ''
    });

    // handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();

        // check if email is already in database
        getGuestByEmail(formData.email)
            .then(guest => {
                if (guest) {
                    console.log('Guest already exists:', guest);
                    // proceed with booking using existing guest data
                } else {
                    // if not, create a new guest object in the database
                }
            })
            .catch(error => {
                console.error('Error fetching guest:', error);
            });
    };


    // handle changes in form inputs
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    return (
        <div className='main-content'>
            <h2>Booking For Room {room.roomNumber}</h2>
            <p><strong>Price:</strong> ${room.price} per night</p>
            <p><strong>Number of Nights:</strong> {days}</p>
            <p><strong>Total Cost:</strong> ${(room.price * days).toFixed(2)}</p>

            {/* Form for user to input their details */}
            <form className='guest-booking-form' onSubmit={handleSubmit}>
                <h3>Guest Information</h3>
                <div>
                    <input type="text" name="name" value={formData.name} onChange={handleChange} required placeholder='Enter your name'/>
                </div>
                <div>
                    <input type="email" name="email" value={formData.email} onChange={handleChange} required placeholder='Enter your email'/>
                </div>
                <div>
                    <input type="tel" name="phone" value={formData.phone} onChange={handleChange} required placeholder='Enter your phone number'/>
                </div>
                <button type="submit" >Confirm Booking</button>
            </form> 
        </div>
    );
}; 

// Guest inputs their name, email and phone number
// when submitted, create a guest object in database 