package com.gidp.sure3odds.repository.games;


import com.gidp.sure3odds.entity.games.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {


    @Query(value = "SELECT * FROM sure_teams WHERE league_id = ?1 and name = ?2", nativeQuery = true)
    Optional<Teams> findByLeagueIDAndTeamName(Long leagueID, String teamName);

    @Query(value = "SELECT * FROM sure_teams WHERE league_id = ?1 order by name asc", nativeQuery = true)
    List<Teams> findTeamsByLeagueID(long leagueid);

    @Query(value = "SELECT * FROM sure_teams WHERE country_id = ?1 order by name asc", nativeQuery = true)
    List<Teams> findTeamsByCountryID(long countryid);


    List<Teams> findTeamsByNameContainingOrderByName(String name);
}
