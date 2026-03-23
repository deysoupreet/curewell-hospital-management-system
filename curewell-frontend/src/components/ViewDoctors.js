import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function ViewDoctors() {
  const [doctors, setDoctors] = useState([]);

  const fetchDoctors = () => {
    axios
      .get("http://localhost:8080/doctors")
      .then((res) => setDoctors(res.data))
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    fetchDoctors();
  }, []);

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:8080/doctors/${id}`)
      .then(() => {
        alert("Doctor deleted");
        fetchDoctors(); // refresh table
      })
      .catch((err) => {
        alert(err.response.data);
      });
  };

  const navigate = useNavigate();

  return (
    <div style={{ padding: "20px" }}>
      <h2>Doctors</h2>

      {doctors.length === 0 ? (
        <p>No doctors found</p>
      ) : (
        <table border="1" cellPadding="10">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {doctors.map((doc) => (
              <tr key={doc.id}>
                <td>{doc.id}</td>
                <td>
                  {doc.firstName} {doc.lastName}
                </td>
                <td>{doc.emailId}</td>

                <td>
                  <button onClick={() => navigate(`/update-doctor/${doc.id}`)}>
                    Edit
                  </button>
                  <button onClick={() => handleDelete(doc.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default ViewDoctors;
