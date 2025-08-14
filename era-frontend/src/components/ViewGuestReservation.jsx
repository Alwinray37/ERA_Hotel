import { Navigate, useLocation } from "react-router-dom"
import { getReservationById, updateReservation } from "../service/ReservationService";
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

    // get reservation obj from database, save it to reservation var^^
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
    // get room obj from database and save it to room var
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
    // get guest obj from database and save it to guest var
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
    }, [reservation]);

    const handleCancel = () => {
        const updRes = {...reservation, status : "Cancelled" };
        console.log("Reservation status: " ,reservation.status);
        setReservation(updRes); // triggers re-render of component 
        // update reservation in the backend
        updateReservation(reservation.reservationId, reservation);
        
    }


    // loading message before data is loaded
    if(!reservation || !room || !guest){
        return (
            <div className="main-content container">
                loading
            </div>
        )
    }
    // if reservation is cancelled, show a custom page
    if(reservation.status == "Cancelled"){
        return(
            <div className="content d-flex flex-column align-items-center p-4">
                <h2>Reservation Cancelled</h2>
                <div className="res-content container shadow p-4">
                    <p><strong>Name:</strong> {guest.name}</p>
                    <p><strong>Email:</strong> {reservation.guestEmail}</p>
                    <p><strong>Room Number:</strong> {reservation.roomNumber}</p>
                    <p><strong>Room Details:</strong> {room.description}</p>
                    <p><strong>Check-in:</strong> {new Date(reservation.startDate).toDateString()}</p>
                    <p><strong>Check-out:</strong> {new Date(reservation.endDate).toDateString()}</p>
                    <p><strong>Status:</strong> {reservation.status}</p>
                </div>
            </div>
            
        )
    }
    // actual data to view when objects are not null 
    return(
        <div className="content">
            <div className="container  shadow p-4 ">
                <h2>Guest Reservation Details</h2>
                <p><strong>Name:</strong> {guest.name}</p>
                <p><strong>Email:</strong> {reservation.guestEmail}</p>
                <p><strong>Room Number:</strong> {reservation.roomNumber}</p>
                <p><strong>Room Details:</strong> {room.description}</p>
                <p><strong>Check-in:</strong> {new Date(reservation.startDate).toDateString()}</p>
                <p><strong>Check-out:</strong> {new Date(reservation.endDate).toDateString()}</p>
                <p><strong>Status:</strong> {reservation.status}</p>

                <div className="button-container contianer d-flex gap-4 justify-content-start w-2">
                    <button className="btn btn-warning">Modify Reservation</button>
                    <button className="btn btn-danger" onClick={handleCancel}>Cancel Reservation</button>
                </div>
            </div>
        </div>
    )
}