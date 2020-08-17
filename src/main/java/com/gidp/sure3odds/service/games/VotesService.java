package com.gidp.sure3odds.service.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidp.sure3odds.repository.games.VotesRepository;

@Service
public class VotesService {

	@Autowired
	VotesRepository votesRepository;
}
