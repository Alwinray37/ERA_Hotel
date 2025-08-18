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
    );
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    // presetting the guest form data from viewGuestRes component
    useEffect(() => {
        if (guest) {
            setFormData({ name: guest.name, email: guest.email });
        }
    }, [guest]);

    // fetch guest data using res.guestEmail 
    useEffect(() => {
        if (reservation?.guestEmail) {
            getGuestByEmail(reservation.guestEmail)
                .then(data => {setGuest(data)}
            );
        }
    }, [reservation?.guestEmail]);
    console.log(guest);

    // handle when user submits form 
    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(''); // reset any previous errs
        setSuccess('');

        // If the email is being changed, check if it already exists
        if (formData.email !== guest.email) {
            const existingGuest = await getGuestByEmail(formData.email);
            if (existingGuest && existingGuest.guestId !== guest.guestId) {
                setError("Error, this email is already being used. Please choose a different email.");
                return;
            }
        }

        // Proceed with update
        const updatedRes = { ...reservation, guestEmail: formData.email };
        await updateReservation(reservation.reservationId, updatedRes);

        const updatedGuest = { ...guest, name: formData.name, email: formData.email };
        await updateGuest(guest.guestId, updatedGuest);

        setSuccess("Changes Made!");
        setTimeout( () => { navigate('/view-guest-reservation', { state: { email: formData.email, resID: reservation.reservationId } }); }, 3000)
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
            <div className="container m-auto d-flex flex-column gap-4 align-items-center mt-4">
                <h2>Modify Guest Reservation</h2>
                 {success && <div className="alert alert-success">{success}</div>}
                <form onSubmit = {handleSubmit}  className="form w-50 m-auto d-flex flex-column gap-2 shadow p-4">
                    <label htmlFor="name">Name:</label>
                    <input className="form-control" type="text" name="name" placeholder="Enter new name" value={formData.name} onChange={handleChange}/>
                    <label htmlFor="email">Email: </label>
                    <input className="form-control" type="email" name="email" placeholder="Enter new email" value={formData.email} onChange={handleChange}/>
                    
                    <div><strong>Reservation ID: </strong>{reservation.reservationId}</div>
                    <div><strong>Room: </strong>{reservation.roomNumber}</div>
                    <div><strong>Check-in: </strong>{new Date(reservation.startDate).toDateString()}</div>
                    <div><strong>Check-out: </strong>{new Date(reservation.endDate).toDateString()}</div>
                    <div><strong>Cost: </strong>{reservation.totalCost}</div>
                    <button type="submit" className="btn btn-success">Save Changes</button>
                </form>
                {error && <div className="alert alert-danger">{error}</div>}
            </div>
        </div>
    );
}