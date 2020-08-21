package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.Selections;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.repository.games.SelectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
			response.setDescription("New Selections created successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("New Selections was not created.");
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
			response.setDescription("No Set found");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse UpdateSelection(Selections selections) {
		BaseResponse response = new BaseResponse();
		Selections updated_selection = selectionsRepository.save(selections);
		if (updated_selection != null) {
			response.setData(updated_selection);
			response.setDescription("Selections has been updated succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("Selections was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse GetAllSelections() {
		BaseResponse response = new BaseResponse();
		List<Selections> selections = selectionsRepository.findAll();
		if (!selections.isEmpty()) {
			response.setData(selections);
			response.setDescription("Sets found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

}
