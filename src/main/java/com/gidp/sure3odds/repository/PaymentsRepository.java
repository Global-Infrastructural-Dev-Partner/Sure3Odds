package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.Payments;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {

}
