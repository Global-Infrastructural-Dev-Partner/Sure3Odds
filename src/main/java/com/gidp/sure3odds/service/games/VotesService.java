package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.Games;
import com.gidp.sure3odds.entity.games.Votes;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.repository.games.GamesRepository;
import com.gidp.sure3odds.repository.games.VotesRepository;
import com.gidp.sure3odds.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class VotesService {

    @Autowired
    VotesRepository votesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    GamesRepository gamesRepository;

    /**
     * @param votes
     * @return
     */
    public BaseResponse CreateVotes(Votes votes, long userid) {
        BaseResponse response = new BaseResponse();
        Optional<Users> user = usersRepository.findById(userid);
        votes.setUser(user.get());
        Votes saved_votes = votesRepository.save(votes);
        if (saved_votes != null) {
            response.setData(saved_votes);
            response.setDescription("New votes added to game successfully");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("New votes was not created.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    /**
     * @param gameID
     * @return
     */
    public BaseResponse GetVotesByGameIDAndUserID(Long gameID, Long userID) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> Votes = new HashMap<>();

        Games games = gamesRepository.findById(gameID).get();
        Users users = usersRepository.findById(userID).get();

        Optional<Votes> UserVote = votesRepository.findByGameAndUser(games, users);
        Votes.put("UserVote", UserVote);

        List<Votes> AwayVotes = votesRepository.findVotesByAwayVoteAndGame(1l, games);
        Votes.put("AwayVotes", AwayVotes.size());

        List<Votes> HomeVotes = votesRepository.findVotesByHomeVoteAndGame(1l, games);
        Votes.put("HomeVotes", HomeVotes.size());

        List<Votes> DrawVotes = votesRepository.findVotesByDrawVoteAndGame(1l, games);
        Votes.put("DrawVotes", DrawVotes.size());


        if (!Votes.isEmpty()) {
            response.setData(Votes);
            response.setDescription("Votes found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }
    /**
     * @return
     */
    public BaseResponse GetVotes() {
        BaseResponse response = new BaseResponse();
        List<Votes> Votes = votesRepository.findAll();

        if (!Votes.isEmpty()) {
            response.setData(Votes);
            response.setDescription("Votes found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

}
