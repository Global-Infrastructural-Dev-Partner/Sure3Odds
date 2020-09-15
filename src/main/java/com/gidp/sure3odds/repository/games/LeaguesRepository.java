package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Countries;
import com.gidp.sure3odds.entity.games.Leagues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaguesRepository extends JpaRepository<Leagues, Long> {

    @Query(value = "SELECT * FROM sure_leagues WHERE country_id = ?1 order by name asc", nativeQuery = true)
    List<Leagues> findLeaguesByCountryID(Countries countries);

    Page<Leagues> findLeaguesByNameContainingOrderByName(String name, Pageable pageable);
}
