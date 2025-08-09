import { listRooms } from "../service/RoomService";
import React, {useEffect, useState } from 'react';

function ViewRooms({ searchStart, searchEnd }) {
    const [rooms, setRooms] = useState([]); // get all rooms from database and save it to this variable
    const [filteredRooms, setFilteredRooms] = useState([]); // rooms that are available based on searchStart and searchEnd
    
    

    
    return (
        <div className="room-cards-container">
            {filteredRooms && filteredRooms.length > 0 ? (
                filteredRooms.map((room) => (
                    <div className="room-card" key={room.roomNumber}>
                        <h3>Room {room.roomNumber}</h3>
                        <p><strong>Price:</strong> ${room.price}</p>
                        <p><strong>Description:</strong> {room.description}</p>
                        <button>Book Now</button> {/* need to add book now function */}
                    </div>
                ))
            ) : (
                <p>No rooms available.</p>
            )}
        </div>
    )
}

export default ViewRooms;