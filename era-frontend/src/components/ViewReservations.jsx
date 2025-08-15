// this is where the guests may view their reservations
import React, {useState} from 'react';
import { getGuestByEmail } from '../service/GuestService';
import { getReservationById } from "../service/ReservationService"
import '../styles/ViewReservations.css'
import { useNavigate } from 'react-router-dom';

export default function ViewReservations() {
    const navigate = useNavigate();

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
        let reservation;
        try{
            reservation = await getReservationById(formData.reservationNumber)
            if(!reservation){
            // if guest does not exist
            alert("Reservation does not exist");
            }else{
                console.log("Reservation Number exists: ", reservation)
            };
        } catch{
            alert("Reservation Does Not Exist.")
        }

        // validate if guest.guestEmail == reservation.guestEmail
        if(guest.email == reservation.guestEmail){
            // alert("Match");

            // navigate to viewGuestRes component
            navigate('/view-guest-reservation', { state: { email: formData.email, resID : formData.reservationNumber } });
        } else {
            alert("No match");
        }
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    }

    return (
        <div className="content ">
            <form onSubmit = {handleSubmit} className = "view-res-form  m-auto mt-4  d-flex flex-column gap-2 shadow p-4">
                <h3>View Reservations</h3>
                <label>Email:</label>
                <input className='form-control' placeholder="Enter Email" type="email" name="email" value={formData.email} onChange={handleChange}/>

                <label>Reservation Number:</label>
                <input className='form-control' placeholder="Enter Reservation Number" type="text" name="reservationNumber" value={formData.reservationNumber} onChange={handleChange}/>
                 <button className='btn btn-primary mb-2' type="submit">Find Reservation</button>
            </form>
        </div>

    )
}
