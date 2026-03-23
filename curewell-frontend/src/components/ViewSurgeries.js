import React from "react";

function ViewSurgeries({ surgeries }) {
  return (
    <div>
      <h2>Surgeries</h2>

      {surgeries.length === 0 ? (
        <p>No surgeries found</p>
      ) : (
        <table border="1">
          <thead>
            <tr>
              <th>ID</th>
              <th>Doctor</th>
              <th>Assistants</th>
              <th>Specialization</th>
              <th>Date</th>
              <th>Start</th>
              <th>End</th>
            </tr>
          </thead>
          <tbody>
            {surgeries.map((surgery) => (
              <tr key={surgery.id}>
                <td>{surgery.id}</td>
                <td>{surgery.doctorName}</td>
                <td>
                  {surgery.assistingDoctorNames
                    ? surgery.assistingDoctorNames.join(", ")
                    : "None"}
                </td>
                <td>{surgery.specialization}</td>
                <td>{surgery.date}</td>
                <td>{surgery.startTime}</td>
                <td>{surgery.endTime}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default ViewSurgeries;