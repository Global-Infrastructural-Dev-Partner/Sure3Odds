package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.Countries;
import com.gidp.sure3odds.entity.games.Leagues;
import com.gidp.sure3odds.entity.games.Teams;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.repository.games.CountriesRepository;
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
public class CountriesService {


    @Autowired
    CountriesRepository countriesRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    LeaguesRepository leaguesRepository;


    public BaseResponse CreateAllCountry(List<Countries> listContries) {
        BaseResponse response = new BaseResponse();
        ArrayList<Object> saved_countries = new ArrayList<>();
        try {
            for (Countries country : listContries) {
                Countries saved_country = countriesRepository.save(country);
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


    public BaseResponse CreateCountry(Countries countries) {
        BaseResponse response = new BaseResponse();
        Countries saved_country = countriesRepository.save(countries);
        if (saved_country != null) {
            response.setData(saved_country);
            response.setDescription("New Country created successfully");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("New Country was not created.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }


    public BaseResponse DeleteCountry(long countryID) {
        BaseResponse response = new BaseResponse();
        Optional<Countries> countries = countriesRepository.findById(countryID);
        if (countries.isPresent()) {
            List<Teams> teams = teamsRepository.findByCountry(countries.get());
            if(!teams.isEmpty()){
                teamsRepository.deleteAll(teams);
            }

            List<Leagues> leagues = leaguesRepository.findByCountry(countries.get());
            if(!leagues.isEmpty()){
                leaguesRepository.deleteAll(leagues);
            }
            countriesRepository.deleteById(countryID);
            response.setDescription("Country deleted successfully");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No Country found");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }


    public BaseResponse UpdateCountry(Countries countries) {
        BaseResponse response = new BaseResponse();
        try {
            if (countriesRepository.existsById(countries.getId())) {
                Countries updated_league = countriesRepository.save(countries);
                response.setData(updated_league);
                response.setDescription("Country has been updated successfully.");
                response.setStatusCode(HttpServletResponse.SC_OK);
            } else {
                response.setDescription("Country was not updated.");
                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception ex) {

        }
        return response;
    }


    public BaseResponse GetAllCountries(int pageNo, int pageSize) {
        BaseResponse response = new BaseResponse();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());
        Page<Countries> countries = countriesRepository.findAll(paging);
        if (!countries.isEmpty()) {
            response.setData(countries);
            response.setDescription("Countries found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }


    public BaseResponse SearchCountries(String name, int pageNo, int pageSize) {
        BaseResponse response = new BaseResponse();
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Countries> countries = countriesRepository.findCountriesByNameContainingOrderByName(name, paging);
        if (!countries.isEmpty()) {
            response.setData(countries);
            response.setDescription("Countries found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse GetCountries() {
        BaseResponse response = new BaseResponse();
        Sort sortOrder = Sort.by("name").ascending();
        List<Countries> countries = countriesRepository.findAll(sortOrder);
        if (!countries.isEmpty()) {
            response.setData(countries);
            response.setDescription("Countries found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }



}
