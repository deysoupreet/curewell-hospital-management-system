import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

function UpdateDoctor() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [doctor, setDoctor] = useState({
    firstName: "",
    lastName: "",
    emailId: ""
  });

  useEffect(() => {
    axios.get(`http://localhost:8080/doctors/${id}`)
      .then(res => setDoctor(res.data))
      .catch(err => console.error(err));
  }, [id]);

  const handleChange = (e) => {
    setDoctor({
      ...doctor,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios.put(`http://localhost:8080/doctors/${id}`, doctor)
      .then(() => {
        alert("Doctor updated successfully");
        navigate("/");
      })
      .catch(err => console.error(err));
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Update Doctor</h2>

      <form onSubmit={handleSubmit}>
        <input
          name="firstName"
          value={doctor.firstName}
          onChange={handleChange}
          placeholder="First Name"
          required
        />

        <input
          name="lastName"
          value={doctor.lastName}
          onChange={handleChange}
          placeholder="Last Name"
          required
        />

        <input
          name="emailId"
          value={doctor.emailId}
          onChange={handleChange}
          placeholder="Email"
          required
        />

        <button type="submit">Update</button>
      </form>
    </div>
  );
}

export default UpdateDoctor;