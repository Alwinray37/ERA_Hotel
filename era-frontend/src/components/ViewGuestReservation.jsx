import { Navigate, useLocation } from "react-router-dom"
import { getReservationById } from "../service/ReservationService";
import { useState, useEffect } from "react";
import { getRoomByNumber } from "../service/RoomService";
import { getGuestByEmail } from "../service/GuestService";


export default function ViewGuestRes(){
    const location = useLocation(); 

    // takes the guest email and resID from ViewReservations page
    const email = location.state.email;
    const resId = location.state.resID; // reservationID (ABC123)
    // checking if values transfered
    // console.log("Guest Email", email);
    // console.log("Reservation Number", resId);

    // get reservation by id, room by roomNumber
    const [reservation, setReservation] = useState(null); 
    const [room, setRoom] = useState(null);
    const [guest, setGuest] = useState(null);

    useEffect(() => {
        const fetchReservation = async () => {
            try {
                const res = await getReservationById(resId);
                setReservation(res);
                console.log("res obj: ", res);
            } catch (error) {
                console.error("Error fetching reservation or room:", error);
            }
        };
        if (resId) fetchReservation();
    }, [resId]);

    useEffect( () => {
        const fetchRoom = async () => {
            console.log(reservation.roomNumber)
            try {
                const res = await getRoomByNumber(reservation.roomNumber);
                setRoom(res);
                console.log("room: " , room);
            } catch {
                console.log("Error fetching room");
            }
        };
        if(reservation) fetchRoom();
    }, [reservation]);

    useEffect( () => {
        const fetchGuest = async () => {
            try {
                const res = await getGuestByEmail(email);
                setGuest(res);
                console.log("guest: " , room);
            } catch {
                console.log("Error fetching room");
            }
        };
        if(reservation) fetchGuest();
    }, [reservation])


    // loading message before data is loaded
    if(!reservation || !room || !guest){
        return (
            <div className="main-content container">
                loading
            </div>
        )
    }

    return(
        <div className="content">
            <div className="container  shadow p-4 ">
                <h2>Guest Reservation Details</h2>
            <p>Name: {guest.name}</p>
            <p>Email: {reservation.guestEmail}</p>
            <p>Room Number: {reservation.roomNumber}</p>
            <p>Room Details: {room.description}</p>
            <p>Check-in: {new Date(reservation.startDate).toDateString()}</p>
            <p>Check-out: {new Date(reservation.endDate).toDateString()}</p>

            <div className="button-container contianer d-flex gap-4 justify-content-start w-2">
                <button className="btn btn-warning">Modify Reservation</button>
                <button className="btn btn-danger">Cancel Reservation</button>
            </div>
            </div>
        </div>
    )
}