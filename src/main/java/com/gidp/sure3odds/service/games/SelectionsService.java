package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.Selections;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.repository.games.SelectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class SelectionsService {

	@Autowired
	SelectionsRepository selectionsRepository;

	public BaseResponse CreateSelection(Selections selections) {
		BaseResponse response = new BaseResponse();
		Selections saved_selections = selectionsRepository.save(selections);
		if(saved_selections != null) {
			response.setData(saved_selections);
			response.setDescription("New Selection created successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("New Selection was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse DeleteSelection(long selectionid) {
		BaseResponse response = new BaseResponse();
		Optional<Selections> selections = selectionsRepository.findById(selectionid);
		if(selections.isPresent()) {
			selectionsRepository.deleteById(selectionid);
			response.setDescription("Selection deleted successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("No Selections found");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse UpdateSelection(Selections selections) {
		BaseResponse response = new BaseResponse();
		if (selectionsRepository.existsById(selections.getId())) {
			Selections updated_selection = selectionsRepository.save(selections);
			response.setData(updated_selection);
			response.setDescription("Selection has been updated succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("Selection was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse GetAllSelections(int pageNo, int pageSize) {
		BaseResponse response = new BaseResponse();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());
		Page<Selections> selections = selectionsRepository.findAll(paging);
		if (!selections.isEmpty()) {
			response.setData(selections);
			response.setDescription("Selections found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

	public BaseResponse SearchSelectionsByName(String name, int pageNo, int pageSize ) {
		BaseResponse response = new BaseResponse();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
		Page<Selections> selections = selectionsRepository.findSelectionsByNameContainingOrderByName(name, paging);
		if (!selections.isEmpty()) {
			response.setData(selections);
			response.setDescription("Selections found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse getAllSelections() {
		BaseResponse response = new BaseResponse();
		List<Selections> selections = selectionsRepository.findAll();
		if (!selections.isEmpty()) {
			response.setData(selections);
			response.setDescription("Selections found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

}
