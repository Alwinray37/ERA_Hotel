
function ViewRooms({rooms}) {
  return (
    <div className="room-cards-container">
      {rooms && rooms.length > 0 ? (
        rooms.map((room) => (
          <div className="room-card" key={room.roomNumber}>
            <h3>Room {room.roomNumber}</h3>
            <p><strong>Price:</strong> ${room.price}</p>
            <p><strong>Description:</strong> {room.description}</p>
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