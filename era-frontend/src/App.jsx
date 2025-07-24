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
          Era Hotel is an intro to software (COMP 380) class project. Era Hotel is an online website where users can create hotel reservations.
        </p>
      </div>
      <Footer />
    </>
  );
}

export default App;
