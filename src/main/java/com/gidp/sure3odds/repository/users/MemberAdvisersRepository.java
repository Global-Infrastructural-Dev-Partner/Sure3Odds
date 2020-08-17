package com.gidp.sure3odds.repository.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.users.MemberAdvisers;

@Repository
public interface MemberAdvisersRepository extends JpaRepository<MemberAdvisers, Long> {
	
	
	@Query(value = "SELECT * FROM sure_member_advisers WHERE memberuserid = ?1", nativeQuery = true)
	MemberAdvisers findAdviserByMemberUserID(long memberUserID);
	
	
	@Query(value = "SELECT * FROM sure_member_advisers WHERE adviseruserid = ?1", nativeQuery = true)
	List<MemberAdvisers> findMembersByAdviserUserID(long adviserUserID);
}
