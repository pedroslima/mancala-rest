import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import GameSelected from "./components/Game";
import Matches from "./components/Matches";

export default function App() {
  return (
    <Router>
      <nav style={{ margin: 10 }}>
        <Link to="/" style={{ padding: 5 }}>
          Home
        </Link>
      </nav>

      <Routes>
        <Route path="/" element={<Matches />} />
        <Route path="/game/:gameId" element={<GameSelected />} />
      </Routes>
    </Router>
  );
}
