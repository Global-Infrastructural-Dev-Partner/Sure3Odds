package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.Countries;
import com.gidp.sure3odds.entity.games.Leagues;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.repository.games.CountriesRepository;
import com.gidp.sure3odds.repository.games.LeaguesRepository;
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
public class LeaguesService {

	
	@Autowired
	LeaguesRepository leaguesRepository;

	@Autowired
	CountriesRepository countriesRepository;

	public BaseResponse CreateAllLeagues(List<Leagues> listLeagues) {
		BaseResponse response = new BaseResponse();
		ArrayList<Object> saved_countries = new ArrayList<>();
		try {
			for (Leagues leagues : listLeagues) {
				Leagues saved_country = leaguesRepository.save(leagues);
				saved_countries.add(saved_country);
			}
		} catch (Exception e) {

		}
		if (saved_countries != null) {
			response.setData(saved_countries);
			response.setDescription("New Leagues created successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("New Leagues was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


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
			response.setDescription("No Leagues found");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse UpdateLeague(Leagues leagues) {
		BaseResponse response = new BaseResponse();
		Leagues updated_league = leaguesRepository.save(leagues);
		if (updated_league != null) {
			response.setData(updated_league);
			response.setDescription("Leagues has been updated successfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("Leagues was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}



	public BaseResponse GetAllLeagues(int pageNo, int pageSize) {
		BaseResponse response = new BaseResponse();
//		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("country_id").ascending());
//		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name").descending());
		Page<Leagues> leagues = leaguesRepository.findAll(paging);
		if (!leagues.isEmpty()) {
			response.setData(leagues);
			response.setDescription("Leagues found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}



	public BaseResponse GetLeagueByCountryID(Long countryid) {
		BaseResponse response = new BaseResponse();
		Countries countries = countriesRepository.findById(countryid).get();
		Sort sortOrder = Sort.by("name").ascending();
		List<Leagues> leagues = leaguesRepository.findByCountryOrderByName(countries, sortOrder);
		if (!leagues.isEmpty()) {
			response.setData(leagues);
			response.setDescription("Leagues found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse SearchLeagueByCountryIDAndName(Long countryId, String countryName) {
		BaseResponse response = new BaseResponse();
		Countries countries = countriesRepository.findById(countryId).get();
		Sort sortOrder = Sort.by("name").ascending();
		List<Leagues> leagues = leaguesRepository.findByNameContainingAndCountryOrderByName(countryName, countries, sortOrder);
		if (!leagues.isEmpty()) {
			response.setData(leagues);
			response.setDescription("Leagues found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse SearchLeaguesByName(String name, int pageNo, int pageSize) {
		BaseResponse response = new BaseResponse();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
		Page<Leagues> leagues = leaguesRepository.findLeaguesByNameContainingOrderByName(name, paging);
		if (!leagues.isEmpty()) {
			response.setData(leagues);
			response.setDescription("Teams found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

}
