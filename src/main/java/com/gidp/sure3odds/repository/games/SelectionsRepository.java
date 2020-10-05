package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Selections;
import com.gidp.sure3odds.entity.games.Teams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionsRepository extends JpaRepository<Selections, Long> {

    Page<Selections> findSelectionsByNameContainingOrderByName(String name, Pageable pageable);
}
