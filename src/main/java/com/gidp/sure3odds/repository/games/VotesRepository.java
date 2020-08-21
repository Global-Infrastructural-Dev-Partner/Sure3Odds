package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {


    Optional<Votes> findVotesByGameIDAndUserID(long gameid, long userid);


    @Query(value = "SELECT * FROM sure_votes WHERE gameid = ?1 and userid = ?2", nativeQuery = true)
    Optional<Votes> findByGameIDAndUserID(long gameID, long userID);


    @Query(value = "SELECT * FROM sure_votes WHERE away_vote = ?1 and gameid = ?2", nativeQuery = true)
    List<Votes> findVotesByAwayVoteAndGameID(long awayvote, Long gameID);



    @Query(value = "SELECT * FROM sure_votes WHERE home_vote = ?1 and gameid = ?2", nativeQuery = true)
    List<Votes> findVotesByHomeVoteAndGameID(long homevote, Long gameID);



    @Query(value = "SELECT * FROM sure_votes WHERE draw_vote = ?1 and gameid = ?2", nativeQuery = true)
    List<Votes> findVotesByDrawVoteAndGameID(long drawvote, Long gameID);

}
