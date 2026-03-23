import React, { useEffect, useState } from "react";
import axios from "axios";

function ViewSpecializations() {
  const [specializations, setSpecializations] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/specializations")
      .then(res => setSpecializations(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h2>Specializations</h2>

      {specializations.length === 0 ? (
        <p>No specializations found</p>
      ) : (
        <table border="1" cellPadding="10">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
            </tr>
          </thead>

          <tbody>
            {specializations.map(spec => (
              <tr key={spec.id}>
                <td>{spec.id}</td>
                <td>{spec.name}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default ViewSpecializations;