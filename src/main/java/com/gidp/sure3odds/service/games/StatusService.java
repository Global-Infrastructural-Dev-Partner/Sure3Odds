package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.Status;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.repository.games.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

	@Autowired
	StatusRepository statusRepository;

	public BaseResponse CreateStatus(Status status) {
		BaseResponse response = new BaseResponse();
		Status saved_status = statusRepository.save(status);
		if(saved_status != null) {
			response.setData(saved_status);
			response.setDescription("New Status created successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("New Status was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse DeleteStatus(long statusId) {
		BaseResponse response = new BaseResponse();
		Optional<Status> status = statusRepository.findById(statusId);
		if(status.isPresent()) {
			statusRepository.deleteById(statusId);
			response.setDescription("Status deleted successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("No results found");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse UpdateStatus(Status status) {
		BaseResponse response = new BaseResponse();
		if (statusRepository.existsById(status.getId())) {
			Status updated_status = statusRepository.save(status);
			response.setData(updated_status);
			response.setDescription("Status has been updated succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("Status was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse GetAllStatuses(String statusType) {
		BaseResponse response = new BaseResponse();
		List<Status> statuses = statusRepository.findByType(statusType);
		if (!statuses.isEmpty()) {
			response.setData(statuses);
			response.setDescription("Statuses found successfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

	public BaseResponse SearchStatusByName(String name ) {
		BaseResponse response = new BaseResponse();
		List<Status> status = statusRepository.findByNameContainingOrderByName(name);
		if (!status.isEmpty()) {
			response.setData(status);
			response.setDescription("Status found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No results found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


}
