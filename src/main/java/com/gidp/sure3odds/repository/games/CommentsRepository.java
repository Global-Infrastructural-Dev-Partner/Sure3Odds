package com.gidp.sure3odds.repository.games;

import com.gidp.sure3odds.entity.games.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.games.Comments;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findCommentsByGameIDOrderByTime(Games gameID);
}
