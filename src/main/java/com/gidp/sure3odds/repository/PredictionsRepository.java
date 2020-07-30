package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gidp.sure3odds.entity.Predictions;

public interface PredictionsRepository extends JpaRepository<Predictions, Long> {

}
