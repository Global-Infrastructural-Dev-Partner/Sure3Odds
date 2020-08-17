package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.games.Countries;
import com.gidp.sure3odds.repository.games.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountriesService {


    @Autowired
    CountriesRepository countriesRepository;


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
        Countries updated_league = countriesRepository.save(countries);
        if (updated_league != null) {
            response.setData(updated_league);
            response.setDescription("Country has been updated succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("Country was not updated.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }


    public BaseResponse GetAllCountries() {
        BaseResponse response = new BaseResponse();
        List<Countries> countries = countriesRepository.findAll();
        if (!countries.isEmpty()) {
            response.setData(countries);
            response.setDescription("Countries found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse GetCountryByID(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<Countries> countries = countriesRepository.findById(id);
        if (countries.isPresent()) {
            response.setData(countries);
            response.setDescription("Country found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }



    public BaseResponse SearchCountries(String name) {
        BaseResponse response = new BaseResponse();
        List<Countries> countries = countriesRepository.findCountriesByNameContainingOrderByName(name);
        if (!countries.isEmpty()) {
            response.setData(countries);
            response.setDescription("Countries found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

}
