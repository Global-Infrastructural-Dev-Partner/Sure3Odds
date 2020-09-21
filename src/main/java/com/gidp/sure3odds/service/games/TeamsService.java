package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.Leagues;
import com.gidp.sure3odds.entity.games.Teams;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.repository.games.LeaguesRepository;
import com.gidp.sure3odds.repository.games.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {

	@Autowired
	TeamsRepository teamsRepository;

	@Autowired
	LeaguesRepository leaguesRepository;


	public BaseResponse CreateAllTeams(List<Teams> listTeams) {
		BaseResponse response = new BaseResponse();
		ArrayList<Object> saved_teams = new ArrayList<>();
		try {


			for (Teams team : listTeams) {
				Teams saved_team = teamsRepository.save(team);
				saved_teams.add(saved_team);
			}
		} catch (Exception e) {

		}
		if (saved_teams != null) {
			response.setData(saved_teams);
			response.setDescription("New Leagues created successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("New Leagues was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse CreateTeam(Teams teams) {
		BaseResponse response = new BaseResponse();
		Teams saved_team = teamsRepository.save(teams);
		if(saved_team != null) {
			response.setData(saved_team);
			response.setDescription("New Team created successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("New Team was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse DeleteTeam(long teamID) {
		BaseResponse response = new BaseResponse();
		Optional<Teams> team = teamsRepository.findById(teamID);
		if(team.isPresent()) {
			teamsRepository.deleteById(teamID);
			response.setDescription("Team deleted successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("No results found");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse UpdateTeam(Teams teams) {
		BaseResponse response = new BaseResponse();
		Teams updated_team = teamsRepository.save(teams);
		if (updated_team != null) {
			response.setData(updated_team);
			response.setDescription("Team has been updated succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("Team was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse GetAllTeams(int pageNo, int pageSize) {
		BaseResponse response = new BaseResponse();
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Teams> teams = teamsRepository.findAll(paging);
		if (!teams.isEmpty()) {
			response.setData(teams);
			response.setDescription("Team found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse SearchTeamsByName(String name, int pageNo, int pageSize ) {
		BaseResponse response = new BaseResponse();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
		Page<Teams> teams = teamsRepository.findTeamsByNameContainingOrderByName(name, paging);
		if (!teams.isEmpty()) {
			response.setData(teams);
			response.setDescription("Teams found successfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}
	public BaseResponse SearchTeamsByLeagueIdAndName(Long leagueId, String leagueName, int pageNo, int pageSize) {
		BaseResponse response = new BaseResponse();
		Leagues leagues = leaguesRepository.findById(leagueId).get();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name") .ascending());
		Page<Teams> teams = teamsRepository.findByNameContainingAndLeagueOrderByName(leagueName, leagues, paging);
		if (!teams.isEmpty()) {
			response.setData(teams);
			response.setDescription("Teams found successfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse GetTeamsByLeagueID(Long leagueId, int pageNo, int pageSize) {
		BaseResponse response = new BaseResponse();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
		Leagues leagues = leaguesRepository.findById(leagueId).get();
		Page<Teams> teams = teamsRepository.findByLeagueOrderByName(leagues, paging);
		if (!teams.isEmpty()) {
			response.setData(teams);
			response.setDescription("Teams found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}



	public BaseResponse getTeamsByLeagueID(Long leagueId) {
		BaseResponse response = new BaseResponse();
		Leagues leagues = leaguesRepository.findById(leagueId).get();
		List<Teams> teams = teamsRepository.findByLeagueOrderByName(leagues);
		if (!teams.isEmpty()) {
			response.setData(teams);
			response.setDescription("Teams found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

}
