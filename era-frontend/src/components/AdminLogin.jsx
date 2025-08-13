import { useState } from "react";
import axios from "axios";

export default function AdminLogin() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [msg, setMsg] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMsg("");
    try {
      const res = await axios.post("http://localhost:8080/api/admin/login", {
        email,
        password,
      });
      localStorage.setItem("admin", JSON.stringify(res.data)); // store admin info
      window.location.href = "/admin"; // go to dashboard
    } catch {
      setMsg("Invalid email or password");
    }
  };

  return (
  <div className ="content">
    <form onSubmit={handleSubmit} style={{ maxWidth: 360, margin: "2rem auto", display: "grid", gap: 12 }}>
      <h2>Admin Login</h2>
      <input type="email" placeholder="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
      <input type="password" placeholder="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
      <button type="submit">Log In</button>
      {msg && <p>{msg}</p>}
    </form>
  </div>
  );
}

