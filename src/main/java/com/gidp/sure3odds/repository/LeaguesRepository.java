package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.Leagues;

import java.util.List;

@Repository
public interface LeaguesRepository extends JpaRepository<Leagues, Long> {

    @Query(value = "SELECT * FROM sure_leagues WHERE countryid = ?1 order by name asc", nativeQuery = true)
    List<Leagues> findLeaguesByCountryID(long countryid);

}
