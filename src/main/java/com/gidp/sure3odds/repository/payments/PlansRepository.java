package com.gidp.sure3odds.repository.payments;


import com.gidp.sure3odds.entity.payments.Plans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansRepository extends JpaRepository<Plans, Long> {

	@Query(value = "SELECT * FROM sure_plans WHERE userid = ?1", nativeQuery = true)
	Plans findPlanByUserID(long userid);



}
