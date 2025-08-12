// src/components/AdminLogin.jsx
import { useState } from "react";
import axios from "axios";

export default function AdminLogin() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [msg, setMsg] = useState("");
  const [loading, setLoading] = useState(false); // NEW

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMsg("");
    setLoading(true); // NEW
    try {
      const res = await axios.post("http://localhost:8080/api/admin/login", {
        email,
        password,
      });

      // Save minimal admin info (no password) — NEW
      localStorage.setItem("admin", JSON.stringify(res.data));

      setMsg(`Welcome ${res.data.name}!`);

      // Redirect to admin dashboard — NEW
      window.location.href = "/admin";
    } catch (err) {
      setMsg("Invalid email or password");
    } finally {
      setLoading(false); // NEW
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      style={{ maxWidth: 360, margin: "2rem auto", display: "grid", gap: 12 }}
    >
      <h2>Admin Login</h2>

      <input
        type="email"
        placeholder="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      />

      <input
        type="password"
        placeholder="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required
      />

      <button type="submit" disabled={loading}>
        {loading ? "Logging in…" : "Log In"}
      </button>

      {msg && <p>{msg}</p>}
    </form>
  );
}
