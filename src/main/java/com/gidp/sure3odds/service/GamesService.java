package com.gidp.sure3odds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidp.sure3odds.repository.GamesRepository;

@Service
public class GamesService {

	@Autowired
	GamesRepository gamesRepository;
	
}
