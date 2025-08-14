import React, { useEffect, useMemo, useState } from "react";
import { listGuests } from "../service/GuestService";
import { listReservations } from "../service/ReservationService";

export default function AdminDashboard() {
  const [guests, setGuests] = useState([]);
  const [reservations, setReservations] = useState([]);
  const [err, setErr] = useState("");

  useEffect(() => {
    Promise.all([listGuests(), listReservations()])
      .then(([g, r]) => { setGuests(g || []); setReservations(r || []); })
      .catch(e => { console.error(e); setErr("Could not load reservations."); });
  }, []);

  const gByEmail = useMemo(() => new Map(guests.map(g => [g.email, g])), [guests]);
  const fmtDate = d => (d ? new Date(d).toLocaleDateString() : "");
  const fmtMoney = n => (n == null ? "" : Number(n).toLocaleString(undefined, { style: "currency", currency: "USD" }));

  const styles = {
    page: { maxWidth: 960, margin: "2rem auto", padding: "0 1rem" },
    headerRow: { display: "flex", justifyContent: "space-between", alignItems: "center" },
    btn: { padding: "8px 12px", borderRadius: 6, border: "none", cursor: "pointer" },
    add: { background: "#2563eb", color: "#fff", fontWeight: 600 },
    hi: { margin: "0.5rem 0 0.75rem" },
    tableWrap: { overflowX: "auto", marginTop: 8 },
    table: { width: "100%", borderCollapse: "collapse", background: "#fff" },
    thtd: { padding: "10px 12px", borderBottom: "1px solid #e5e7eb", whiteSpace: "nowrap", textAlign: "left" },
    thead: { background: "#f8fafc" },
    error: { color: "#b91c1c", marginTop: 8 },
    logout: { marginTop: 16 }
  };

  const logout = () => { localStorage.removeItem("admin"); window.location.href = "/admin-login"; };

  return (
    <div style={styles.page}>
      <div style={styles.headerRow}>
        <h2 style={{ margin: 0 }}>Admin Dashboard</h2>
        <button style={{ ...styles.btn, ...styles.add }} onClick={() => (window.location.href = "/add-room")}>Add Room</button>
      </div>

      <p style={styles.hi}>Welcome Admin</p>

      {/* reservations table */}
      <div style={styles.tableWrap}>
        {err ? (
          <div style={styles.error}>{err}</div>
        ) : (
          <table style={styles.table}>
            <thead style={styles.thead}>
              <tr>
                <th style={styles.thtd}>resID</th>
                <th style={styles.thtd}>guestName</th>
                <th style={styles.thtd}>guest email</th>
                <th style={styles.thtd}>phone</th>
                <th style={styles.thtd}>roomNum</th>
                <th style={styles.thtd}>dates</th>
                <th style={styles.thtd}>cost</th>
                <th style={styles.thtd}>status</th>
              </tr>
            </thead>
            <tbody>
              {reservations.length ? reservations.map(r => {
                const g = gByEmail.get(r.guestEmail) || {};
                return (
                  <tr key={r.reservationId}>
                    <td style={styles.thtd}>{r.reservationId}</td>
                    <td style={styles.thtd}>{g.name || `${g.firstName || ""} ${g.lastName || ""}`.trim()}</td>
                    <td style={styles.thtd}>{r.guestEmail}</td>
                    <td style={styles.thtd}>{g.phone || ""}</td>
                    <td style={styles.thtd}>{r.roomNumber}</td>
                    <td style={styles.thtd}>{fmtDate(r.startDate)} — {fmtDate(r.endDate)}</td>
                    <td style={styles.thtd}>{fmtMoney(r.totalCost)}</td>
                    <td style={styles.thtd}>{r.status}</td>
                  </tr>
                );
              }) : (
                <tr><td style={styles.thtd} colSpan="8">No reservations found.</td></tr>
              )}
            </tbody>
          </table>
        )}
      </div>

      <div style={styles.logout}>
        <button style={styles.btn} onClick={logout}>Logout</button>
      </div>
    </div>
  );
}
