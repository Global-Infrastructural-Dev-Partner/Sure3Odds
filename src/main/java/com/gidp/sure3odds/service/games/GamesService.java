package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.*;
import com.gidp.sure3odds.entity.payments.Plans;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.NewGameAndPrediction;
import com.gidp.sure3odds.repository.games.*;
import com.gidp.sure3odds.repository.payments.PlansRepository;
import com.gidp.sure3odds.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    @Autowired
    UsersService usersService;

    @Autowired
    PlansRepository plansRepository;


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
                            Games savedGame = gamesRepository.save(newGame);
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
        if (prediction.isPresent()) {
            Optional<Sets> set = setsRepository.findById(setID);
            if (set.isPresent()) {
                Games newGame = new Games(0, 0, prediction.get().getPrediction(), prediction.get().getOdds(),
                        prediction.get().getConfidenceLevel(), "Not Started", prediction.get().getMatchDate(), prediction.get().getMatchTime());

                newGame.setAwayTeamID(prediction.get().getAwayTeamID());
                newGame.setHomeTeamID(prediction.get().getHomeTeamID());
                newGame.setLeagueID(prediction.get().getLeagueID());
                newGame.setSetID(set.get());
                newGame.setCountryID(prediction.get().getCountryID());
                Games savedGame = gamesRepository.save(newGame);
                //update prediction status to approved
                prediction.get().setStatus("Approved");
                predictionsRepository.save(prediction.get());
                response.setData(savedGame);
                response.setDescription("Games has been updated succesfully.");
                response.setStatusCode(HttpServletResponse.SC_OK);
            } else {
                response.setDescription("Please, select a set to add the game to.");
                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setDescription("Please, select a prediction to add the game to.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

    public BaseResponse GetUserGames(long UserID, Date matchDate) {
        BaseResponse response = new BaseResponse();
        //Get the user plan type by id,
        //Get the set(s) by the plantype
        //Get the games by the set
        HashMap<String, Object> userGames = new HashMap<>();
        List<Games> Set1Games = new ArrayList<>();
        List<Games> Set2Games = new ArrayList<>();
        List<Games> Set3Games = new ArrayList<>();

        Date SearchDate = null;
        Date CurrentDate = new Date();
        if(CurrentDate.equals(matchDate)){
            SearchDate = matchDate;
        }

        if (usersService.IsUserActive(UserID)) {
            long planTypeID = 0l;
            if(UserID != 1 ){
                Plans plan = plansRepository.findPlanByUserID(UserID);
                planTypeID = plan.getPlanTypeID().getId();
            }
            if (planTypeID == 1 || UserID == 1) {
                //Get for VVIP (set 1, set 2, set 3)
                Optional<Sets> set1 = setsRepository.findById(1l);
                if(CurrentDate.equals(matchDate)){
                    Set1Games = gamesRepository.findGamesByMatchDateAndSetIDOrderByMatchTime(matchDate, set1.get());
                }else{
                    Set1Games = gamesRepository.findGamesByMatchDateAndSetIDAndStatusOrderByMatchTime(matchDate, set1.get(), "Won");
                }
                if(!Set1Games.isEmpty()){
                    userGames.put(set1.get().getName(), Set1Games);
                }else{
                    userGames.put(set1.get().getName(), set1.get().getName() + " Games will be available shortly or No Games.");
                }

                Optional<Sets> set2 = setsRepository.findById(2l);
                if(CurrentDate.equals(matchDate)){
                    Set2Games = gamesRepository.findGamesByMatchDateAndSetIDOrderByMatchTime(matchDate, set2.get());
                }else{
                    Set2Games = gamesRepository.findGamesByMatchDateAndSetIDAndStatusOrderByMatchTime(matchDate, set2.get(), "Won");
                }
                if(!Set2Games.isEmpty()){
                    userGames.put(set2.get().getName(), Set2Games);
                }else{
                    userGames.put(set2.get().getName(), set2.get().getName() + " Games will be available shortly or No Games.");
                }

                Optional<Sets> set3 = setsRepository.findById(3l);
                if(CurrentDate.equals(matchDate)){
                    Set3Games = gamesRepository.findGamesByMatchDateAndSetIDOrderByMatchTime(matchDate, set3.get());
                }else{
                    Set3Games = gamesRepository.findGamesByMatchDateAndSetIDAndStatusOrderByMatchTime(matchDate, set3.get(), "Won");
                }
                if(!Set3Games.isEmpty()){
                    userGames.put(set3.get().getName(), Set3Games);
                }else{
                    userGames.put(set3.get().getName(), set3.get().getName() + " Games will be available shortly or No Games.");
                }
            } else if (planTypeID == 2) {
                //Get for VIP (set 1, set 2)
                Optional<Sets> set1 = setsRepository.findById(1l);
                if(CurrentDate.equals(matchDate)){
                    Set1Games = gamesRepository.findGamesByMatchDateAndSetIDOrderByMatchTime(matchDate, set1.get());
                }else{
                    Set1Games = gamesRepository.findGamesByMatchDateAndSetIDAndStatusOrderByMatchTime(matchDate, set1.get(), "Won");
                }
                if(!Set1Games.isEmpty()){
                    userGames.put(set1.get().getName(), Set1Games);
                }else{
                    userGames.put(set1.get().getName(), set1.get().getName() + " Games will be available shortly or No Games.");
                }

                Optional<Sets> set2 = setsRepository.findById(2l);
                if(CurrentDate.equals(matchDate)){
                    Set2Games = gamesRepository.findGamesByMatchDateAndSetIDOrderByMatchTime(matchDate, set2.get());
                }else{
                    Set2Games = gamesRepository.findGamesByMatchDateAndSetIDAndStatusOrderByMatchTime(matchDate, set2.get(), "Won");
                }
                if(!Set2Games.isEmpty()){
                    userGames.put(set2.get().getName(), Set2Games);
                }else{
                    userGames.put(set2.get().getName(), set2.get().getName() + " Games will be available shortly or No Games.");
                }

            }
            response.setData(userGames);
            response.setDescription("Games found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);

        } else {
            String result = usersService.GetUserName(UserID) + ", your subscription has expired. Please, contact the admin or renew your subscription.";
            response.setDescription(result);
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }


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
