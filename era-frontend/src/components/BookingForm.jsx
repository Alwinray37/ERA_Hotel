import React, {useState} from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { getGuestByEmail, createGuest } from '../service/GuestService';

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
    const handleSubmit = async (e) => {
        e.preventDefault();

         // Check if guest already exists by email
        try {
            const guest = await getGuestByEmail(formData.email);
            if (guest) {
                console.log('Guest already exists. ', guest);
                return;
            }

            // Create new guest if not found
            const newGuest = await createGuest(formData);
            console.log('New guest created:', newGuest);

            // Redirect to a confirmation page 
            // navigate(`/confirmation`);
        } catch (error) {
            console.error('Error creating guest:', error);
            alert('Failed to create guest. Please try again.');
        }
    };


    // handle changes in form inputs
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    return (
        <div className='main-content'>
            <div className='booking-details'>
                <h2>Booking For Room {room.roomNumber}</h2>
                <p><strong>Price:</strong> ${room.price} per night</p>
                <p><strong>Number of Nights:</strong> {days}</p>
                <p><strong>Total Cost:</strong> ${(room.price * days).toFixed(2)}</p>
            </div>
            {/* Form for user to input their details */}
            <form className='guest-booking-form form' onSubmit={handleSubmit}>
                <h3>Guest Information</h3>
                <div>
                    <label htmlFor="name">Name:</label>
                    <input type="text" name="name" value={formData.name} onChange={handleChange} required placeholder='Enter your name'/>
                </div>
                <div>
                    <label htmlFor="email">Email:</label>
                    <input type="email" name="email" value={formData.email} onChange={handleChange} required placeholder='Enter your email'/>
                </div>
                <div>
                    <label htmlFor="phone">Phone:</label>
                    <input type="tel" name="phone" value={formData.phone} onChange={handleChange} required placeholder='Enter your phone number'/>
                </div>
                <button type="submit" >Confirm Booking</button>
            </form> 
        </div>
    );
}; 

// Guest inputs their name, email and phone number
// when button is clicked, check if guest already exists
// if exists, create a reservation instance, and append to the existing guest's reservations
// if not, create a new guest instance, and then create a reservation instance
// redirect to a confirmation page

