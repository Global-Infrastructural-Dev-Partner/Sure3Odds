package com.gidp.sure3odds.service;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.Leagues;
import com.gidp.sure3odds.repository.LeaguesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class LeaguesService {

	
	@Autowired
	LeaguesRepository leaguesRepository;


	public BaseResponse CreateLeague(Leagues leagues) {
		BaseResponse response = new BaseResponse();
		Leagues saved_league = leaguesRepository.save(leagues);
		if(saved_league != null) {
			response.setData(saved_league);
			response.setDescription("New Leagues created successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("New Leagues was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse DeleteLeague(long leagueID) {
		BaseResponse response = new BaseResponse();
		Optional<Leagues> leagues = leaguesRepository.findById(leagueID);
		if(leagues.isPresent()) {
			leaguesRepository.deleteById(leagueID);
			response.setDescription("League deleted successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("No League found");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse UpdateLeague(Leagues leagues) {
		BaseResponse response = new BaseResponse();
		Leagues updated_league = leaguesRepository.save(leagues);
		if (updated_league != null) {
			response.setData(updated_league);
			response.setDescription("Leagues has been updated succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("Leagues was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse GetAllLeagues() {
		BaseResponse response = new BaseResponse();
		List<Leagues> leagues = leaguesRepository.findAll();
		if (!leagues.isEmpty()) {
			response.setData(leagues);
			response.setDescription("League found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse GetLeagueByID(Long id) {
		BaseResponse response = new BaseResponse();
		Optional<Leagues> leagues = leaguesRepository.findById(id);
		if (leagues.isPresent()) {
			response.setData(leagues);
			response.setDescription("League found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse GetLeagueByCountryID(Long countryid) {
		BaseResponse response = new BaseResponse();
		List<Leagues> leagues = leaguesRepository.findLeaguesByCountryID(countryid);
		if (!leagues.isEmpty()) {
			response.setData(leagues);
			response.setDescription("League found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse SearchLeaguesByName(String name) {
		BaseResponse response = new BaseResponse();
		List<Leagues> leagues = leaguesRepository.findLeaguesByNameContainingOrderByName(name);
		if (!leagues.isEmpty()) {
			response.setData(leagues);
			response.setDescription("Teams found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

}
