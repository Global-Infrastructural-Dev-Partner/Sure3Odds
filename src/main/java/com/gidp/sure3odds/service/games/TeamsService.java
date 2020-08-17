package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.games.Teams;
import com.gidp.sure3odds.repository.games.LeaguesRepository;
import com.gidp.sure3odds.repository.games.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


	public boolean checkTeamExist(long leagueID, String teamName) {
		boolean result = false;
		Optional<Teams> teams = teamsRepository.findByLeagueIDAndTeamName(leagueID, teamName);
		if (teams.isPresent()) {
			result = true;
		}
		return result;
	}

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
			leaguesRepository.deleteById(teamID);
			response.setDescription("Team deleted successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("No Team found");
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


	public BaseResponse GetAllTeams() {
		BaseResponse response = new BaseResponse();
		List<Teams> teams = teamsRepository.findAll();
		if (!teams.isEmpty()) {
			response.setData(teams);
			response.setDescription("Team found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse GetTeamByID(Long id) {
		BaseResponse response = new BaseResponse();
		Optional<Teams> team = teamsRepository.findById(id);
		if (team.isPresent()) {
			response.setData(team);
			response.setDescription("Team found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse GetTeamsByCountryID(Long countryid) {
		BaseResponse response = new BaseResponse();
		List<Teams> teams = teamsRepository.findTeamsByCountryID(countryid);
		if (!teams.isEmpty()) {
			response.setData(teams);
			response.setDescription("Teams found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse GetTeamsByLeagueID(Long leagueid) {
		BaseResponse response = new BaseResponse();
		List<Teams> teams = teamsRepository.findTeamsByLeagueID(leagueid);
		if (!teams.isEmpty()) {
			response.setData(teams);
			response.setDescription("Teams found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse SearchTeamsByName(String name) {
		BaseResponse response = new BaseResponse();
		List<Teams> teams = teamsRepository.findTeamsByNameContainingOrderByName(name);
		if (!teams.isEmpty()) {
			response.setData(teams);
			response.setDescription("Teams found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

}
