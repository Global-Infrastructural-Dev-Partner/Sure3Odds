package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Countries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Long> {

    Page<Countries> findCountriesByNameContainingOrderByName(String name, Pageable pageable);
}
