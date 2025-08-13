export default function AdminDashboard() {
  const admin = JSON.parse(localStorage.getItem("admin") || "{}");

  const logout = () => {
    localStorage.removeItem("admin");
    window.location.href = "/admin-login";
  };

  return (
      <div className ="content">
    <div style={{ maxWidth: 720, margin: "2rem auto" }}>
      <h2>Admin Dashboard</h2>
      <p>Welcome, {admin?.name} ({admin?.email})</p>
      <button onClick={logout}>Logout</button>
    </div>
    </div>
  );
}