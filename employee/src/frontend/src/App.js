import {useState, useEffect} from "react";
import './App.css';
import {getAllStudents} from "./client";
function App() {
    getAllStudents().then(data => console.log)
    return <p>hello react</p>;
}

export default App;
