package com.gidp.sure3odds.repository.games;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.games.Votes;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {

}
