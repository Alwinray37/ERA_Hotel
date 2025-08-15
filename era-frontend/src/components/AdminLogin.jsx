import React, { useState, useEffect } from 'react';
import { getAdminByEmail } from '../service/AdminService';
import '../App.css'; // Keep global styles
import { useNavigate } from 'react-router-dom';

export default function AdminLogin() {
    const navigate = useNavigate();
    const [error, setError] = useState('');

    // data from the form are saved here 
    // updated by handleChange function const [formData, setFormData] = useState({ email: "", password: "" }); 
    const [formData, setFormData] = useState({
        email: "",
        password: ""
    });

     // Check localStorage for admin on mount
    useEffect(() => {
        const storedAdmin = localStorage.getItem("admin");
        if (storedAdmin) {
            // Optionally, you can parse and set formData if you want to pre-fill the form
            // const adminObj = JSON.parse(storedAdmin);
            // setFormData({ email: adminObj.email, password: adminObj.password });
            navigate("/admin-dashboard");
        }
    }, [navigate]);
    
    // function to handle login button const handleSubmit = async (e) => { e.preventDefault(); 
    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(""); 
  
        // get admin email
        const admin = await getAdminByEmail(formData.email);
        if (!admin) {
            console.log("admin not found");
            setError("Admin not found");
        } else {
            console.log("admin found: ", admin);
            if (formData.email === admin.email && formData.password === admin.password) {
                console.log("admin info: ", admin);
                // Save to localStorage so dashboard can read it
                localStorage.setItem("admin", JSON.stringify(admin));
                navigate("/admin-dashboard");
            } else {
                alert("Email and Password do not match");
            }
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    return (
        <div className="admin-login-page">
            <form onSubmit={handleSubmit} className="container d-flex flex-column gap-2 shadow p-4 w-50 bg-white rounded">
                <h3>Admin Login</h3>

                <label>Email:</label>
                <input
                    className="form-control"
                    placeholder="Enter Email"
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                />

                <label>Password:</label>
                <input
                    className="form-control"
                    placeholder="Enter Password"
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                />

                <button className="btn btn-primary mb-2" type="submit">Login</button>
                {error && <div className="alert alert-danger">{error}</div>}
            </form>
        </div>
    );
}
