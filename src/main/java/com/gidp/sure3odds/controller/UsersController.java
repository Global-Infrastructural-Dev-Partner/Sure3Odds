package com.gidp.sure3odds.controller;

import com.gidp.sure3odds.entity.*;
import com.gidp.sure3odds.service.MemberAdvisersService;
import com.gidp.sure3odds.service.UserTypesService;
import com.gidp.sure3odds.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/sure3odds")
@RestController
public class UsersController {

	@Autowired
	UsersService usersService;

	@Autowired
	UserTypesService userTypesService;
	
	@Autowired
	MemberAdvisersService memberAdvisersService;

	@PostMapping(value = "/users/usertype/create")
	ResponseEntity<?> createUserTypes(@RequestBody UserTypes userType) {
		BaseResponse response = userTypesService.CreateUserType(userType);
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/users/usertype/getall")
	ResponseEntity<?> getUserTypes() {
		BaseResponse response = userTypesService.getUserTypes();
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/users/getall")
	ResponseEntity<?> getAllUsers() {
		BaseResponse response = usersService.GetAllUsers();
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping(value = "users/usertype/delete/{id}")
	ResponseEntity<?> deleteUserType(@PathVariable Long id) {
		BaseResponse response = userTypesService.DeleteUserType(id);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "/users/get_by_usertypeid/{id}")
	ResponseEntity<?> getUsersByUserTypeID(@PathVariable Long id) {
		BaseResponse response = usersService.GetUsersByUserTypID(id);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/users/get_by_id/{id}")
	ResponseEntity<?> getUserID(@PathVariable Long id) {
		BaseResponse response = usersService.GetUserDetailsByID(id);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/users/update")
	ResponseEntity<?> updateUser(@RequestBody Users users) {
		BaseResponse response = usersService.UpdateUser(users);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/users/authenticate")
	ResponseEntity<?> authenticate(@RequestParam String Email, @RequestParam String Password) {
		BaseResponse response = null;
		//check the user duedate
		//update the user status
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}


	@PostMapping(value = "/users/members/create")
	ResponseEntity<?> createMember(@RequestBody NewUser user) throws IOException {
		BaseResponse response = usersService.CreateNewUser(user);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "users/search")
	ResponseEntity<?> searchUser(@RequestParam String searchvalue, @RequestParam Long usertypeid) {
		BaseResponse response = usersService.searchByFirstNameOrLastName(usertypeid, searchvalue);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "users/advisers/create")
	ResponseEntity<?> createAdviserr(@RequestBody NewUser user) {
		BaseResponse response = usersService.CreateAdviser(user);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/users/member_advisers/assign")
	ResponseEntity<?> assignMemberAdviser(@RequestBody MemberAdvisers memberAdvisers) {
		BaseResponse response = memberAdvisersService.AssignMemberAdviser(memberAdvisers);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/users/member_advisers/delete/{id}")
	ResponseEntity<?> deleteMemberAdviser(@PathVariable Long id) {
		BaseResponse response = memberAdvisersService.DeleteMemberAdviser(id);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/users/member_advisers/update")
	ResponseEntity<?> updateMemberAdviser(@RequestBody MemberAdvisers memberAdvisers) {
		BaseResponse response = memberAdvisersService.UpdateMemberAdviser(memberAdvisers);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/users/members/adviserdetails_by_memeruserid/{id}")
	ResponseEntity<?> getAdviserDetailsByMemberUserID(@PathVariable Long id) {
		BaseResponse response = memberAdvisersService.GetAdviserDetailsByMemberID(id);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/users/advisers/members/getall/{id}")
	ResponseEntity<?> getMembersByAdviserUserID(@PathVariable Long id) {
		BaseResponse response = memberAdvisersService.GetMembersByAdviserUserID(id);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
//	@PostMapping(value = "/forgot_password")
//	ResponseEntity<?> ForgotPassword(@RequestParam String Email, @RequestParam String Phone, @RequestParam String NewPassword) {
//		BaseResponse response = null;
//		if (response.getStatusCode() == 200) {
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
//	}


//	@GetMapping(value = "/get_app_stats")
//	ResponseEntity<?> getAppStats() {
//		BaseResponse response = null;
//		if (response.getStatusCode() == 200) {
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
//	}
//
//	@GetMapping(value = "/get_monthly_stats")
//	ResponseEntity<?> getMonthlyStats() {
//		BaseResponse response = null;
//		if (response.getStatusCode() == 200) {
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
//	}
//


}
