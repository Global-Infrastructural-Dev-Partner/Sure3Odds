package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Games;
import com.gidp.sure3odds.entity.games.Votes;
import com.gidp.sure3odds.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {


    Optional<Votes> findVotesByGameAndUser(long gameid, long userid);


    @Query(value = "SELECT * FROM sure_votes WHERE gameid = ?1 and user_id = ?2", nativeQuery = true)
    Optional<Votes> findByGameAndUser(Games games, Users users);


    @Query(value = "SELECT * FROM sure_votes WHERE away_vote = ?1 and game_id = ?2", nativeQuery = true)
    List<Votes> findVotesByAwayVoteAndGame(long awayvote, Games games);



    @Query(value = "SELECT * FROM sure_votes WHERE home_vote = ?1 and game_id = ?2", nativeQuery = true)
    List<Votes> findVotesByHomeVoteAndGame(long homevote, Games game);



    @Query(value = "SELECT * FROM sure_votes WHERE draw_vote = ?1 and game_id = ?2", nativeQuery = true)
    List<Votes> findVotesByDrawVoteAndGame(long drawvote, Games game);

}
