package com.gidp.sure3odds.repository.games;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.games.Predictions;

import java.util.Date;
import java.util.List;

@Repository
public interface PredictionsRepository extends JpaRepository<Predictions, Long> {


    List<Predictions> findPredictionsByMatchDate(Date matchDate);


}
