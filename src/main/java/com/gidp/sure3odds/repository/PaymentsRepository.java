package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gidp.sure3odds.entity.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {

}
