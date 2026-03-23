import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import ViewDoctors from "./components/ViewDoctors";
import AddDoctor from "./components/AddDoctor";
import ViewSpecializations from "./components/ViewSpecializations";
import ViewTodaySurgeries from "./components/ViewTodaySurgeries";

function App() {
  return (
    <Router>
      <Navbar />

      <Routes>
        <Route path="/" element={<ViewDoctors />} />
        <Route path="/add-doctor" element={<AddDoctor />} />
        <Route path="/specializations" element={<ViewSpecializations />} />
        <Route path="/today-surgeries" element={<ViewTodaySurgeries />} />
      </Routes>
    </Router>
  );
}

export default App;