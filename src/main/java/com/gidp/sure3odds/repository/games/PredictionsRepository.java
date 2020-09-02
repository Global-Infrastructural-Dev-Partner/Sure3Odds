package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Predictions;
import com.gidp.sure3odds.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PredictionsRepository extends JpaRepository<Predictions, Long> {


    List<Predictions> findPredictionsByMatchDate(LocalDate matchDate);

    List<Predictions> findPredictionsByMatchDateAndUserOrderByMatchTime(LocalDate matchDate, Users users);

}
