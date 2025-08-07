import './BookingSearch.css'
import { FaRegCalendarAlt } from 'react-icons/fa'
import DatePicker from 'react-datepicker'
import 'react-datepicker/dist/react-datepicker.css'
import { useState } from 'react'
import { format } from 'date-fns'

export default function BookingSearch({ onSearch }) {
  const [dateRange, setDateRange] = useState([null, null])
  const [startDate, endDate] = dateRange

  const getFormattedRange = () => {
    if (startDate && endDate) {
      return `${format(startDate, 'MMM d')} – ${format(endDate, 'MMM d')}`
    } else if (startDate) {
      return format(startDate, 'MMM d')
    }
    return ''
  }

  const handleSearchClick = () => {
    // Call the parent's onSearch with the selected date range
    onSearch(dateRange)
  }

  return (
    <>
      {/* Hero Section with background image */}
      <section className="hero-section">
        <div className="hero-overlay">
          <h1>Welcome to Era Hotel</h1>
          <p>Your comfort is our <strong>priority</strong>.</p>

          <div className="booking-bar">
            <div className="date-container">
              <span className="date-label">Dates</span>
              <div className="input-with-icon">
                <FaRegCalendarAlt className="calendar-icon" />
                <DatePicker
                  selectsRange={true}
                  startDate={startDate}
                  endDate={endDate}
                  onChange={(update) => {
                    setDateRange(update)
                  }}
                  placeholderText="Select dates"
                  className="date-picker-input"
                  dateFormat="MMM d"
                  value={getFormattedRange()}
                />
              </div>
            </div>
            <button onClick={handleSearchClick}>Search</button>
          </div>
        </div>
      </section>
    </>
  )
}
