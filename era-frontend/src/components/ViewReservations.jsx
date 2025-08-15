import React, { useState } from 'react';
import { getGuestByEmail } from '../service/GuestService';
import { getReservationById } from "../service/ReservationService";
import '../styles/ViewReservations.css';
import { useNavigate } from 'react-router-dom';

export default function ViewReservations() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: "",
    reservationNumber: ""
  });

  const [error, setError] = useState("");

  // handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(""); // Clear previous errors

    // Check if guest exists
    let guest;
    try {
      guest = await getGuestByEmail(formData.email);
      if (!guest) {
        setError("Guest does not exist.");
        return;
      }
    } catch {
      setError("Error checking guest. Please try again.");
      return;
    }

    // Check if reservation exists
    let reservation;
    try {
      reservation = await getReservationById(formData.reservationNumber);
      if (!reservation) {
        setError("Reservation does not exist.");
        return;
      }
    } catch {
      setError("Error checking reservation. Please try again.");
      return;
    }

    // Validate if guest.email matches reservation.guestEmail
    if (guest.email === reservation.guestEmail) {
      navigate('/view-guest-reservation', { state: { email: formData.email, resID: formData.reservationNumber } });
    } else {
      setError("Email and reservation do not match.");
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  return (
    <div className="content">
      <form onSubmit={handleSubmit} className="view-res-form m-auto mt-4 d-flex flex-column gap-2 shadow p-4" style={{ maxWidth: 400 }}>
        <h3>View Reservations</h3>
        <label>Email:</label>
        <input
          className='form-control'
          placeholder="Enter Email"
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
        />

        <label>Reservation Number:</label>
        <input
          className='form-control'
          placeholder="Enter Reservation Number"
          type="text"
          name="reservationNumber"
          value={formData.reservationNumber}
          onChange={handleChange}
        />
        <button className='btn btn-primary mb-2' type="submit">Find Reservation</button>
        {error && <div className="alert alert-danger">{error}</div>}
      </form>
    </div>
  );
}
