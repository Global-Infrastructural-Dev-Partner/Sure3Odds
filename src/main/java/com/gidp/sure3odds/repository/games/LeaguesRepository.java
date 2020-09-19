package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Countries;
import com.gidp.sure3odds.entity.games.Leagues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaguesRepository extends JpaRepository<Leagues, Long> {


    List<Leagues> findByCountryOrderByName(Countries countries, Sort sort);


    Page<Leagues> findLeaguesByNameContainingOrderByName(String name, Pageable pageable);


    List<Leagues> findByNameContainingAndCountryOrderByName(String name, Countries countries, Sort sort);
}
