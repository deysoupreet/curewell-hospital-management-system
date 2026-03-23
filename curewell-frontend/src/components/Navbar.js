import React from "react";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <div style={styles.navbar}>
      <h2 style={styles.logo}>CureWell</h2>

      <div style={styles.links}>
        <Link to="/" style={styles.link}>View Doctors</Link>
        <Link to="/specializations" style={styles.link}>View Specializations</Link>
        <Link to="/today-surgeries" style={styles.link}>View Today's Surgery</Link>
        <Link to="/add-doctor" style={styles.link}>Add Doctor</Link>
      </div>
    </div>
  );
}

const styles = {
  navbar: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    backgroundColor: "black",
    color: "white",
    padding: "10px 20px",
  },
  logo: {
    margin: 0,
  },
  links: {
    display: "flex",
    gap: "20px",
  },
  link: {
    color: "white",
    textDecoration: "none",
  },
};

export default Navbar;