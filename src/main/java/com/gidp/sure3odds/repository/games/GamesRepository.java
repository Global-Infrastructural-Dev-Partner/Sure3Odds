package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {



    List<Games> findGamesByMatchDate(Date matchDate);

}
