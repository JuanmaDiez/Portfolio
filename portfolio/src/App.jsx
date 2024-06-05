import "./App.css";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import PersonalInfo from "./pages/PersonalInfo";
import AllProjects from "./pages/AllProjects";
import Project from "./pages/Project";

function App() {
  return (
    <section className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/personal" element={<PersonalInfo />} />
        <Route path="/projects" element={<AllProjects />} />
        <Route path="/project/:id" element={<Project />} />
      </Routes>
    </section>
  );
}

export default App;
