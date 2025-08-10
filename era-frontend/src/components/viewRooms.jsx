import { set } from "date-fns";
import { listRooms } from "../service/RoomService";
import { useNavigate } from "react-router-dom";

function ViewRooms({ availableRooms }) {
    const navigate = useNavigate();

    // handleBookNow function to navigate to BookingForm with roomId
    const handleBookNow = (room) => {
        navigate(`/book/${room.roomId}`, { state: { room } });
    };

    return (
        <div className="room-cards-container">
            {availableRooms && availableRooms.length > 0 ? (
                availableRooms.map((room) => (
                    <div className="room-card" key={room.roomNumber}>
                        <h3>Room {room.roomNumber}</h3>
                        <p><strong>Price:</strong> ${room.price}</p>
                        <p><strong>Description:</strong> {room.description}</p>
                        <button onClick={() => handleBookNow(room)}>Book Now</button>
                    </div>
                ))
            ) : (
                <p>No rooms available.</p>
            )}
        </div>
    )
}

export default ViewRooms;