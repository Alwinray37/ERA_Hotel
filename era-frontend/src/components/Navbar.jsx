import { Link } from "react-router-dom";
 
export default function Navbar() {
  return (
    <nav className="navbar">
      <h1 className="navbar-brand">Era Hotel</h1>
      <ul className="navbar-links">
        <li><Link to="/" onClick={e => {
            e.preventDefault();
            window.location.href = '/';
          }}>Home</Link></li>
        <li><Link to="/view-reservation">My Reservations</Link></li>
        <li><Link to="/about">About</Link></li>
        <li><Link to='/admin-login'>Admin</Link></li>
      </ul>
    </nav>
  );
}