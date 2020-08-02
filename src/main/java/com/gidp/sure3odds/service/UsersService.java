package com.gidp.sure3odds.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.NewUser;
import com.gidp.sure3odds.entity.UserTypes;
import com.gidp.sure3odds.repository.UserTypesRepository;
import com.gidp.sure3odds.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserTypesRepository userTypesRepository;

	public BaseResponse CreateNewUser(NewUser newUser) {
		BaseResponse response = new BaseResponse();
//		Users user = new Users(newUser.gete, phone, password, datejoined, status, device_token, assigned)

//		UserTypes userType = newUser.setUsertypeID(usertypeID);
//		UserGroup new_group = userGroupRepo.save(group);
//		User user = new User(newUser.getFirstName(), newUser.getLastName(), newUser.getPassword());
//		user.setUser_group(new_group);
//		User saved_user = userRepo.save(user);
//		Member member = new Member(new Date(), new Date(), newUser.getGender());
//		member.setUser(saved_user);
//		Member created_member = memberService.createMember(member);

//		response.setData(created_member);
		response.setDescription("user created successfully");
		response.setStatusCode(HttpServletResponse.SC_CREATED);

		return response;

	}

	public BaseResponse CreateUserType(String name) {
		BaseResponse response = new BaseResponse();
		UserTypes usertypes = new UserTypes(name);
		usertypes = userTypesRepository.save(usertypes);
		response.setData(usertypes);
		response.setDescription("user created successfully");
		response.setStatusCode(HttpServletResponse.SC_CREATED);

		return response;

	}
}
