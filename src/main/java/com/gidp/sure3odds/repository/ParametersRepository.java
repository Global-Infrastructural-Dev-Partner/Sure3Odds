package com.gidp.sure3odds.repository;

import com.gidp.sure3odds.entity.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersRepository extends JpaRepository<Parameters, Long> {

//    @Query(value = "SELECT * FROM sure_parameters WHERE id = 1", nativeQuery = true)
//    Parameters findPayStackSecretKey();

}
