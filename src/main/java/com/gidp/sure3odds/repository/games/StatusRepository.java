package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {


    List<Status> findByNameContainingOrderByName(String name);

    Optional<Status> findByName(String name);

    List<Status> findByType(String type);
}
