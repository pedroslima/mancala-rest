import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import MatchService from "../services/MatchService";

export default function GameSelected() {
  let params = useParams();

  const [match, setMatch] = useState([]);

  useEffect(() => {
    getMatch();
  }, []);

  const getMatch = () => {
    MatchService.getMatch(params.gameId).then((response) => {
      setMatch(response.data);
      console.log(response.data);
    });
  };

  const moveFrom = (index) => {
    MatchService.moveFrom(params.gameId, match.player, index).then(() => {
      getMatch();
    });
  };

  function move(index) {
    moveFrom(index);
  }

  return (
    <div className="container">
      <ScoreComponent player1={match.mancalaP1} player2={match.mancalaP2} />
      <WinnerComponent player1={match.mancalaP1} player2={match.mancalaP2} />
      {match.boardStatus ? (
        <table className="table table-bordered">
          <tbody>
            <tr>
              <td>
                <TurnComponet player={match.player} turn="PLAYER_2" />
              </td>
              <td rowSpan={2} className="table-info">
                {match.boardStatus[13]}
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="12"
                  value={match.boardStatus[12]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="11"
                  value={match.boardStatus[11]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="10"
                  value={match.boardStatus[10]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="9"
                  value={match.boardStatus[9]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="8"
                  value={match.boardStatus[8]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="7"
                  value={match.boardStatus[7]}
                />
              </td>
              <td rowSpan={2} className="table-info">
                {match.boardStatus[6]}
              </td>
            </tr>
            <tr>
              <td>
                <TurnComponet player={match.player} turn="PLAYER_1" />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="0"
                  value={match.boardStatus[0]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="1"
                  value={match.boardStatus[1]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="2"
                  value={match.boardStatus[2]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="3"
                  value={match.boardStatus[3]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="4"
                  value={match.boardStatus[4]}
                />
              </td>
              <td>
                <MancalaComponent
                  handleClick={move}
                  idx="5"
                  value={match.boardStatus[5]}
                />
              </td>
            </tr>
          </tbody>
        </table>
      ) : null}
    </div>
  );
}

const MancalaComponent = ({ idx, value, handleClick }) => {
  return (
    <>
      <button
        className="btn btn-outline-primary btn-block"
        onClick={() => handleClick(idx)}
      >
        {value}
      </button>
    </>
  );
};

const TurnComponet = ({ player, turn }) => {
  return <>{player == turn ? "🎮" : ""}</>;
};

const ScoreComponent = ({ player1, player2 }) => {
  return (
    <table className="table">
      <thead>
        <tr>
          <th>Player 1</th>
          <th>Player 2</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>{player1}</td>
          <td>{player2}</td>
        </tr>
      </tbody>
    </table>
  );
};

const WinnerComponent = ({ player1, player2 }) => {
  const collectedSeeds = player1 + player2 == 48;

  let winner;
  if (collectedSeeds) {
    winner = player1 > player2 ? "Player 1 Won" : "Player 2 Won";
  }

  return (
    <>
      {collectedSeeds && (
        <div class="alert alert-warning" role="alert">
          {winner}
        </div>
      )}
    </>
  );
};
