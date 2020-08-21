package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.Votes;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Users;
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

    /**
     * @param votes
     * @return
     */
    public BaseResponse CreateVotes(Votes votes, long userid) {
        BaseResponse response = new BaseResponse();
        Optional<Users> user = usersRepository.findById(userid);
        votes.setUserID(user.get());
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

        Optional<Votes> UserVote = votesRepository.findByGameIDAndUserID(gameID, userID);
        if(UserVote.isPresent()){
            Votes.put("UserVote", UserVote);
        }

        List<Votes> AwayVotes = votesRepository.findVotesByAwayVoteAndGameID(1l, gameID);
        if(!AwayVotes.isEmpty()){
            Votes.put("AwayVotes", AwayVotes.size());
        }

        List<Votes> HomeVotes = votesRepository.findVotesByHomeVoteAndGameID(1l, gameID);
        if(!HomeVotes.isEmpty()){
            Votes.put("HomeVotes", HomeVotes.size());
        }

        List<Votes> DrawVotes = votesRepository.findVotesByDrawVoteAndGameID(1l, gameID);
        if(!DrawVotes.isEmpty()){
            Votes.put("DrawVotes", DrawVotes.size());
        }


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
