package com.gidp.sure3odds.repository.payments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.payments.PlanTypes;

@Repository
public interface PlanTypesRepository extends JpaRepository<PlanTypes, Long> {

}