package com.gidp.sure3odds.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.Games;
import com.gidp.sure3odds.entity.Predictions;

@RequestMapping("/sure3odds")
@RestController
public class GamesController {

	@PostMapping(value = "/add_prediction")
	ResponseEntity<?> addPrediction(@RequestParam Predictions predictions) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/update_prediction")
	ResponseEntity<?> updatePrediction(@RequestParam Predictions permissions) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/add_game")
	ResponseEntity<?> addGame(@RequestParam Games games) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/update_game")
	ResponseEntity<?> updateGame(@RequestParam Games games) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/create_game")
	ResponseEntity<?> createGame(@RequestParam Long PredictionID, Long SetID) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/add_comment")
	ResponseEntity<?> addComment(@RequestParam Long UserID, Long GameID, String comments) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/add_game_vote")
	ResponseEntity<?> addGameVote(@RequestParam Long UserID, Long GameID, Long UserVote, Long HomeVote, Long AwayVote,
			Long DrawVote) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_predictions")
	ResponseEntity<?> getPredictions() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_games_by_userid/{userid}")
	ResponseEntity<?> getGamesByUserID(@RequestParam Long UserID) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_games_by_date")
	ResponseEntity<?> getGamesByDate(@RequestParam Long UserID, @RequestParam Date MatchDate) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_games_settings")
	ResponseEntity<?> getGamesSettings() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_game_votes")
	ResponseEntity<?> getGameVotes(@RequestParam Long UserID, @RequestParam Long GameID) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/delete_option/")
	ResponseEntity<?> deleteOption(@RequestParam Long OptionID, String OptionText) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_comments")
	ResponseEntity<?> getComments(@RequestParam Long GameID) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
