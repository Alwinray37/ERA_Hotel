import { useNavigate } from "react-router-dom";


function ViewRooms({ availableRooms, sStart, sEnd }) {
  const navigate = useNavigate();
  // url base roomNumber to image
  const roomImgUrlBase = "../assets/Images/Room";

    //function to handle the book now button in room card
    const handleBookNow = (room) => {
        navigate(`/book/${room.roomId}`, {
            state: { room, searchStart: sStart, searchEnd: sEnd },
        });
    };

  return (
    <div className="room-cards-container container p-2 d-flex flex-column gap-4">
        {availableRooms && availableRooms.length > 0 ? (
            availableRooms.map((room) => (
                <div className="room-card bg-white shadow d-flex flex-column flex-md-row gap-4" key={room.roomNumber}>
                    <div className="col">
                        <h3 >Room {room.roomNumber}</h3>
                        <p><strong>Price:</strong> ${room.price}</p>
                        <p><strong>Description:</strong> {room.description}</p>
                        <p><strong>Room Type:</strong> {room.type}</p>
                        <button className="btn btn-dark" onClick={() => handleBookNow(room)}>Book Now</button>
                    </div>
                    <div className="col">
                        <img src={`${roomImgUrlBase} ${room.roomNumber}.jpg`} alt={`Room ${room.roomNumber}`} className="rounded card-img-top col"  />
                    </div>
                </div>
            ))
        ) : (
        <p>No rooms available.</p>
      )}
    </div>
  );
}

export default ViewRooms;
