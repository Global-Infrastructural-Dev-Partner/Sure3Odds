package com.gidp.sure3odds.controller.users;

import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.MemberAdvisers;
import com.gidp.sure3odds.entity.users.NewUser;
import com.gidp.sure3odds.entity.users.UserTypes;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.service.users.AuthenticationService;
import com.gidp.sure3odds.service.users.MemberAdvisersService;
import com.gidp.sure3odds.service.users.UserTypesService;
import com.gidp.sure3odds.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RequestMapping("/sure3odds")
@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UserTypesService userTypesService;

    @Autowired
    MemberAdvisersService memberAdvisersService;

    @Autowired
    AuthenticationService authenticationService;

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
    ResponseEntity<?> getUsersByUserTypeID(@RequestParam Long id) {
        BaseResponse response = usersService.GetUsersByUserTypID(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/user/get_by_userid")
    ResponseEntity<?> getUserDetails(@RequestAttribute("UserID") Long UserID) {
        BaseResponse response = usersService.GetUserDetailsByID(UserID);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/users/user/update")
    ResponseEntity<?> updateUser(@RequestBody Users users) {
        BaseResponse response = usersService.UpdateUser(users);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "users/user/search")
    ResponseEntity<?> searchUser(@RequestParam String searchvalue, @RequestParam Long usertypeid) {
        BaseResponse response = usersService.searchByFirstNameOrLastName(usertypeid, searchvalue);
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

    @PostMapping(value = "/users/member/authenticate")
    ResponseEntity<?> authenticate(@RequestParam String email, @RequestParam String password) {
        BaseResponse response = authenticationService.userLogin(email, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping(value = "users/advisers/create")
    ResponseEntity<?> createAdviser(@RequestBody Users user) {
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

    @GetMapping(value = "/users/members/adviserdetails_by_memeruserid")
    ResponseEntity<?> getAdviserDetailsByMemberUserID(@RequestAttribute("UserID") Long UserID) {
        BaseResponse response = memberAdvisersService.GetAdviserDetailsByMemberID(UserID);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/advisers/members/getall")
    ResponseEntity<?> getMembersByAdviserUserID(@RequestAttribute("UserID") Long UserID) {
        BaseResponse response = memberAdvisersService.GetMembersByAdviserUserID(UserID);
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


	@GetMapping(value = "users/app/get_app_reports")
	ResponseEntity<?> getAppReports() {
		BaseResponse response = usersService.GetAppReports();
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

    /**
     * @param selectedDate
     * @return
     */
	@GetMapping(value = "users/app/get_monthly_reports")
	ResponseEntity<?> getMonthlyReports(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) Date selectedDate) {
		BaseResponse response = usersService.GetMonthlyReports(selectedDate);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
//


}
