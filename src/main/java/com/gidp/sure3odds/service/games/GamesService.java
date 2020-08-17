package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.*;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.NewGameAndPrediction;
import com.gidp.sure3odds.repository.games.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GamesService {

    @Autowired
    GamesRepository gamesRepository;

    @Autowired
    LeaguesRepository leaguesRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    SetsRepository setsRepository;

    @Autowired
    CountriesRepository countriesRepository;

    @Autowired
    PredictionsRepository predictionsRepository;


    public BaseResponse addGame(NewGameAndPrediction newGameAndPrediction) {
        BaseResponse response = new BaseResponse();
        Long countryid = newGameAndPrediction.getCountryID().getId();
        Optional<Countries> countries = countriesRepository.findById(countryid);
        if (countries.isPresent()) {
            Long leagueid = newGameAndPrediction.getLeagueID().getId();
            Optional<Leagues> league = leaguesRepository.findById(leagueid);
            if (league.isPresent()) {
                Long hometeamid = newGameAndPrediction.getHomeTeamID().getId();
                Optional<Teams> hometeam = teamsRepository.findById(hometeamid);
                if (hometeam.isPresent()) {
                    Long awayteamid = newGameAndPrediction.getAwayTeamID().getId();
                    Optional<Teams> awayteam = teamsRepository.findById(awayteamid);
                    if (awayteam.isPresent()) {
                        Long setid = newGameAndPrediction.getSetID().getId();
                        Optional<Sets> sets = setsRepository.findById(setid);
                        if (sets.isPresent()) {
                            Games newGame = new Games(0, 0, newGameAndPrediction.getPrediction(), newGameAndPrediction.getOdds(),
                            newGameAndPrediction.getConfidenceLevel(), "Not Started", newGameAndPrediction.getMatchDate(), newGameAndPrediction.getMatchTime());
                            newGame.setAwayTeamID(awayteam.get());
                            newGame.setHomeTeamID(hometeam.get());
                            newGame.setLeagueID(league.get());
                            newGame.setSetID(sets.get());
                            newGame.setCountryID(countries.get());
                            Games savedGame= gamesRepository.save(newGame);
                            response.setData(savedGame);
                            response.setDescription("Game created successfully");
                            response.setStatusCode(HttpServletResponse.SC_OK);
                        } else {
                            response.setDescription("Please, select a set to add the game to.");
                            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    } else {
                        response.setDescription("Please, select an Away Team.");
                        response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } else {
                    response.setDescription("Please, select a Home Team.");
                    response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                response.setDescription("Please, select a League.");
                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setDescription("Please, select a Country.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }

        return response;
    }


    public BaseResponse CreateGameFromPrediction(long predictionID, long setID) {
        BaseResponse response = new BaseResponse();

        Optional<Predictions> prediction = predictionsRepository.findById(predictionID);
        if(prediction.isPresent()){
            Optional<Sets> set = setsRepository.findById(setID);
            if(set.isPresent()){
                Games newGame = new Games(0, 0, prediction.get().getPrediction(), prediction.get().getOdds(),
                        prediction.get().getConfidenceLevel(), "Not Started", prediction.get().getMatchDate(), prediction.get().getMatchTime());

                newGame.setAwayTeamID(prediction.get().getAwayTeamID());
                newGame.setHomeTeamID(prediction.get().getHomeTeamID());
                newGame.setLeagueID(prediction.get().getLeagueID());
                newGame.setSetID(set.get());
                newGame.setCountryID(prediction.get().getCountryID());
                Games savedGame= gamesRepository.save(newGame);
                response.setData(savedGame);
                response.setDescription("Games has been updated succesfully.");
                response.setStatusCode(HttpServletResponse.SC_OK);
            }else{
                response.setDescription("Please, select a set to add the game to.");
                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            }
        }else{
            response.setDescription("Please, select a prediction to add the game to.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

//    public BaseResponse getUserGames(long userID, Date matchDate) {
//        BaseResponse response = new BaseResponse();
//
//
//        Optional<Predictions> prediction = predictionsRepository.findById(predictionID);
//        if(prediction.isPresent()){
//            Optional<Sets> set = setsRepository.findById(setID);
//            if(set.isPresent()){
//                Games newGame = new Games(0, 0, prediction.get().getPrediction(), prediction.get().getOdds(),
//                        prediction.get().getConfidenceLevel(), "Not Started", prediction.get().getMatchDate(), prediction.get().getMatchTime());
//
//                newGame.setAwayTeamID(prediction.get().getAwayTeamID());
//                newGame.setHomeTeamID(prediction.get().getHomeTeamID());
//                newGame.setLeagueID(prediction.get().getLeagueID());
//                newGame.setSetID(set.get());
//                newGame.setCountryID(prediction.get().getCountryID());
//                Games savedGame= gamesRepository.save(newGame);
//                response.setData(savedGame);
//                response.setDescription("Games has been updated succesfully.");
//                response.setStatusCode(HttpServletResponse.SC_OK);
//            }else{
//                response.setDescription("Please, select a set to add the game to.");
//                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
//            }
//        }else{
//            response.setDescription("Please, select a prediction to add the game to.");
//            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
//        }
//        return response;
//    }





    public BaseResponse UpdateGames(Games games) {
        BaseResponse response = new BaseResponse();
        Games updateGames = gamesRepository.save(games);
        if (updateGames != null) {
            response.setData(updateGames);
            response.setDescription("Games has been updated succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("Games was not updated.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

    public BaseResponse GetAllGamesByDate(Date matchDate) {
        BaseResponse response = new BaseResponse();
        List<Games> games = gamesRepository.findGamesByMatchDate(matchDate);
        ArrayList<Object> data = new ArrayList<>();
        if (!games.isEmpty()) {
            response.setData(games);
            response.setDescription("Games found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse GetGameByID(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<Games> game = gamesRepository.findById(id);
        if (game.isPresent()) {
            response.setData(game);
            response.setDescription("Game found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }


}
