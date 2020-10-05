package com.gidp.sure3odds.repository.payments;


import com.gidp.sure3odds.entity.payments.PlanTypes;
import com.gidp.sure3odds.entity.payments.Plans;
import com.gidp.sure3odds.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlansRepository extends JpaRepository<Plans, Long> {

	Plans findPlansByUser(Users users);

	List<Plans> findByPlantype(PlanTypes planTypes);

	List<Plans> findByStartDateBetweenAndPlantypeEquals(LocalDate startDate, LocalDate endDate, PlanTypes planTypes);
}
