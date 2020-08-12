package com.gidp.sure3odds.controller;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.Games;
import com.gidp.sure3odds.entity.Predictions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/sure3odds")
@RestController
public class GamesController {

	@PostMapping(value = "/games/prediction/create")
	ResponseEntity<?> CreatePrediction(@RequestParam Predictions predictions) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/games/prediction/update")
	ResponseEntity<?> updatePrediction(@RequestParam Predictions permissions) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/games/game/add")
	ResponseEntity<?> addGame(@RequestParam Games games) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/games/game/update")
	ResponseEntity<?> updateGame(@RequestParam Games games) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/games/game/create")//convert the prediction into a game
	ResponseEntity<?> createGame(@RequestParam Long PredictionID, Long SetID) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/games/comment/create")
	ResponseEntity<?> addComment(@RequestParam Long UserID, Long GameID, String comments) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/games/vote/create")
	ResponseEntity<?> addGameVote(@RequestParam Long UserID, Long GameID, Long UserVote, Long HomeVote, Long AwayVote,
			Long DrawVote) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/games/prediction/getall")
	ResponseEntity<?> getPredictions() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/games/prediction/get_by_id/{id}")
	ResponseEntity<?> getPredictionsByID() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/games/game/get_by_userid/{id}")
	ResponseEntity<?> getGamesByUserID(@RequestParam Long UserID) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/games/game/get_by_date")
	ResponseEntity<?> getGamesByDate(@RequestParam Long UserID, @RequestParam Date MatchDate) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/games/game/get_settings")
	ResponseEntity<?> getGamesSettings() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/games/vote/get_game_votes")
	ResponseEntity<?> getGameVotes(@RequestParam Long UserID, @RequestParam Long GameID) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/games/delete_option/")
	ResponseEntity<?> deleteOption(@RequestParam Long OptionID, String OptionText) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/games/comment/get_game_comments")
	ResponseEntity<?> getComments(@RequestParam Long GameID) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
