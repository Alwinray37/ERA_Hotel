import React, {useState, useEffect} from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { getGuestByEmail, createGuest, updateGuest} from '../service/GuestService';
import { appendResIdToGuest, appendResIdToRoom, createReservation, updateReservation } from '../service/ReservationService'; // Assuming you have a service to create reservations
import { set } from 'date-fns';
import { getRoom, updateRoom } from '../service/RoomService';

export default function BookingForm() {
    const location = useLocation(); // to pull out values (room, searchStart, searchEnd) that were explicitly sent when navigating to this page.
    const navigate = useNavigate();

    const room = location.state?.room;                  // Get the room data passed from ViewRooms
    const searchStart = location.state?.searchStart;    // Get the search start date
    const searchEnd = location.state?.searchEnd;        // Get the search end date

    // function to determine # of days between searchStart and searchEnd
    const calculateNights = (start, end) => {
        if (!start || !end) return 0;
        const startDate = new Date(start);
        const endDate = new Date(end);
        const timeDiff = endDate - startDate;
        return (Math.ceil(timeDiff / (1000 * 3600 * 24))); // convert ms to days
    };
    const numberOfNights = calculateNights(searchStart, searchEnd);

    // guestInfo state to hold user input
    // for guest information, to create a new guest if they don't exist
    const [guestInfo, setGuestInfo] = useState({
        name: '',
        email: '',
        phone: ''
    });

    // used to create a reservation object
    // this will be sent to the server to create a new reservation
    const [forReservation, setForReservation] = useState({
        roomNumber: room.roomNumber,
        guestEmail: guestInfo.email,
        startDate: searchStart,
        endDate: searchEnd,
        totalCost: (room.price * numberOfNights).toFixed(2), // total cost based on room price and number of days
        status: "Confirmed" // default status
    });

    // Update reservation guestEmail when guestInfo.email changes
    useEffect(() => {
        setForReservation(prev => ({
            ...prev,
            guestEmail: guestInfo.email
        }));
    }, [guestInfo.email]);

    // handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();

         // Check if guest already exists by email
        try {
            // Check if guest exists
            let guest = await getGuestByEmail(guestInfo.email);
            if (!guest) {
                guest = await createGuest(guestInfo);
                console.log('New guest created:', guest);
            } else {
                setGuestInfo({
                    name: guest.name,
                    email: guest.email,
                    phone: guest.phone
                });
                console.log("Guest Exists: ", guest);
            }

            // Create reservation
            const reservationData = {
                ...forReservation,
                guestEmail: guest.email
            };
            const reservation = await createReservation(reservationData);
            console.log("Reservation Created: ", reservation.reservationId);

            // Append reservation ID to guest
            const updatedGuest = await appendResIdToGuest(guest.guestId, reservation.reservationId);
            console.log("Updated Guest ResList", updatedGuest);
            
            // update guest to database 
            const saveGuest = await updateGuest(guest.guestId, updatedGuest); 

            // get room obj
            let resRoom = await getRoom(room.roomId);
            if(!resRoom){
                console.log("Room failed to load");
            }
            // append to roomReservations 
            const tempRoom = await appendResIdToRoom(resRoom.roomId, reservation.reservationId);
            console.log("Updated Room Res list: ", tempRoom);

            await updateRoom(resRoom.roomId, tempRoom);
            
            // Optionally navigate to a confirmation page
            navigate('/confirmation', { state: { guest: updatedGuest, reservation } });

        } catch (error) {
            console.error('Error during booking', error);
            alert('Error during booking. Please try again.');
        }
    };

    // handle changes in form inputs
    const handleChange = (e) => {
        const { name, value } = e.target;
        setGuestInfo({ ...guestInfo, [name]: value });
    };
    // Format dates for display
    const formatDate = (date) => {
        if (!date) return '';
        const d = new Date(date);
        return d.toLocaleDateString();
    };


    return (
    <div className="content">
        <div className='container'>
            <div className='booking-details container border rounded-3 p-4 shadow'>
                <h2>Booking For Room {room.roomNumber}</h2>
                <p><strong>Price:</strong> ${room.price} per night</p>
                <p><strong>Check-in Date:</strong> {searchStart.toDateString()}</p>
                <p><strong>Check-out Date:</strong> {searchEnd.toDateString()}</p>
                <p><strong>Number of Nights:</strong> {numberOfNights}</p>
                <p><strong>Total Cost:</strong> ${(room.price * numberOfNights).toFixed(2)}</p>
            </div>
            {/* Form for user to input their details */}
            <form className='guest-booking-form form border p-4 d-flex gap-2 rounded shadow  flex-column justify-content-center align-items-center' onSubmit={handleSubmit}>
                <h3>Guest Information</h3>
                <div>
                    <label htmlFor="name">Name:</label>
                    <input className='form-control' type="text" name="name" value={guestInfo.name} onChange={handleChange} required placeholder='Enter your name'/>
                </div>
                <div>
                    <label htmlFor="email">Email:</label>
                    <input className='form-control' type="email" name="email" value={guestInfo.email} onChange={handleChange} required placeholder='Enter your email'/>
                </div>
                <div>
                    <label htmlFor="phone">Phone:</label>
                    <input className='form-control' type="tel" name="phone" value={guestInfo.phone} onChange={handleChange} required placeholder='Enter your phone number'/>
                </div>
                <button type="submit" className='btn btn-primary w-1'>Confirm Booking</button>
            </form> 
        </div>
    </div>
    );
}; 

// Guest inputs their name, email and phone number
// when button is clicked, check if guest already exists
// if exists, create a reservation instance, and append to the existing guest's reservations
// if not, create a new guest instance, and then create a reservation instance
// reservation instances should be appended to the guest's reservations array 
// as well as the room's reservations array


