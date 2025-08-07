import './Hero.css'
import BookingSearch from '../search/BookingSearch'

export default function Hero({ children }) {
  return (
    <section className="hero-wrapper">
      <div className="hero-image">
        <h1>Welcome to Era Hotel</h1>
        <p>Your comfort is our <span>priority.</span></p>
        
        {children}
      </div>
    </section>
  )
}
