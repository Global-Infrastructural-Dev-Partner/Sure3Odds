package com.gidp.sure3odds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
	@Query(value = "SELECT * FROM sure_users WHERE usertypeid = ?1", nativeQuery = true)
	List<Users> findUsersByUserTypeID(long usertypeid);

	@Query(value = "SELECT * FROM sure_users WHERE email = ?1 or phone = ?2", nativeQuery = true)
	Optional<Users> findByEmailOrPhoneNumber(String email, String phoneNumber);

	@Query(value = "SELECT * FROM sure_users WHERE firstname like ?1 or lastname like ?1", nativeQuery = true)
	List<Users> searchByFirstNameOrLastName(String searchValue);
	
	

	
//	@Modifying
//	@Query("update User u set u.firstname = ?1 where u.lastname = ?2")
//	int setFixedFirstnameFor(String firstname, String lastname);
	
//	@Modifying
//	@Query(value = "update Users u set u.status = ? where u.name = ?", 
//	  nativeQuery = true)
//	int updateUserSetStatusForNameNative(Integer status, String name);
}
