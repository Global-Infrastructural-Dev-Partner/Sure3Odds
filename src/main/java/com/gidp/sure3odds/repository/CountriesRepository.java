package com.gidp.sure3odds.repository;

import com.gidp.sure3odds.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Long> {


    List<Countries> findCountriesByNameContainingOrderByName(String name);
}
