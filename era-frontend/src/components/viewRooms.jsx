import React from 'react'
import rooms from '../assets/dummyRoomData.json'

function ViewRooms({rooms}) {
  return (
    <div className="room-cards-container">
      {rooms && rooms.length > 0 ? (
        rooms.map((room) => (
          <div className="room-card" key={room.roomNumber}>
            <h3>Room {room.roomNumber} - {room.roomType}</h3>
            <p><strong>Price:</strong> ${room.nightlyPrice}</p>
            <p><strong>Max Occupancy:</strong> {room.maxOccupancy}</p>
            <p><strong>Description:</strong> {room.roomDescription}</p>
            <p><strong>Amenities:</strong> {room.roomAmenities && room.roomAmenities.join(', ')}</p>
            <p><strong>Available:</strong> {room.isAvailable ? "Yes" : "No"}</p>
            <button>Book Now</button> // need to add book now function
          </div>
        ))
      ) : (
        <p>No rooms available.</p>
      )}
    </div>
  )
}

export default ViewRooms