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
                <div className="room-card card " key={room.roomNumber}>
                    <img
                        src={`${roomImgUrlBase} ${room.roomNumber}.jpg`}
                        alt={`Room ${room.roomNumber}`}
                        className="room-image card-img-top"
                    />

                    <h3 >Room {room.roomNumber}</h3>
                    <p><strong>Price:</strong> ${room.price}</p>
                    <p><strong>Description:</strong> {room.description}</p>

                    <button className="btn btn-dark" onClick={() => handleBookNow(room)}>Book Now</button>
                </div>
            ))
        ) : (
        <p>No rooms available.</p>
      )}
    </div>
  );
}

export default ViewRooms;
