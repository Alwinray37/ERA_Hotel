import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addRoom, getRoomByNumber } from "../service/RoomService";

export default function AddRoom() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    roomNumber: "",
    description: "",
    price: "",
    type: "Single",
    roomImgUrl: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    // Basic validation
    if (
      !formData.roomNumber ||
      !formData.description ||
      !formData.price ||
      !formData.type
    ) {
      setError("Please fill in all required fields.");
      return;
    }

    // Check if room number already exists
    try {
      const existingRoom = await getRoomByNumber(formData.roomNumber);
      if (existingRoom) {
        setError("Error checking room number. Please try again.");
        return;
      }
    } catch (err) {
      // If the backend returns 404, it means the room does not exist, so we can proceed
      if (err.response && err.response.status !== 404) {
        setError("Error checking room number. Please try again.");
        return;
      }
    }

    try {
      // Convert price to number
      const roomToAdd = {
        ...formData,
        price: Number(formData.price),
        roomReservations: [],
      };
      await addRoom(roomToAdd);
      setSuccess("Room added successfully!");
      setTimeout(() => navigate("/admin-dashboard"), 3000);
    } catch (err) {
      setError("Failed to add room. Please try again.");
    }
  };

  return (
    <div className="content">
      <div className="container" style={{ maxWidth: 600 }}>
        <h2 className="mb-4">Add a Room</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        {success && <div className="alert alert-success">{success}</div>}
        <form onSubmit={handleSubmit} className="d-flex flex-column gap-3 shadow p-4">
          <div>
            <label className="form-label" htmlFor="roomNumber">
              Room Number<span className="text-danger">*</span>
            </label>
            <input
              className="form-control"
              type="text"
              name="roomNumber"
              id="roomNumber"
              value={formData.roomNumber}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label className="form-label" htmlFor="description">
              Description<span className="text-danger">*</span>
            </label>
            <textarea
              className="form-control"
              name="description"
              id="description"
              value={formData.description}
              onChange={handleChange}
              required
              rows={3}
            />
          </div>
          <div>
            <label className="form-label" htmlFor="price">
              Price per Night (USD)<span className="text-danger">*</span>
            </label>
            <input
              className="form-control"
              type="number"
              name="price"
              id="price"
              value={formData.price}
              onChange={handleChange}
              required
              min="0"
              step="0.01"
            />
          </div>
          <div>
            <label className="form-label" htmlFor="type">
              Room Type<span className="text-danger">*</span>
            </label>
            <select
              className="form-select"
              name="type"
              id="type"
              value={formData.type}
              onChange={handleChange}
              required
            >
              <option value="Single">Single</option>
              <option value="Double">Double</option>
              <option value="King">King</option>
              <option value="Suite">Suite</option>
            </select>
          </div>
          <div>
            <label className="form-label" htmlFor="roomImgUrl">
              Image URL
            </label>
            <input
              className="form-control"
              type="text"
              name="roomImgUrl"
              id="roomImgUrl"
              value={formData.roomImgUrl}
              onChange={handleChange}
              placeholder="e.g. ../assets/Images/Room 101.jpg"
            />
          </div>
          <button type="submit" className="btn btn-success">
            Add Room
          </button>
        </form>
      </div>
    </div>
  );
}
