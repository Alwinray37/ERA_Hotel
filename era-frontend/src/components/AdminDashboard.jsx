import { Navigate, useLocation } from "react-router-dom"
import { useState, useEffect } from "react";
import { listGuests } from "../service/GuestService";
import { listReservations } from "../service/ReservationService";


export default function AdminDashboard(){
    const [guests, setGuests] = useState([]);
    const [reservations, setReservations] = useState([]);

    useEffect(() => {
        listGuests()
        .then(data => {
            setGuests(data);
        })
        .catch(err => console.log("error fetching guests", err));

        listReservations()
        .then(data => {
            setReservations(data);
        })
    }, []);

    console.log("Guests list: ", guests);
    console.log("Reservations : ", reservations);


    return(
        <div className="content">
            <div className="container shadow p-4 ">
                <h2>Admin Dashboard</h2>
                <table>
                    <tr></tr>
                </table>
            </div>
        </div>
    )
}