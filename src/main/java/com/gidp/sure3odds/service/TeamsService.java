package com.gidp.sure3odds.service;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.Leagues;
import com.gidp.sure3odds.entity.Teams;
import com.gidp.sure3odds.repository.LeaguesRepository;
import com.gidp.sure3odds.repository.TeamsRepository;
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
				Optional<Leagues> leagues = leaguesRepository.findById(team.getLeagueID().getId());

				team.setLeagueID(leagues.get());
				team.setCountryID(leagues.get().getCountryID());
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
	public BaseResponse CreateLeagueTeams(List<Teams> listTeams) {
		BaseResponse response = new BaseResponse();
		ArrayList<Object> saved_teams = new ArrayList<>();
		try {

			Optional<Leagues> leagues = leaguesRepository.findById(796l);

			for (Teams teams : listTeams) {
                if (!checkTeamExist(leagues.get().getId(), teams.getName())) {
					teams.setLeagueID(leagues.get());
					teams.setCountryID(leagues.get().getCountryID());
					Teams saved_team = teamsRepository.save(teams);
					saved_teams.add(saved_team);
                }

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
}
