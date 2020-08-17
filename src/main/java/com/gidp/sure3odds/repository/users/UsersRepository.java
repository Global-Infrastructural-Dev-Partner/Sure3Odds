package com.gidp.sure3odds.repository.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.users.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
	@Query(value = "SELECT * FROM sure_users WHERE usertypeid = ?1", nativeQuery = true)
	List<Users> findUsersByUserTypeID(long usertypeid);

//	@Query(value = "SELECT * FROM sure_users WHERE email = ?1 or phone = ?2", nativeQuery = true)
//	Optional<Users> findByEmailOrPhoneNumber(String email, String phoneNumber);
//
	Optional<Users> findUsersByEmailOrPhoneContaining(String email, String phone);

	List<Users> findUsersByFirstnameOrLastnameContaining(String searchValue, String searchText);

}
