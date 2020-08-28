package com.gidp.sure3odds.repository.users;

import com.gidp.sure3odds.entity.users.MemberAdvisers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAdvisersRepository extends JpaRepository<MemberAdvisers, Long> {
	
	
	@Query(value = "SELECT * FROM sure_member_advisers WHERE memberuserid = ?1", nativeQuery = true)
	MemberAdvisers findAdviserByMemberuser(long memberUserID);
	
	
	@Query(value = "SELECT * FROM sure_member_advisers WHERE adviseruserid = ?1", nativeQuery = true)
	List<MemberAdvisers> findMembersByAdviseruser(long adviserUserID);
}
