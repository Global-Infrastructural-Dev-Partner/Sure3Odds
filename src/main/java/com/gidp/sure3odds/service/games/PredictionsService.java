package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.*;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.NewGameAndPrediction;
import com.gidp.sure3odds.repository.games.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PredictionsService {

    @Autowired
    PredictionsRepository predictionsRepository;

    @Autowired
    CountriesRepository countriesRepository;

    @Autowired
    LeaguesRepository leaguesRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    SetsRepository setsRepository;


    public BaseResponse CreatePrediction(NewGameAndPrediction newGameAndPrediction) {
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
                            Predictions newPrediction = new Predictions(newGameAndPrediction.getMatchDate(), newGameAndPrediction.getMatchTime(), newGameAndPrediction.getPrediction(), newGameAndPrediction.getOdds(), "Pending", newGameAndPrediction.getConfidenceLevel());
                            newPrediction.setAwayTeamID(awayteam.get());
                            newPrediction.setHomeTeamID(hometeam.get());
                            newPrediction.setLeagueID(league.get());
                            newPrediction.setSetID(sets.get());
                            newPrediction.setCountryID(countries.get());
                            Predictions savedPrediction = predictionsRepository.save(newPrediction);
                            response.setData(savedPrediction);
                            response.setDescription("Prediction created successfully");
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


    public BaseResponse UpdatePrediction(Predictions predictions) {
        BaseResponse response = new BaseResponse();
        Predictions updated_prediction = predictionsRepository.save(predictions);
        if (updated_prediction != null) {
            response.setData(updated_prediction);
            response.setDescription("Prediction has been updated succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("Prediction was not updated.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

    public BaseResponse GetAllPredictions() {
        BaseResponse response = new BaseResponse();
        List<Predictions> predictions = predictionsRepository.findAll();
        if (!predictions.isEmpty()) {
            response.setData(predictions);
            response.setDescription("Predictions found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse GetAllPredictionByDate(Date matchDate) {
        BaseResponse response = new BaseResponse();
        List<Predictions> predictions = predictionsRepository.findPredictionsByMatchDate(matchDate);
        if (!predictions.isEmpty()) {
            response.setData(predictions);
            response.setDescription("Predictions found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse GetPredictionByID(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<Predictions> predictions = predictionsRepository.findById(id);
        if (predictions.isPresent()) {
            response.setData(predictions);
            response.setDescription("Prediction found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse DeletePrediction(long id) {
        BaseResponse response = new BaseResponse();
        Optional<Predictions> prediction = predictionsRepository.findById(id);
        if(prediction.isPresent()) {
            predictionsRepository.deleteById(id);
            response.setDescription("Prediction deleted successfully");
            response.setStatusCode(HttpServletResponse.SC_OK);
        }else {
            response.setDescription("No Set found");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }



}
