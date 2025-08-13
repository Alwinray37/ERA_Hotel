import { useLocation } from "react-router-dom";

export default function Confirmation(){
    const location = useLocation();

    const guest = location.state.guest;
    const reservation = location.state.reservation;

    return (
        <div className="main-content">
            <h2>Confirmed!</h2>
            <div className="room-info">
                <p><strong>Reservation Number: {reservation.reservationId}</strong></p>
                <p>room number: {reservation.roomNumber}</p>
                <p>Total cost: {reservation.totalCost}</p>
            </div>
            <div className="guest-info">
                <p>Guest name: {guest.name}</p>
                <p>Guest email: {guest.email}</p>
                <p>Guest phone: {guest.phone}</p>
            </div>

            <p>Please save the Reservation Number for future reference!</p>
        </div>
    )
}