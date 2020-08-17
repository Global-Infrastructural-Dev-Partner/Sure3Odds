package com.gidp.sure3odds.service.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidp.sure3odds.repository.games.CommentsRepository;

@Service
public class CommentsService {

	@Autowired
	CommentsRepository commentsRepository;
	
}
