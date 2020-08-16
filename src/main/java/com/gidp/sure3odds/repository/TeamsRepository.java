package com.gidp.sure3odds.repository;


import com.gidp.sure3odds.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {


    @Query(value = "SELECT * FROM sure_teams WHERE leagueid = ?1 and name = ?2", nativeQuery = true)
    Optional<Teams> findByLeagueIDAndTeamName(Long leagueID, String teamName);

    @Query(value = "SELECT * FROM sure_teams WHERE leagueid = ?1 order by name asc", nativeQuery = true)
    List<Teams> findTeamsByLeagueID(long leagueid);

    @Query(value = "SELECT * FROM sure_teams WHERE countryid = ?1 order by name asc", nativeQuery = true)
    List<Teams> findTeamsByCountryID(long countryid);


    List<Teams> findTeamsByNameContaining(String name);
}
