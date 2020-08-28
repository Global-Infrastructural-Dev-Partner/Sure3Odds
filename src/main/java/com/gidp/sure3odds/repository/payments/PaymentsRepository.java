package com.gidp.sure3odds.repository.payments;

import com.gidp.sure3odds.entity.payments.Payments;
import com.gidp.sure3odds.entity.payments.PlanTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {

	@Query(value = "SELECT * FROM sure_payments WHERE userid = ?1", nativeQuery = true)
	List<Payments> findPaymentsByUser(long userid);


	List<Payments> findPaymentsByPaymentdateBetweenAndPlantypeEquals(Date startDate, Date endDate, PlanTypes planTypes);

	List<Payments> findPaymentsByPlantypeEquals(PlanTypes planTypes);

}
