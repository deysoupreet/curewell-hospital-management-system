import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function AddDoctor() {
  const navigate = useNavigate();

  const [doctor, setDoctor] = useState({
    firstName: "",
    lastName: "",
    emailId: ""
  });

  const handleChange = (e) => {
    setDoctor({
      ...doctor,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios.post("http://localhost:8080/doctors", doctor)
      .then(() => {
        alert("Doctor added successfully");
        navigate("/");
      })
      .catch(err => console.error(err));
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Add Doctor</h2>

      <form onSubmit={handleSubmit}>
        <input
          name="firstName"
          placeholder="First Name"
          value={doctor.firstName}
          onChange={handleChange}
          required
        />

        <input
          name="lastName"
          placeholder="Last Name"
          value={doctor.lastName}
          onChange={handleChange}
          required
        />

        <input
          name="emailId"
          placeholder="Email"
          value={doctor.emailId}
          onChange={handleChange}
          required
        />

        <button type="submit">Add Doctor</button>
      </form>
    </div>
  );
}

export default AddDoctor;