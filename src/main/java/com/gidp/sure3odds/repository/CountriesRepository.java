package com.gidp.sure3odds.repository;

import com.gidp.sure3odds.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Long> {


//    @Query(value = "SELECT * FROM sure_users WHERE firstname like ?1 or lastname like ?1", nativeQuery = true)
    List<Countries> findByName(String name);
}
