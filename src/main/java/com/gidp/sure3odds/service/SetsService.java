package com.gidp.sure3odds.service;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidp.sure3odds.repository.SetsRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class SetsService {

	@Autowired
	SetsRepository setsRepository;

	public BaseResponse CreateSet(Sets sets) {
		BaseResponse response = new BaseResponse();
		Sets saved_sets = setsRepository.save(sets);
		if(saved_sets != null) {
			response.setData(saved_sets);
			response.setDescription("New Set created successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("New Set was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse DeleteSet(long setid) {
		BaseResponse response = new BaseResponse();
		Optional<Sets> sets = setsRepository.findById(setid);
		if(sets.isPresent()) {
			setsRepository.deleteById(setid);
			response.setDescription("Set deleted successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("No Set found");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse UpdateSet(Sets sets) {
		BaseResponse response = new BaseResponse();
		Sets updated_set = setsRepository.save(sets);
		if (updated_set != null) {
			response.setData(updated_set);
			response.setDescription("Set has been updated succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("Set was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse GetAllSets() {
		BaseResponse response = new BaseResponse();
		List<Sets> sets = setsRepository.findAll();
		if (!sets.isEmpty()) {
			response.setData(sets);
			response.setDescription("Sets found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse GetSetByID(Long id) {
		BaseResponse response = new BaseResponse();
		Optional<Sets> set = setsRepository.findById(id);
		if (set.isPresent()) {
			response.setData(set);
			response.setDescription("Set found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}
}
