package com.gidp.sure3odds.service.users;

import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Parameters;
import com.gidp.sure3odds.repository.users.ParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class ParametersService {

	@Autowired
	ParametersRepository parametersRepository;


	public BaseResponse CreateParameter(Parameters parameters) {
		BaseResponse response = new BaseResponse();
		Parameters saved_parameters = parametersRepository.save(parameters);
		if(saved_parameters != null) {
			response.setData(saved_parameters);
			response.setDescription("New Parameter created successfully");
			response.setStatusCode(HttpServletResponse.SC_CREATED);
		}else {
			response.setDescription("New plan type was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}


	public BaseResponse DeleteParameter(long id) {
		BaseResponse response = new BaseResponse();
		Optional<Parameters> parameters = parametersRepository.findById(id);
		if(parameters.isPresent()) {
			parametersRepository.deleteById(id);
			response.setDescription("Parameter deleted successfully");
			response.setStatusCode(HttpServletResponse.SC_CREATED);
		}else {
			response.setDescription("No Plan type found");
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
		}
		return response;

	}


	public BaseResponse UpdateParametere(Parameters parameters) {
		BaseResponse response = new BaseResponse();
		Parameters updated_parameter = parametersRepository.save(parameters);
		if (updated_parameter != null) {
			response.setData(updated_parameter);
			response.setDescription("Parameter has been updated succesfully.");
			response.setStatusCode(HttpServletResponse.SC_CREATED);
		} else {
			response.setDescription("Parameter was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}


	public BaseResponse GetAllParameters() {
		BaseResponse response = new BaseResponse();
		List<Parameters> parameters = parametersRepository.findAll();
		if (!parameters.isEmpty()) {
			response.setData(parameters);
			response.setDescription("plan types found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
		}
		return response;

	}

	public BaseResponse GetParameterByID(Long id) {
		BaseResponse response = new BaseResponse();
		Optional<Parameters> parameters = parametersRepository.findById(id);
		if (parameters.isPresent()) {
			response.setData(parameters);
			response.setDescription("Parameter found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
		}
		return response;

	}
}
