package com.gidp.sure3odds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidp.sure3odds.repository.LeaguesRepository;

@Service
public class LeaguesService {

	
	@Autowired
	LeaguesRepository leaguesRepository;
}
