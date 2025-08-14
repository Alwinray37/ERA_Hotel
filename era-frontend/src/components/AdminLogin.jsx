import React, { useState, useEffect } from 'react';
import { getAdminByEmail, listAdmins } from '../service/AdminService';
import '../styles/ViewReservations.css'; // same style as ViewReservations
import { useNavigate } from 'react-router-dom';

export default function AdminLogin() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: "",
        password: ""
    });
    const [admins, setAdmins] = useState([]);
    const [admin, setAdmin] = useState(null);
    useEffect( () => {
        listAdmins()
            .then(data => {
                setAdmins(data);
                console.log("Admins: ", admins);
            })
            .catch( err => {
                console.log("Error fetching admins from db" );
            })
        }, []);


    const handleSubmit = async (e) => {
        e.preventDefault();
  
        // get admin email
        const admin = await getAdminByEmail(formData.email);
        if(!admin){
            console.log("admin not found");
        } else {
            console.log("admin found: ", admin);
            if(formData.email === admin.email && formData.password === admin.password)  {
                console.log("admin info: ", admin);
                alert("Match");
            }  else {
                alert("no match")
            }
        }
    };


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    return (
        <div className="content">
            <form onSubmit={handleSubmit} className="container d-flex flex-column gap-2 shadow p-4">
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
            </form>
        </div>
    );
}