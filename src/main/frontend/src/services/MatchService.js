import axios from "axios";

const MATCH_REST_API_URL = "/match";

class MatchService {
  newMatch() {
    return axios.post(MATCH_REST_API_URL);
  }

  getMatches() {
    return axios.get(MATCH_REST_API_URL);
  }

  getMatch(gameId) {
    return axios.get(MATCH_REST_API_URL + "/" + gameId);
  }

  moveFrom(gameId, player, index) {
    return axios.post(MATCH_REST_API_URL + "/" + gameId, {
      player: player,
      index: index,
    });
  }
}

export default new MatchService();
