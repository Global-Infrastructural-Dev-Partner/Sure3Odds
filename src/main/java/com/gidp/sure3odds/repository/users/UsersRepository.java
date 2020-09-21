package com.gidp.sure3odds.repository.users;

import com.gidp.sure3odds.entity.users.UserTypes;
import com.gidp.sure3odds.entity.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	

	@Query(value = "SELECT * FROM sure_users WHERE email = ?1 or phone = ?2", nativeQuery = true)
	Optional<Users> findByEmailOrPhoneNumber(String email, String phoneNumber);

	Optional<Users> findUsersByEmailOrPhoneContaining(String email, String phone);

	List<Users> findUsersByFirstnameOrLastnameContaining(String searchValue, String searchText);

	Optional<Users> findByEmail(String email);


	List<Users> findUsersByDatejoinedBetweenAndUsertypesEquals(Date startDate, Date endDate, UserTypes usertypeID);


	List<Users> findUsersByDatejoinedBetweenAndStatusEqualsAndUsertypesEquals(Date startDate, Date endDate, String status, UserTypes usertypeID);


	List<Users> findUsersByUsertypesEquals(UserTypes usertypeID);

	List<Users> findUsersByStatusEqualsAndUsertypesEquals(String status, UserTypes usertypeID);

	Page<Users> findByUsertypes(UserTypes userTypes, Pageable pageable);


	Page<Users> findByLastnameContainingOrFirstnameContainingAndUsertypesEquals(String searchvalue, String searchValue, UserTypes userTypes, Pageable pageable);


}
