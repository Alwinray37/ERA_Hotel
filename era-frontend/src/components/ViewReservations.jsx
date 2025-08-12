// this is where the guests may view their reservations
import React, {useState} from 'react';
import { getGuestByEmail } from '../service/GuestService';
import { getReservationById } from "../service/ReservationService"
import '../styles/ViewReservations.css'

export default function ViewReservations() {
    //stuff to form the booking forms
    const [formData, setFormData] = useState({
        email: "",
        reservationNumber: ""
    });

    // handle form submission
    const handleSubmit = async (e) => {
       e.preventDefault();

        // system checks if the email is in the database already
        const guest = await getGuestByEmail(formData.email);
        if(!guest){
            // if guest does not exist
            alert("Guest does not exist");
        }else{
            console.log("guest exist: ", guest)
        }

        // grab the reservation num, save it, and check if resEmail == getGuestByEmail
        const resNumber = await getReservationById(formData.reservationNumber);
        if(!resNumber){
                    // if guest does not exist
                    alert("Reservation does not exist");
        }else{
            console.log("Reservation Number exists: ", resNumber)
        }

        // validate if guest.guestEmail == resNumber.guestEmail
        if(guest.email == resNumber.guestEmail){
            alert("Match");
        } else {
            alert("No match");
        }

    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    }

    return (
        <div className="main-content ">
            <form onSubmit = {handleSubmit} className = "view-res-form rform">
                <h3>View Reservations</h3>
                <label>Email:</label>
                <input placeholder="Enter Email" type="email" name="email" value={formData.email} onChange={handleChange}/>

                <label>Reservation Number:</label>
                <input placeholder="Enter Reservation Number" type="text" name="reservationNumber" value={formData.reservationNumber} onChange={handleChange}/>
                 <button className='btn btn-primary mb-2' type="submit">Submit</button>
            </form>
        </div>

    )
}
