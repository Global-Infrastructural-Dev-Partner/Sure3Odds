package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.*;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.repository.games.*;
import com.gidp.sure3odds.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
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
    UsersRepository usersRepository;

    @Autowired
    SelectionsRepository selectionsRepository;

    @Autowired
    StatusRepository statusRepository;


    public BaseResponse CreatePrediction(Long UserID, NewGameAndPrediction newGameAndPrediction) {
        BaseResponse response = new BaseResponse();
        Long countryid = newGameAndPrediction.getCountry().getId();
        Optional<Countries> countries = countriesRepository.findById(countryid);
        if (countries.isPresent()) {
            Long leagueid = newGameAndPrediction.getLeague().getId();
            Optional<Leagues> league = leaguesRepository.findById(leagueid);
            if (league.isPresent()) {
                Long hometeamid = newGameAndPrediction.getHometeam().getId();
                Optional<Teams> hometeam = teamsRepository.findById(hometeamid);
                if (hometeam.isPresent()) {
                    Long awayteamid = newGameAndPrediction.getAwayteam().getId();
                    Optional<Teams> awayteam = teamsRepository.findById(awayteamid);
                    if (awayteam.isPresent()) {
                        Optional<Users> users = usersRepository.findById(UserID);
                        if (users.isPresent()) {
                            Long selectionId = newGameAndPrediction.getSelections().getId();
                            Optional<Selections> selections = selectionsRepository.findById(selectionId);
                            if (selections.isPresent()) {
                                Status status = statusRepository.findByName("Pending").get();
                                Predictions newPrediction = new Predictions(newGameAndPrediction.getMatchdate(), newGameAndPrediction.getMatchtime() , newGameAndPrediction.getOdds());
                                newPrediction.setAwayteam(awayteam.get());
                                newPrediction.setHometeam(hometeam.get());
                                newPrediction.setLeague(league.get());
                                newPrediction.setUser(users.get());
                                newPrediction.setSelections(selections.get());
                                newPrediction.setCountry(countries.get());
                                newPrediction.setStatus(status);
                                Predictions savedPrediction = predictionsRepository.save(newPrediction);
                                response.setData(savedPrediction);
                                response.setDescription("Prediction created successfully");
                                response.setStatusCode(HttpServletResponse.SC_OK);
                            } else {
                                response.setDescription("Ops!, the process was halted, try to login again");
                                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                            }
                        } else {
                            response.setDescription("Ops!, the process was halted, try to login again");
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
            response.setDescription("Prediction has been updated successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("Prediction was not updated.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

    public BaseResponse GetPredictionByDateAndUserID(LocalDate matchDate, Long UserID) {
        BaseResponse response = new BaseResponse();
        Users users = usersRepository.findById(UserID).get();
        List<Predictions> predictions = null;
        if(users.getId() == 1){
            predictions = predictionsRepository.findPredictionsByMatchdateOrderByMatchtime(matchDate);
        }else{
            predictions = predictionsRepository.findPredictionsByMatchdateAndUserOrderByMatchtime(matchDate, users);
        }
        if (!predictions.isEmpty()) {
            response.setData(predictions);
            response.setDescription("Predictions found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No predictions yet.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse DeletePrediction(long id) {
        BaseResponse response = new BaseResponse();
        Optional<Predictions> prediction = predictionsRepository.findById(id);
        if (prediction.isPresent()) {
            predictionsRepository.deleteById(id);
            response.setDescription("Prediction deleted successfully");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No Set found");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }


}
