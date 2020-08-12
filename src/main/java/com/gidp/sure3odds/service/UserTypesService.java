package com.gidp.sure3odds.service;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.UserTypes;
import com.gidp.sure3odds.repository.UserTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class UserTypesService {

	@Autowired
	UserTypesRepository userTypesRepository;

	public BaseResponse CreateUserType(UserTypes usertype) {
		BaseResponse response = new BaseResponse();
		UserTypes usertypes = userTypesRepository.save(usertype);
		response.setData(usertypes);
		response.setDescription("user created successfully");
		response.setStatusCode(HttpServletResponse.SC_OK);

		return response;

	}
	
	public BaseResponse getUserTypes() {
		BaseResponse response = new BaseResponse();
		List<UserTypes>  usertypes = userTypesRepository.findAll();
		if (!usertypes.isEmpty()) {
			response.setData(usertypes);
			response.setDescription("usertypes found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}
	
	
	public BaseResponse DeleteUserType(Long UsertypeID) {
		BaseResponse response = new BaseResponse();
		Optional<UserTypes>  usertypes = userTypesRepository.findById(UsertypeID);
		if (usertypes.isPresent()) {
			userTypesRepository.deleteById(UsertypeID);
			response.setDescription("Usertype has been deleted successfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No record found for the specified id");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

	
}
