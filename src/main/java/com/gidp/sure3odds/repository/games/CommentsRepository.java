package com.gidp.sure3odds.repository.games;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.games.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
