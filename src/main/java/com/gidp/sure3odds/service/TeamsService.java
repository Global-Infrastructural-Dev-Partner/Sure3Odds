package com.gidp.sure3odds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidp.sure3odds.repository.TeamsRepository;

@Service
public class TeamsService {

	@Autowired
	TeamsRepository teamsRepository;
}
