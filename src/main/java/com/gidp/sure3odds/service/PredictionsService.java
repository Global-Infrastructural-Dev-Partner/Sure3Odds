package com.gidp.sure3odds.service;

import com.gidp.sure3odds.entity.*;
import com.gidp.sure3odds.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
                            Predictions newPrediction = new Predictions(new Date(), new Date(), newGameAndPrediction.getPrediction(), newGameAndPrediction.getOdds(), newGameAndPrediction.getStatus(), newGameAndPrediction.getConfidenceLevel());
                            newPrediction.setAwayTeamID(awayteam.get());
                            newPrediction.setHomeTeamID(hometeam.get());
                            newPrediction.setLeagueID(league.get());
                            newPrediction.setSetID(sets.get());
                            newPrediction.setCountryID(countries.get());
                            Predictions savedPrediction = predictionsRepository.save(newPrediction);
                            response.setData(savedPrediction);
                            response.setDescription("user created successfully");
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


}
