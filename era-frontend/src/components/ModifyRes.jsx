import { useLocation, useNavigate } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import { getGuestByEmail, updateGuest } from "../service/GuestService";
import { updateReservation } from "../service/ReservationService";

// navigate here from ViewGuestReservations component
// imports the reservation obj from guestreservations page 
// shows a form where user can change name, email

export default function ModifyRes() {
    const location = useLocation();
    const navigate = useNavigate();
    const reservation = location.state.reservation;
    const [guest, setGuest] = useState(null);
    const [formData, setFormData] = useState(
        {   name: "",
        email: ""   }  
    )

    // fetch guest data using res.guestEmail 
    useEffect(() => {
        if (reservation?.guestEmail) {
            getGuestByEmail(reservation.guestEmail)
                .then(data => {setGuest(data)}
            );
        }
    }, [reservation?.guestEmail]);
    console.log(guest);

    // 
    const handleSubmit = (e) => {
        e.preventDefault();

        const updatedRes = {...reservation, guestEmail: formData.email}; // reservation only has guest email 
        updateReservation(reservation.reservationId, updatedRes);   
        const updatedGuest = {... guest, name: formData.name, email: formData.email};
        updateGuest(guest.guestId, updatedGuest);

        alert("Changes made!");
        navigate('/view-guest-reservation', { state: { email: formData.email, resID : reservation.reservationId } })// navigate back to view reservation page
    }

    // updates the formData variables: email, name
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    }

    if(!guest){
        return (
            <div className="container">
                <h2>Loading...</h2>
            </div>
        )
    }

    return (
        <div className="content">
            <h2>Modify Guest Reservation</h2>

            <div className="container m-auto">
                <form onSubmit = {handleSubmit}  className="form w-50 m-auto d-flex flex-column gap-2 shadow p-4">
                    <label htmlFor="name">Name: {guest.name}</label>
                    <input className="form-control" type="text" name="name" placeholder="Enter new name" value={formData.name} onChange={handleChange}/>
                    <label htmlFor="email">Email: {reservation.guestEmail} </label>
                    <input className="form-control" type="email" name="email" placeholder="Enter new email" value={formData.email} onChange={handleChange}/>
                    
                    <p><strong>Reservation ID: </strong>{reservation.reservationId}</p>
                    <p><strong>Room: </strong>{reservation.roomNumber}</p>
                    <p><strong>Check-in: </strong>{new Date(reservation.startDate).toDateString()}</p>
                    <p><strong>Check-out: </strong>{new Date(reservation.endDate).toDateString()}</p>
                    <p><strong>Cost: </strong>{reservation.totalCost}</p>
                    <button type="submit" className="btn btn-success">Save Changes</button>
                </form>
            </div>
        </div>
    );
}