export default function AdminDashboard() {
  const admin = JSON.parse(localStorage.getItem("admin") || "{}");

  const logout = () => {
    localStorage.removeItem("admin");
    window.location.href = "/admin-login";
  };

  return (
    <div style={{ maxWidth: 720, margin: "2rem auto" }}>
      <h2>Admin Dashboard</h2>
      <p>
        Signed in as: <b>{admin?.name} ({admin?.email})</b>
      </p>
      <button onClick={logout}>Logout</button>

      {/* Future: add reservations list here */}
    </div>
  );
}
