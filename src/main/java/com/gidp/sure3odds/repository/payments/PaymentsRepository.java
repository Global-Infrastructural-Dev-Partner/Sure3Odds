package com.gidp.sure3odds.repository.payments;

import com.gidp.sure3odds.entity.payments.Payments;
import com.gidp.sure3odds.entity.payments.PlanTypes;
import com.gidp.sure3odds.entity.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {


	Page<Payments> findByUser(Users users, Pageable pageable);


	List<Payments> findPaymentsByPaymentdateBetweenAndPlantypeEquals(Date startDate, Date endDate, PlanTypes planTypes);

	List<Payments> findPaymentsByPlantypeEquals(PlanTypes planTypes);

	Page<Payments> findByPaymenttypeContainingOrPlatformContainingOrderByPaymentdate(String searchvalue, String searchValue, Pageable pageable);

}
