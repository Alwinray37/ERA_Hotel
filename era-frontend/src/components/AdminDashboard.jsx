import React, { useEffect, useMemo, useState } from "react";
import { listGuests } from "../service/GuestService";
import { listReservations } from "../service/ReservationService";
import { useNavigate } from 'react-router-dom'

export default function AdminDashboard() {
  const [guests, setGuests] = useState([]);
  const [reservations, setReservations] = useState([]);
  const [err, setErr] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    Promise.all([listGuests(), listReservations()])
      .then(([g, r]) => { setGuests(g || []); setReservations(r || []); })
      .catch(e => { console.error(e); setErr("Could not load reservations."); });
  }, []);

  const gByEmail = useMemo(() => new Map(guests.map(g => [g.email, g])), [guests]);
  const fmtDate = d => (d ? new Date(d).toLocaleDateString() : "");
  const fmtMoney = n => (n == null ? "" : Number(n).toLocaleString(undefined, { style: "currency", currency: "USD" }));

  const logout = () => { localStorage.removeItem("admin"); window.location.href = "/admin-login"; };

  return (
    <div className="content my-4">
      <div className="container">
        <div className="d-flex justify-content-between align-items-center mb-3">
        <h2 className="mb-0">Admin Dashboard</h2>
        <button className="btn btn-primary fw-bold" onClick={() => (navigate('/add-room'))}>Add Room</button>
      </div>

      <p className="mb-3">Welcome Admin</p>

      {/* reservations table */}
      <div className="table-responsive">
        {err ? (
          <div className="alert alert-danger">{err}</div>
        ) : (
          <table className="table table-striped table-bordered align-middle">
            <thead className="table-primary">
              <tr>
                <th>Reservation Number</th>
                <th>Guest Name</th>
                <th>Guest Email</th>
                <th>Phone</th>
                <th>Room Number</th>
                <th>Dates</th>
                <th>Cost</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {reservations.length ? reservations.map(r => {
                const g = gByEmail.get(r.guestEmail) || {};
                return (
                  <tr key={r.reservationId}>
                    <td>{r.reservationId}</td>
                    <td>{g.name || `${g.firstName || ""} ${g.lastName || ""}`.trim()}</td>
                    <td>{r.guestEmail}</td>
                    <td>{g.phone || ""}</td>
                    <td>{r.roomNumber}</td>
                    <td>{fmtDate(r.startDate)} — {fmtDate(r.endDate)}</td>
                    <td>{fmtMoney(r.totalCost)}</td>
                    <td>{r.status}</td>
                  </tr>
                );
              }) : (
                <tr>
                  <td colSpan="8" className="text-center">No reservations found.</td>
                </tr>
              )}
            </tbody>
          </table>
        )}
      </div>

      <div className="mt-4">
        <button className="btn btn-secondary" onClick={logout}>Logout</button>
      </div>
      </div>
    </div>
  );
}
