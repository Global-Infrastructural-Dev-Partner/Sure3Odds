package com.gidp.sure3odds.controller.games;

import com.gidp.sure3odds.entity.games.*;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.games.NewGameAndPrediction;
import com.gidp.sure3odds.service.games.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/sure3odds")
@RestController

public class GamesController {

    @Autowired
    SetsService setsService;

    @Autowired
    LeaguesService leaguesService;

    @Autowired
    CountriesService countriesService;

    @Autowired
    TeamsService teamsService;

    @Autowired
    PredictionsService predictionsService;

    @Autowired
    GamesService gamesService;

    @Autowired
    CommentsService commentsService;

    @Autowired
    VotesService votesService;

    @Autowired
    SelectionsService selectionsService;

    @Autowired
    StatusService statusService;

    //-----------------Start-----Country--------------------------
    @PostMapping("/games/country/createall")
    ResponseEntity<?> createCountries(@RequestBody List<Countries> listContries) {
        BaseResponse response = countriesService.CreateAllCountry(listContries);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/games/country/create")
    ResponseEntity<?> createCountry(@RequestBody Countries countries) {
        BaseResponse response = countriesService.CreateCountry(countries);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/games/country/update")
    ResponseEntity<?> updateCountry(@RequestBody Countries countries) {
        BaseResponse response = countriesService.UpdateCountry(countries);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/country/getall")
    ResponseEntity<?> getAllCountries(@RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = countriesService.GetAllCountries(pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/country/get_all")
    ResponseEntity<?> getallcountries() {
        BaseResponse response = countriesService.GetCountries();
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/games/country/delete/{id}")
    ResponseEntity<?> deleteCountry(@PathVariable long id) {
        BaseResponse response = countriesService.DeleteCountry(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/country/search")
    ResponseEntity<?> searchCountryByName(@RequestParam String searchValue, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = countriesService.SearchCountries(searchValue, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/country/leagues/get/{id}")
    ResponseEntity<?> searchLeagueByCountryID(@PathVariable Long id) {
        BaseResponse response = leaguesService.GetLeagueByCountryID(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/country/leagues/search")
    ResponseEntity<?> searchLeaguesByCountryIDAndName(@RequestParam String searchValue, @RequestParam long countryId) {
        BaseResponse response = leaguesService.SearchLeagueByCountryIDAndName(countryId, searchValue);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    //-----------------End-----Country----------------------------------

    //-------------------Start-----Leagues-----------------------------

    @PostMapping("/games/league/createall")
    ResponseEntity<?> createLeagues(@RequestBody List<Leagues> listLeagues) {
        BaseResponse response = leaguesService.CreateAllLeagues(listLeagues);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/games/league/create")
    ResponseEntity<?> createLeague(@RequestBody Leagues leagues) {
        BaseResponse response = leaguesService.CreateLeague(leagues);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/games/league/update")
    ResponseEntity<?> updateLeague(@RequestBody Leagues leagues) {
        BaseResponse response = leaguesService.UpdateLeague(leagues);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/league/getall")
    ResponseEntity<?> getAllLeagues(@RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = leaguesService.GetAllLeagues(pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/league/search")
    ResponseEntity<?> searchLeagueByName(@RequestParam String searchValue, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = leaguesService.SearchLeaguesByName(searchValue, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/games/league/delete/{id}")
    ResponseEntity<?> deleteLeague(@PathVariable Long id) {
        BaseResponse response = leaguesService.DeleteLeague(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/league/teams/get")
    ResponseEntity<?> GetTeamsByLeagudId(@RequestParam long leagueId, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = teamsService.GetTeamsByLeagueID(leagueId, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/league/teams/search")
    ResponseEntity<?> searchTeamsByLeagueIdAndName(@RequestParam String searchValue, @RequestParam long leagueId, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = teamsService.SearchTeamsByLeagueIdAndName(leagueId, searchValue, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/league/teams/getall/{id}")
    ResponseEntity<?> getTeamsByLeagudId(@PathVariable long id) {
        BaseResponse response = teamsService.getTeamsByLeagueID(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


//----------------End------Leagues--------------------------

    //----------------Start------Teams--------------------------
    @PostMapping("/games/team/createall")
    ResponseEntity<?> createTeams(@RequestBody List<Teams> listTeams) {
        BaseResponse response = teamsService.CreateAllTeams(listTeams);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/games/team/create")
    ResponseEntity<?> createTeam(@RequestBody Teams teams) {
        BaseResponse response = teamsService.CreateTeam(teams);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/games/team/update")
    ResponseEntity<?> updateTeam(@RequestBody Teams teams) {
        BaseResponse response = teamsService.UpdateTeam(teams);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/team/getall")
    ResponseEntity<?> getAllTeams(@RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = teamsService.GetAllTeams(pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/team/search")
    ResponseEntity<?> searchTeamsByName(@RequestParam String searchValue, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = teamsService.SearchTeamsByName(searchValue, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/games/team/delete/{id}")
    ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        BaseResponse response = teamsService.DeleteTeam(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
//----------------End------Teams--------------------------


    //----------------Start------Selections--------------------------
    @PostMapping(value = "/games/selection/create")
    ResponseEntity<?> createSelection(@RequestBody Selections selections) {
        BaseResponse response = selectionsService.CreateSelection(selections);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/games/selection/update")
    ResponseEntity<?> updateSelection(@RequestBody Selections selections) {
        BaseResponse response = selectionsService.UpdateSelection(selections);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/selection/getall")
    ResponseEntity<?> GetAllSelections(@RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = selectionsService.GetAllSelections(pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/games/selection/delete/{id}")
    ResponseEntity<?> deleteSelection(@PathVariable Long id) {
        BaseResponse response = selectionsService.DeleteSelection(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/selection/search")
    ResponseEntity<?> selectionsService(@RequestParam String searchValue, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = selectionsService.SearchSelectionsByName(searchValue, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/games/selection/get_all")
    ResponseEntity<?> getAllSelections() {
        BaseResponse response = selectionsService.getAllSelections();
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

//----------------End------Selections--------------------------


    //----------------Start------Set--------------------------
    @PostMapping(value = "/games/set/create")
    ResponseEntity<?> createSet(@RequestBody Sets sets) {
        BaseResponse response = setsService.CreateSet(sets);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/games/set/update")
    ResponseEntity<?> updateSet(@RequestBody Sets sets) {
        BaseResponse response = setsService.UpdateSet(sets);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/set/getall")
    ResponseEntity<?> getAllSets() {
        BaseResponse response = setsService.GetAllSets();
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/set/get_by_id/{id}")
    ResponseEntity<?> getSetByID(@RequestParam Long id) {
        BaseResponse response = setsService.GetSetByID(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/games/set/delete/{id}")
    ResponseEntity<?> deleteSet(@RequestParam Long id) {
        BaseResponse response = setsService.DeleteSet(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //----------------End------Set--------------------------

    //----------------Start------Prediction--------------------------
    @PostMapping(value = "/games/prediction/create")
    ResponseEntity<?> createPrediction(@RequestAttribute("UserID") Long userid, @RequestBody NewGameAndPrediction newGameAndPrediction) {
        BaseResponse response = predictionsService.CreatePrediction(userid, newGameAndPrediction);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/games/prediction/update")
    ResponseEntity<?> updatePrediction(@RequestBody Predictions predictions) {
        BaseResponse response = predictionsService.UpdatePrediction(predictions);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/prediction/getall")
    ResponseEntity<?> getAllPredictions() {
        BaseResponse response = predictionsService.GetAllPredictions();
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/prediction/get_by_id/{id}")
    ResponseEntity<?> getPredictionByID(@RequestParam long id) {
        BaseResponse response = predictionsService.GetPredictionByID(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/prediction/get_by_date/")
    ResponseEntity<?> getAllPredictionByDate(@RequestAttribute("UserID") Long UserID, @RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) LocalDate matchDate) {
        BaseResponse response = predictionsService.GetPredictionByDateAndUserID(matchDate, UserID);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/games/prediction/delete/{id}")
    ResponseEntity<?> deletePrediction(@RequestParam Long id) {
        BaseResponse response = predictionsService.DeletePrediction(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    //----------------End------Prediction--------------------------


    //----------------Start------Game--------------------------
    @PostMapping(value = "/games/game/add")
    ResponseEntity<?> addGame(@RequestBody NewGameAndPrediction newGameAndPrediction) {
        BaseResponse response = gamesService.addGame(newGameAndPrediction);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/games/game/update")
    ResponseEntity<?> updateGame(@RequestBody Games games) {
        BaseResponse response = gamesService.UpdateGames(games);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/games/game/create")
        //convert the prediction into a game
    ResponseEntity<?> createGame(@RequestParam Long PredictionID, Long SetID) {
        BaseResponse response = gamesService.CreateGameFromPrediction(PredictionID, SetID);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/games/game/delete/{id}")
    ResponseEntity<?> deleteGame(@PathVariable Long id) {
        BaseResponse response = gamesService.DeleteGame(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/games/game/user/get")
    ResponseEntity<?> getUserGames(@RequestAttribute("UserID") Long UserID, @RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) LocalDate matchDate) {
        BaseResponse response = gamesService.GetUserGames(UserID, matchDate);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    //----------------End------Game--------------------------

    //----------------Start------Comment--------------------------
    @PostMapping(value = "/games/comment/create")
    ResponseEntity<?> addComment(@RequestAttribute("UserID") Long UserID, @RequestBody Comments comments) {
        BaseResponse response = commentsService.CreateComments(comments, UserID);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/comment/get_game_comments/{id}")
    ResponseEntity<?> getComments(@PathVariable Long id) {
        BaseResponse response = commentsService.GetCommentsByGameID(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    //----------------End------Comment--------------------------

    //----------------Start------Vote--------------------------

    @PostMapping(value = "/games/vote/create")
    ResponseEntity<?> addGameVote(@RequestBody Votes votes, @RequestAttribute("UserID") Long UserID) {
        BaseResponse response = votesService.CreateVotes(votes, UserID);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/vote/get_game_votes/{id}")
    ResponseEntity<?> getGameVotes(@PathVariable Long id, @RequestAttribute("UserID") Long UserID) {
        BaseResponse response = votesService.GetVotesByGameIDAndUserID(id, UserID);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/games/vote/getall")
    ResponseEntity<?> getGameVotes() {
        BaseResponse response = votesService.GetVotes();
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    //----------------End------Vote--------------------------

    @PostMapping(value = "/games/status/create")
    ResponseEntity<?> createStatus(@RequestBody Status status) {
        BaseResponse response = statusService.CreateStatus(status);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping(value = "/games/selection/update")
//    ResponseEntity<?> updateSelection(@RequestBody Selections selections) {
//        BaseResponse response = selectionsService.UpdateSelection(selections);
//        if (response.getStatusCode() == 200) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
//
    @GetMapping(value = "/games/status/type/get")
    ResponseEntity<?> GetAllStatusesByType(@RequestParam String statusType) {
        BaseResponse response = statusService.GetAllStatuses(statusType);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
//
//    @DeleteMapping(value = "/games/selection/delete/{id}")
//    ResponseEntity<?> deleteSelection(@PathVariable Long id) {
//        BaseResponse response = selectionsService.DeleteSelection(id);
//        if (response.getStatusCode() == 200) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping(value = "/games/selection/search")
//    ResponseEntity<?> selectionsService(@RequestParam String searchValue, @RequestParam int pageNo, @RequestParam int pageSize) {
//        BaseResponse response = selectionsService.SearchSelectionsByName(searchValue, pageNo, pageSize);
//        if (response.getStatusCode() == 200) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
//    @GetMapping(value = "/games/selection/get_all")
//    ResponseEntity<?> getAllSelections() {
//        BaseResponse response = selectionsService.getAllSelections();
//        if (response.getStatusCode() == 200) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }


}
