package com.gidp.sure3odds.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.PlanTypes;
import com.gidp.sure3odds.repository.PlanTypesRepository;

@Service
public class PlanTypesService {

	@Autowired
	PlanTypesRepository planTypesRepository;
	

	public BaseResponse CreatePlanTypes(PlanTypes plantype) {
		BaseResponse response = new BaseResponse();
		PlanTypes plantypes = planTypesRepository.save(plantype);
		if(plantype != null) {
			response.setData(plantypes);
			response.setDescription("New Plan type created successfully");
			response.setStatusCode(HttpServletResponse.SC_CREATED);
		}else {
			response.setDescription("New plan type was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}
	

	public BaseResponse DeletePlanTypes(long plantypeid) {
		BaseResponse response = new BaseResponse();
		Optional<PlanTypes> plantype = planTypesRepository.findById(plantypeid);
		if(plantype.isPresent()) {
			planTypesRepository.deleteById(plantypeid);
			response.setDescription("Plan type deleted successfully");
			response.setStatusCode(HttpServletResponse.SC_CREATED);
		}else {
			response.setDescription("No Plan type found");
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
		}
		return response;

	}
	
	
	public BaseResponse UpdatePlantype(PlanTypes plantype) {
		BaseResponse response = new BaseResponse();
		PlanTypes plantypes = planTypesRepository.save(plantype);
		if (plantypes != null) {
			response.setData(plantypes);
			response.setDescription("Plan type has been updated succesfully.");
			response.setStatusCode(HttpServletResponse.SC_CREATED);
		} else {
			response.setDescription("Plan type was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}
	

	public BaseResponse GetAllPlantypes() {
		BaseResponse response = new BaseResponse();
		List<PlanTypes> plantypes = planTypesRepository.findAll();
		if (!plantypes.isEmpty()) {
			response.setData(plantypes);
			response.setDescription("plan types found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
		}
		return response;

	}
	
	public BaseResponse GetPlantype(Long id) {
		BaseResponse response = new BaseResponse();
		Optional<PlanTypes> plantypes = planTypesRepository.findById(id);
		if (plantypes.isPresent()) {
			response.setData(plantypes);
			response.setDescription("plan type found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
		}
		return response;

	}

}
