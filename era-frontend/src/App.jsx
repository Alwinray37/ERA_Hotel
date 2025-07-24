import Navbar from './components/navbar/Navbar';
import Hero from './components/hero/Hero';
import BookingSearch from './components/search/BookingSearch';
import Footer from './components/footer/footer';
import './App.css';

function App() {
  return (
    <>
      <Navbar />
      <Hero />

      <div className="about-us-section">
        <h2>About Era Hotel</h2>
        <p>
          Nestled in the heart of paradise, Era Hotel is your premier destination for luxury,
          relaxation, and unforgettable experiences. Whether you’re planning a weekend escape or
          a long vacation, we provide modern comfort, top-tier service, and an inviting atmosphere
          for all travelers.
        </p>
      </div>
      <Footer />
    </>
  );
}

export default App;
