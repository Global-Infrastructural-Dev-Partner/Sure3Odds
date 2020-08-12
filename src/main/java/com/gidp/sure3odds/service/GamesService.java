package com.gidp.sure3odds.service;

import com.gidp.sure3odds.entity.*;
import com.gidp.sure3odds.repository.GamesRepository;
import com.gidp.sure3odds.repository.LeaguesRepository;
import com.gidp.sure3odds.repository.SetsRepository;
import com.gidp.sure3odds.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    public BaseResponse addGame(NewGameAndPrediction newGameAndPrediction) throws IOException {
        BaseResponse response = new BaseResponse();
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
                        Games newGames = new Games();
                        newGames.setAwayTeamID(awayteam.get());
                        newGames.setHomeTeamID(awayteam.get());
                        newGames.setLeagueID(league.get());
                        newGames.setSetID(sets.get());
                        Games savedGames = gamesRepository.save(newGames);
                        response.setData(savedGames);
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

    public BaseResponse GetAllGamesByDate() {
        BaseResponse response = new BaseResponse();
        List<Games> games = gamesRepository.findAll();
        ArrayList<Object>  data = new ArrayList<>();
        if (!games.isEmpty()) {
        for (Games game : games){
            Date currendate = new Date();
            Date gameDate = game.getMatchDate();
            if(currendate.equals(gameDate)){
                data.add(game);
            }
        }
        if(!data.isEmpty()){
            response.setData(games);
            response.setDescription("Games found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        }else{
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }



        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

//    public BaseResponse searchByFirstNameOrLastName(Long usertypeid, String searchValue) {
//        BaseResponse response = new BaseResponse();
//        List<Users> users = usersRepository.searchByFirstNameOrLastName(searchValue);
//        ArrayList<Object> data = new ArrayList<>();
//        if (!users.isEmpty()) {
//            for (Users user : users) {
//                Long usertypeID = user.getUserTypeID().getId();
//                if (usertypeID == usertypeid) {
//                    data.add(user);
//                }
//            }
//            if (!data.isEmpty()) {
//                response.setData(data);
//                response.setDescription("usertypes found succesfully.");
//                response.setStatusCode(HttpServletResponse.SC_OK);
//            } else {
//                response.setDescription("No result found.");
//                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
//            }
//
//        } else {
//            response.setDescription("No result found.");
//            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
//        }
//        return response;
//
//    }


}
