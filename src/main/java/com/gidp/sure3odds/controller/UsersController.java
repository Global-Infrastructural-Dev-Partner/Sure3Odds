package com.gidp.sure3odds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.NewUser;
import com.gidp.sure3odds.entity.Users;
import com.gidp.sure3odds.service.UsersService;

@RequestMapping("/sure3odds")
@RestController
public class UsersController {

	@Autowired
	UsersService usersService;

	@PostMapping(value = "/create_usertype")
	ResponseEntity<?> createUserTypes(@RequestParam String name) {
		BaseResponse response = usersService.CreateUserType(name);
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/authenticate")
	ResponseEntity<?> addPrediction(@RequestParam String Email, String Password) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/register_member")
	ResponseEntity<?> registerMember(@RequestParam NewUser newUser) {
		BaseResponse response = usersService.CreateNewUser(newUser);
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/check_email")
	ResponseEntity<?> checkEmail() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_users")
	ResponseEntity<?> getUsers() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_advisers")
	ResponseEntity<?> getAdvisers() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/update_user")
	ResponseEntity<?> updateUser(@RequestParam Users users) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/assign_member_adviser")
	ResponseEntity<?> assignMemberAdviser(@RequestParam NewUser newUser) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/delete_member_adviser/")
	ResponseEntity<?> deleteMemberAdviser(@RequestParam Long MemberUserID, Long AdviserUserID) {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_user_details")
	ResponseEntity<?> getUserDetails() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_app_stats")
	ResponseEntity<?> getAppStats() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_monthly_stats")
	ResponseEntity<?> getMonthlyStats() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/search_user")
	ResponseEntity<?> searchUser() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
