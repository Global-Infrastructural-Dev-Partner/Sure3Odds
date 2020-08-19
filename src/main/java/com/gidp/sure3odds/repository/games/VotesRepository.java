package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Games;
import com.gidp.sure3odds.entity.games.Votes;
import com.gidp.sure3odds.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {


    Optional<Votes> findVotesByGameIDAndUserID(Games gameID, Users userID);

}
