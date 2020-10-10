package com.gidp.sure3odds.controller.users;

import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.NewUser;
import com.gidp.sure3odds.entity.users.UserTypes;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.service.users.AuthenticationService;
import com.gidp.sure3odds.service.users.UserTypesService;
import com.gidp.sure3odds.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RequestMapping("/sure3odds")
@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UserTypesService userTypesService;


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

    @DeleteMapping(value = "/users/usertype/delete/{id}")
    ResponseEntity<?> deleteUserType(@PathVariable Long id) {
        BaseResponse response = userTypesService.DeleteUserType(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/user/type/get")
    ResponseEntity<?> getUsersByUserTypeID(@RequestParam Long usertypeId, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = usersService.GetUsersByUserTypID(usertypeId, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    
    @GetMapping(value = "/users/user/type/search")
    ResponseEntity<?> searchUsersByUserTypeID(@RequestParam String searchValue, @RequestParam long usertypeId, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = usersService.SearchUsersByUserTypID(usertypeId, searchValue, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/user/get")
    ResponseEntity<?> getUserDetails(@RequestAttribute("UserID") Long UserID) {
        BaseResponse response = usersService.GetUserDetailsByID(UserID);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/user/find/{id}")
    ResponseEntity<?> findUserDetails(@PathVariable long id) {
        BaseResponse response = usersService.GetUserDetailsByID(id);
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

    @PostMapping(value = "/users/member/create")
    ResponseEntity<?> createMember(@RequestBody NewUser user) throws IOException {
        BaseResponse response = usersService.CreateNewUser(user);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/users/member/add")
    ResponseEntity<?> addMember(@RequestBody NewUser user) throws IOException {
        BaseResponse response = usersService.CreateNewUser(user);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/users/member/delete/{id}")
    ResponseEntity<?> deleteMember(@PathVariable Long id) {
        BaseResponse response = usersService.DeleteMember(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/member/authenticateuser")
    ResponseEntity<?> authenticateUser(@RequestParam() String email, @RequestParam() String password) {
        BaseResponse response = authenticationService.userLogin(email, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping(value = "/users/subadmin/create")
    ResponseEntity<?> CreateSubAdmin(@RequestBody Users user) {
        BaseResponse response = usersService.CreateSubAdmin(user);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "/users/subadmin/delete/{id}")
    ResponseEntity<?> deleteSubAdmin(@PathVariable Long id) {
        BaseResponse response = usersService.DeleteSubAdmin(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/users/user/forgot_password")
    ResponseEntity<?> ForgotPassword(@RequestParam String Email, @RequestParam String Phone, @RequestParam String NewPassword) {
        BaseResponse response = null;
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/users/report/general/get")
    ResponseEntity<?> getAppReports() {
        BaseResponse response = usersService.GetAppReports();
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/users/report/monthly/get")
    ResponseEntity<?> getMonthlyReports(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate) {
        BaseResponse response = usersService.GetMonthlyReports(selectedDate);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/app/getversion")
    ResponseEntity<?> getAppVersion() {
        BaseResponse response = usersService.GetAppVersion();
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
