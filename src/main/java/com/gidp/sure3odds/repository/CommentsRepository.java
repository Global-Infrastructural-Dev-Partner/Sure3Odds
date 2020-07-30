package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gidp.sure3odds.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
