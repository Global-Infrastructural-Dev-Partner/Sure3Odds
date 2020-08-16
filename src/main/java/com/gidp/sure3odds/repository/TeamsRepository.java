package com.gidp.sure3odds.repository;

import com.gidp.sure3odds.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {


    @Query(value = "SELECT * FROM sure_teams WHERE leagueid = ?1 and name = ?2", nativeQuery = true)
    Optional<Teams> findByLeagueIDAndTeamName(Long leagueID, String teamName);

}
