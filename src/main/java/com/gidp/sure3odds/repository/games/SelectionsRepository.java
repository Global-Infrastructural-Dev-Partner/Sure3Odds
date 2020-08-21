package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Selections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionsRepository extends JpaRepository<Selections, Long> {

}
