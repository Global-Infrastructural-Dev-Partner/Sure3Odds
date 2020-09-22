package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.*;
import com.gidp.sure3odds.entity.payments.Plans;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.helper.AppHelper;
import com.gidp.sure3odds.repository.games.*;
import com.gidp.sure3odds.repository.payments.PlansRepository;
import com.gidp.sure3odds.repository.users.UsersRepository;
import com.gidp.sure3odds.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    UsersService usersService;

    @Autowired
    PlansRepository plansRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    SelectionsRepository selectionsRepository;

    @Autowired
    UsersRepository usersRepository;

    AppHelper appHelper = new AppHelper();

    @Autowired
    VotesRepository votesRepository;

    @Autowired
    CommentsRepository commentsRepository;


    public BaseResponse addGame(NewGameAndPrediction newGameAndPrediction) {
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
                        Long setid = newGameAndPrediction.getSets().getId();
                        Optional<Sets> sets = setsRepository.findById(setid);
                        if (sets.isPresent()) {
                            Long selectionid = newGameAndPrediction.getSelections().getId();
                            Optional<Selections> selections = selectionsRepository.findById(selectionid);
                            if (selections.isPresent()) {
                                Status gameStatus = statusRepository.findByName("Not Started").get();
                                Games newGame = new Games(0, 0, newGameAndPrediction.getOdds(),
                                        newGameAndPrediction.getMatchdate(), newGameAndPrediction.getMatchtime());
                                newGame.setAwayteam(awayteam.get());
                                newGame.setHometeam(hometeam.get());
                                newGame.setLeague(league.get());
                                newGame.setSets(sets.get());
                                newGame.setCountry(countries.get());
                                newGame.setSelections(selections.get());
                                newGame.setStatus(gameStatus);
                                Games savedGame = gamesRepository.save(newGame);
                                response.setData(savedGame);
                                response.setDescription("Game created successfully");
                                response.setStatusCode(HttpServletResponse.SC_OK);
                            } else {
                                response.setDescription("Please, pick a selection to add the game to.");
                                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                            }
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


    public BaseResponse CreateGameFromPrediction(long predictionId, long setId, long statusId) {
        BaseResponse response = new BaseResponse();

        Optional<Predictions> prediction = predictionsRepository.findById(predictionId);
        if (prediction.isPresent()) {
            Optional<Sets> set = setsRepository.findById(setId);
            if (set.isPresent()) {
                Optional<Status> predictionStatus = statusRepository.findById(statusId);
                if (predictionStatus.isPresent()) {
                    Games newGame = new Games(0, 0, prediction.get().getOdds(),
                            prediction.get().getMatchdate(), prediction.get().getMatchtime());

                    Status gameStatus = statusRepository.findByName("Not Started").get();
                    newGame.setAwayteam(prediction.get().getAwayteam());
                    newGame.setHometeam(prediction.get().getHometeam());
                    newGame.setLeague(prediction.get().getLeague());
                    newGame.setSets(set.get());
                    newGame.setSelections(prediction.get().getSelections());
                    newGame.setCountry(prediction.get().getCountry());
                    newGame.setStatus(gameStatus);
                    Games savedGame = gamesRepository.save(newGame);
                    //update prediction status to approved
//
                    prediction.get().setStatus(predictionStatus.get());
                    predictionsRepository.save(prediction.get());
                    response.setData(savedGame);
                    response.setDescription("Game has been updated successfully.");
                    response.setStatusCode(HttpServletResponse.SC_OK);
                }

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

    public BaseResponse GetUserGames(long UserID, LocalDate matchDate) {
        BaseResponse response = new BaseResponse();
        ArrayList<Object> AllUserGames = new ArrayList<>();
        double set1Odds = 0.00;
        double set2Odds = 0.00;
        double set3Odds = 0.00;

        List<Games> Set1Games = new ArrayList<>();
        List<Games> Set2Games = new ArrayList<>();
        List<Games> Set3Games = new ArrayList<>();

        LocalDate CurrentDate = LocalDate.now();

        if (usersService.IsUserActive(UserID)) {
            long planTypeID = 0l;
            Users user = usersRepository.findById(UserID).get();
            if (user.getUsertypes().getName().equals("Member")) {
                Plans plan = plansRepository.findPlansByUser(user);
                planTypeID = plan.getPlantype().getId();
            } else if (user.getUsertypes().getName().equals("Admin")){
                usersService.ValidateAllUsersPaymentDueDate();
            } else if (user.getUsertypes().getName().equals("SubAdmin")){
                UserID = 1;
            }
            Status status = statusRepository.findByName("Won").get();
            if (planTypeID == 1 || UserID == 1) {
                //Get for VVIP (set 1, set 2, set 3)
                Optional<Sets> set1 = setsRepository.findById(1l);
                if (CurrentDate.equals(matchDate)) {
                    Set1Games = gamesRepository.findGamesByMatchdateAndSetsOrderByMatchtime(matchDate, set1.get());
                } else {
                    Set1Games = gamesRepository.findByMatchdateAndSetsAndStatusOrderByMatchtime(matchDate, set1.get(), status);
                }
                HashMap<String, Object> userGames1 = new HashMap<>();
                if (!Set1Games.isEmpty()) {
                    userGames1.put("title", set1.get().getName());
                    userGames1.put("items", Set1Games);
                    for (Games game : Set1Games) {
                        double s1odds = game.getOdds();
                        set1Odds += s1odds;
                    }
                    double TotalSet1Odds = Math.round(set1Odds * 100.0) / 100.0;
                    userGames1.put("odds", TotalSet1Odds);
                    AllUserGames.add(userGames1);
                } else {
                    userGames1.put("title", set1.get().getName());
                    userGames1.put("msg", set1.get().getName() + " Games will be available shortly or No Games.");
                    AllUserGames.add(userGames1);
                }


                Optional<Sets> set2 = setsRepository.findById(2l);
                if (CurrentDate.equals(matchDate)) {
                    Set2Games = gamesRepository.findGamesByMatchdateAndSetsOrderByMatchtime(matchDate, set2.get());
                } else {
                    Set2Games = gamesRepository.findByMatchdateAndSetsAndStatusOrderByMatchtime(matchDate, set2.get(), status);
                }
                HashMap<String, Object> userGames2 = new HashMap<>();
                if (!Set2Games.isEmpty()) {
                    userGames2.put("title", set2.get().getName());
                    userGames2.put("items", Set2Games);
                    for (Games game : Set2Games) {
                        double s2odds = game.getOdds();
                        set2Odds += s2odds;
                    }
                    double TotalSet2Odds = Math.round(set2Odds * 100.0) / 100.0;
                    userGames2.put("odds", TotalSet2Odds);
                    AllUserGames.add(userGames2);
                } else {
                    userGames2.put("title", set2.get().getName());
                    userGames2.put("msg", set2.get().getName() + " Games will be available shortly or No Games.");
                    AllUserGames.add(userGames2);
                }

                Optional<Sets> set3 = setsRepository.findById(3l);
                if (CurrentDate.equals(matchDate)) {
                    Set3Games = gamesRepository.findGamesByMatchdateAndSetsOrderByMatchtime(matchDate, set3.get());
                } else {
                    Set3Games = gamesRepository.findByMatchdateAndSetsAndStatusOrderByMatchtime(matchDate, set3.get(), status);
                }
                HashMap<String, Object> userGames3 = new HashMap<>();
                if (!Set3Games.isEmpty()) {
                    userGames3.put("title", set3.get().getName());
                    userGames3.put("items", Set3Games);
                    for (Games game : Set3Games) {
                        double s3odds = game.getOdds();
                        set3Odds += s3odds;
                    }
                    double TotalSet3Odds = Math.round(set3Odds * 100.0) / 100.0;
                    userGames3.put("odds", TotalSet3Odds);
                    AllUserGames.add(userGames3);
                } else {
                    userGames3.put("title", set3.get().getName());
                    userGames3.put("msg", set3.get().getName() + " Games will be available shortly or No Games.");
                    AllUserGames.add(userGames3);
                }
            } else if (planTypeID == 2) {
                Optional<Sets> set1 = setsRepository.findById(1l);
                if (CurrentDate.equals(matchDate)) {
                    Set1Games = gamesRepository.findGamesByMatchdateAndSetsOrderByMatchtime(matchDate, set1.get());
                } else {
                    Set1Games = gamesRepository.findByMatchdateAndSetsAndStatusOrderByMatchtime(matchDate, set1.get(), status);
                }
                HashMap<String, Object> userGames1 = new HashMap<>();
                if (!Set1Games.isEmpty()) {
                    userGames1.put("title", set1.get().getName());
                    userGames1.put("items", Set1Games);
                    for (Games game : Set1Games) {
                        double s1odds = game.getOdds();
                        set1Odds += s1odds;
                    }
                    double TotalSet1Odds = Math.round(set1Odds * 100.0) / 100.0;
                    userGames1.put("odds", TotalSet1Odds);
                    AllUserGames.add(userGames1);
                } else {
                    userGames1.put("title", set1.get().getName());
                    userGames1.put("msg", set1.get().getName() + " Games will be available shortly or No Games.");
                    AllUserGames.add(userGames1);
                }


                Optional<Sets> set2 = setsRepository.findById(2l);
                if (CurrentDate.equals(matchDate)) {
                    Set2Games = gamesRepository.findGamesByMatchdateAndSetsOrderByMatchtime(matchDate, set2.get());
                } else {
                    Set2Games = gamesRepository.findByMatchdateAndSetsAndStatusOrderByMatchtime(matchDate, set2.get(), status);
                }
                HashMap<String, Object> userGames2 = new HashMap<>();
                if (!Set2Games.isEmpty()) {
                    userGames2.put("title", set2.get().getName());
                    userGames2.put("items", Set2Games);
                    for (Games game : Set2Games) {
                        double s2odds = game.getOdds();
                        set2Odds += s2odds;
                    }
                    double TotalSet2Odds = Math.round(set2Odds * 100.0) / 100.0;
                    userGames2.put("odds", TotalSet2Odds);
                    AllUserGames.add(userGames2);
                } else {
                    userGames2.put("title", set2.get().getName());
                    userGames2.put("msg", set2.get().getName() + " Games will be available shortly or No Games.");
                    AllUserGames.add(userGames2);
                }
            }
            response.setData(AllUserGames);
            response.setDescription("Games found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);

        } else {
            String result = usersService.GetUserName(UserID) + ", your subscription has expired or something went wrong. Please, contact Sure3Odds Support on 08188888320 / send an email to support@sure3odds.com or renew your subscription.";
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
            response.setDescription("Games has been updated successfully.");
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

    public BaseResponse DeleteGame(long gameId) {
        BaseResponse response = new BaseResponse();
        Optional<Games> game = gamesRepository.findById(gameId);
        if (game.isPresent()) {

            List<Votes> votes = votesRepository.findByGame(game.get());
            if (!votes.isEmpty()) {
                votesRepository.deleteAll(votes);
            }

            List<Comments> comments = commentsRepository.findByGame(game.get());
            if (!comments.isEmpty()) {
                commentsRepository.deleteAll(comments);
            }

            gamesRepository.deleteById(gameId);
            response.setDescription("Game deleted successfully");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

}
