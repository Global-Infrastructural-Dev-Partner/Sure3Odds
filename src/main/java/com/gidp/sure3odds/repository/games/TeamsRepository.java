package com.gidp.sure3odds.repository.games;


import com.gidp.sure3odds.entity.games.Leagues;
import com.gidp.sure3odds.entity.games.Teams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {

    Page<Teams> findByLeagueOrderByName(Leagues leagues, Pageable pageable);


    Page<Teams> findTeamsByNameContainingOrderByName(String name, Pageable pageable);


    Page<Teams> findByNameContainingAndLeagueOrderByName(String name, Leagues leagues, Pageable pageable);
}
