package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Games;
import com.gidp.sure3odds.entity.games.Sets;
import com.gidp.sure3odds.entity.games.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {


    List<Games> findGamesByMatchdateAndSetsOrderByMatchtime(LocalDate matchDate, Sets set);

    List<Games> findByMatchdateAndSetsAndStatusOrderByMatchtime(LocalDate matchDate, Sets set, Status status);

}
