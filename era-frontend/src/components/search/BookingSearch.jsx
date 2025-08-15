import './BookingSearch.css'
import { FaRegCalendarAlt } from 'react-icons/fa'
import DatePicker from 'react-datepicker'
import 'react-datepicker/dist/react-datepicker.css'
import { useState } from 'react'
import { format } from 'date-fns'

export default function BookingSearch({ handleSearch }) {
	// State for date range selection
	const [dateRange, setDateRange] = useState([null, null]);
	const [startDate, endDate] = dateRange;
	
	// State for room type filter
	const [roomType, setRoomType] = useState('All');

	// Format the selected date range for display
	const getFormattedRange = () => {
    if (startDate && endDate) {
		return `${format(startDate, 'MMM d')} – ${format(endDate, 'MMM d')}`
    } else if (startDate) {
      	return format(startDate, 'MMM d')
    }
    return ''
  }

  // Handle search button click, passing date range and room type to parent
  const handleSearchClick = () => {
    handleSearch(dateRange, roomType);
  }

  return (
    <section className="hero-section container">
      <div className="hero-overlay d-flex flex-column justify-content-center align-items-center">
        <h1>Welcome to ERA Hotel</h1>
        <p>Your comfort is our <strong>priority</strong>.</p>
        <div className="booking-bar d-flex flex-column flex-md-row align-items-center gap-3">
          {/* Date Picker */}
          <div className="date-container">
            <div className="input-with-icon">
              <FaRegCalendarAlt className="calendar-icon" />
              <DatePicker
                selectsRange={true}
                startDate={startDate}
                endDate={endDate}
                onChange={update => setDateRange(update)}
                placeholderText="Select dates"
                className="date-picker-input"
                dateFormat="MMM d"
                value={getFormattedRange()}
              />
            </div>
          </div>
          {/* Room Type Dropdown */}
          <div className="room-type-filter ms-2">
            <select
              className="form-select"
              value={roomType}
              onChange={e => setRoomType(e.target.value)}
            >
              <option value="All">All Types</option>
              <option value="Single">Single</option>
              <option value="Double">Double</option>
              <option value="King">King</option>
              <option value="Suite">Suite</option>
            </select>
          </div>
          {/* Search Button */}
          <button
            className="btn btn-primary ms-2"
            onClick={handleSearchClick}
            disabled={!startDate || !endDate}
          >
            Search
          </button>
        </div>
      </div>
    </section>
  );

}
