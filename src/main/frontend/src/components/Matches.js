import React, { useState, useEffect } from "react";
import MatchService from "../services/MatchService";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

export default function Matches() {
  const [matches, setMatches] = useState([]);

  useEffect(() => {
    getMatches();
  }, []);

  const getMatches = () => {
    MatchService.getMatches().then((response) => {
      setMatches(response.data);
    });
  };

  function newMatch() {
    console.log("New match");
    MatchService.newMatch().then(() => {
      getMatches();
    });
  }

  return (
    <div className="container">
      <h1 className="text-center">Matches list</h1>
      <button className="btn btn-outline-primary" onClick={() => newMatch()}>
        New Match
      </button>

      <table className="table table-striped">
        <thead>
          <tr>
            <th>Game Status</th>
            <th>Created</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {matches.map((match) => (
            <tr key={match.gameId}>
              <td>{match.gameStatus}</td>
              <td>
                <StartTimeComponent time={match.startTime} />
              </td>
              <td>
                <Link to={`/game/${match.gameId}`}>Join</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

const StartTimeComponent = ({ time }) => {
  const d = new Date(time);

  let dia = new Intl.DateTimeFormat("en-GB", {
    year: "numeric",
    month: "long",
    day: "2-digit",
    hour: "numeric",
    minute: "numeric",
  }).format(d);
  return <>{dia}</>;
};
