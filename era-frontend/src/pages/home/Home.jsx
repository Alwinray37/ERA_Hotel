import React from "react";
import BookingSearch from "../../components/search/BookingSearch";
import ViewRooms from "../../components/viewRooms";
import dummyRooms from "../../assets/dummyRoomData.json";

export default function Home() {
  const [filteredRooms, setFilteredRooms] = React.useState([]); // Start with no rooms

  const handleSearch = (dateRange) => {
    // Example: filter available rooms (customize as needed)
    const availableRooms = dummyRooms.filter((room) => room.isAvailable);
    setFilteredRooms(availableRooms);
  };

  return (
    <div>
      <h2>Home Page</h2>
      <p>Welcome to the Era Hotel homepage!</p>

      
      <ViewRooms />
    </div>
  );
}