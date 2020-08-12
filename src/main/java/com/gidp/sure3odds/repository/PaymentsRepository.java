package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.Payments;

import java.util.List;


@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {

	@Query(value = "SELECT * FROM sure_payments WHERE userid = ?1", nativeQuery = true)
	List<Payments> findPaymentsByUserID(long userid);
}
