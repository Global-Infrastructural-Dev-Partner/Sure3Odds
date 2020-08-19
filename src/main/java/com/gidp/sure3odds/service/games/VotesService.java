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
    public BaseResponse CreateVotes(Votes votes) {
        BaseResponse response = new BaseResponse();
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
    public BaseResponse GetVotesByGameIDAndUserID(Long userID, Long gameID) {
        BaseResponse response = new BaseResponse();
        Optional<Users> user = usersRepository.findById(userID);
        Optional<Games> game = gamesRepository.findById(gameID);

        Optional<Votes> votes = votesRepository.findVotesByGameIDAndUserID(game.get(), user.get());
        if (!votes.isPresent()) {
            response.setData(votes);
            response.setDescription("Votes found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }
}
