// this is where the guests may view their reservations
import React, {useState} from 'react';
import { getGuestByEmail } from '../service/GuestService';
export default function ViewReservations() {

    //stuff to form the booking forms
    const [formData, setFormData] = useState({
        email: "",
        reservationNumber: ""
    });

    // handle form submission
    const handleSubmit = (e) => {
       e.preventDefault();

        // system checks if the email is in the database already
        getGuestByEmail(formData.email)
        .then(guest => {
            if (guest) {
                console.log('Guest already exists:', guest);
                // Proceeds with booking using the existing guest data
            }
        })
        .catch (error => {
            console.error('Error fetching guest:', error);
        });
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    }

    return (
        <div className="main-content">
            <form onSubmit = {handleSubmit} >
                <h3>View Reservations</h3>
                <input placeholder="Enter Email" type="email" name="email" value={formData.email} onChange={handleChange}/>
                <input placeholder="Enter Reservation Number" />
                 <button className='btn btn-primary mb-2' type="submit">Submit</button>
            </form>
        </div>

    )
}
