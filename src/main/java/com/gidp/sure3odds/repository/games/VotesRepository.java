package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Games;
import com.gidp.sure3odds.entity.games.Votes;
import com.gidp.sure3odds.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {


    Optional<Votes> findVotesByGameIDAndUserID(long gameid, long userid);

    Optional<Votes> findByGameIDAndUserID(Games gameID, Users userID);


    List<Votes> countVotesByAwayVoteAndGameID(long awayvote, Games gameID);


    List<Votes> countVotesByHomeVoteAndGameID(long awayvote, Games gameID);


    List<Votes> countVotesByDrawVoteAndGameID(long awayvote, Games gameID);


    List<Votes> countByAwayVoteEqualsAndGameID(long away, Games games);



}
