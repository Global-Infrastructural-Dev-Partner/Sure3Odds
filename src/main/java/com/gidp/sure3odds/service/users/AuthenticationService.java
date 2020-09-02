package com.gidp.sure3odds.service.users;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.helper.AppHelper;
import com.gidp.sure3odds.helper.JWTHelper;
import com.gidp.sure3odds.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthenticationService {

    private static final Logger LOG = Logger.getLogger(AuthenticationService.class.getName());

    @Autowired
    JWTHelper jwtHelper;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    AppHelper helper = new AppHelper();

    @Autowired
    UsersService usersService;

    /**
     * Generate jwt
     *
     * @param user
     * @return
     */
    public BaseResponse generateToken(Users user) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        baseResponse.setDescription("An error has occured, please try again");
        try {
            String token = jwtHelper.createToken(user);
            baseResponse.setStatusCode(HttpStatus.OK.value());
            baseResponse.setDescription("Successful");
            Map object = new HashMap();
            object.put("token", token);
            baseResponse.setData(object);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }

        return baseResponse;
    }

    /**
     * validate user login
     *
     * @param email
     * @param password
     * @return
     */
    public BaseResponse userLogin(String email, String password) {
        BaseResponse baseResponse = new BaseResponse();
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String description = "An error has occured, please try again";
        try {
            Optional<Users> isUser = usersRepository.findByEmail(email.toLowerCase());
            statusCode = HttpStatus.BAD_REQUEST.value();
            if (!isUser.isPresent()) {
                baseResponse.setStatusCode(statusCode);
                baseResponse.setDescription("Invalid Email");
                return baseResponse;
            }
            Users user = isUser.get();
            if (!passwordEncoder.matches(password, user.getPassword())) {
                baseResponse.setStatusCode(statusCode);
                baseResponse.setDescription("Invalid password");
                return baseResponse;
            }
            return generateToken(user);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }

        baseResponse.setStatusCode(statusCode);
        baseResponse.setDescription(description);
        return baseResponse;
    }

    /**
     * validate jwt token
     *
     * @param token
     * @return
     */
    public BaseResponse validateToken(String token) {
        DecodedJWT decodedJwt = jwtHelper.validateToken(token);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        baseResponse.setDescription("Invalid access token");
        if (decodedJwt != null) {
            baseResponse.setStatusCode(HttpStatus.OK.value());
            baseResponse.setDescription("Successful");
            // other check e.g authorization
        }

        return baseResponse;
    }


    public String getUserID(String token){
        DecodedJWT decodedJwt = jwtHelper.getUserId(token);
        String userid = "none";
        if(decodedJwt != null){
            userid =   decodedJwt.getId();
        }
        return userid;
    }
}
