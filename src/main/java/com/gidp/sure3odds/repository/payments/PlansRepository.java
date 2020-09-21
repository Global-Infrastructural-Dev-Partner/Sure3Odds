package com.gidp.sure3odds.repository.payments;


import com.gidp.sure3odds.entity.payments.Plans;
import com.gidp.sure3odds.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansRepository extends JpaRepository<Plans, Long> {

	Plans findPlansByUser(Users users);

}
