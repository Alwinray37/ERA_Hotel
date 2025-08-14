import { useNavigate } from "react-router-dom";

// 1) Import your images from src/assets/images
import img101 from "../assets/Images/Room 101.jpg";
import img202 from "../assets/Images/Room 202.jpg";
import img303 from "../assets/Images/Room 303.jpg";
import img309 from "../assets/Images/Room 309.jpg";
import img310 from "../assets/Images/Room 310.jpg";

// 2) Map roomNumber to image
const roomImages = {
  101: img101,
  202: img202,
  303: img303,
  309: img309,
  310: img310,
};

function ViewRooms({ availableRooms, sStart, sEnd }) {
  const navigate = useNavigate();

  // Handle clicking "Book Now"
  const handleBookNow = (room) => {
    navigate(`/book/${room.roomId}`, {
      state: {
        room,
        searchStart: sStart,
        searchEnd: sEnd,
      },
    });
  };
return (
    <div className="room-cards-container container p-2 d-flex flex-column gap-4">
      {availableRooms && availableRooms.length > 0 ? (
        availableRooms.map((room) => (
          <div className="room-card card p-3" key={room.roomNumber}>
            {/* 3) Room image */}
            <img
              src={roomImages[room.roomNumber]}
              alt={`Room ${room.roomNumber}`}
              className="card-img-top mb-3"
              style={{
                width: "100%",
                height: 220,
                objectFit: "cover",
                borderRadius: 12,
              }}
            />

            <h3 className="mb-2">Room {room.roomNumber}</h3>
            <p className="mb-1">
              <strong>Price:</strong> ${room.price ?? room.nightlyPrice}
            </p>
            <p className="mb-3">
              <strong>Description:</strong>{" "}
              {room.description ?? room.roomDescription}
            </p>

            <button
              className="btn btn-dark"
              onClick={() => handleBookNow(room)}
            >
              Book Now
            </button>
          </div>
        ))
      ) : (
        <p>No rooms available.</p>
      )}
    </div>
  );
}

export default ViewRooms;