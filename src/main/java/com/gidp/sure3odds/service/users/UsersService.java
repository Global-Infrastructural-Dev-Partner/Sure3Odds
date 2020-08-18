package com.gidp.sure3odds.service.users;

import com.gidp.sure3odds.entity.payments.Payments;
import com.gidp.sure3odds.entity.payments.PlanTypes;
import com.gidp.sure3odds.entity.payments.Plans;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.NewUser;
import com.gidp.sure3odds.entity.users.UserTypes;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.helper.AppHelper;
import com.gidp.sure3odds.repository.payments.PaymentsRepository;
import com.gidp.sure3odds.repository.payments.PlanTypesRepository;
import com.gidp.sure3odds.repository.payments.PlansRepository;
import com.gidp.sure3odds.repository.users.UserTypesRepository;
import com.gidp.sure3odds.repository.users.UsersRepository;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    CloseableHttpClient httpClient = HttpClients.createDefault();

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserTypesRepository userTypesRepository;

    @Autowired
    PlanTypesRepository planTypesRepository;

    @Autowired
    PlansRepository plansRepository;

    @Autowired
    PaymentsRepository paymentsRepository;

    AppHelper appHelper = new AppHelper();

    @Autowired
    PasswordEncoder passwordEncoder;

    public BaseResponse CreateNewUser(NewUser newUser) throws IOException {
        BaseResponse response = new BaseResponse();
        if (appHelper.isValidEmail(newUser.getEmail())) {
            if (!checkEmailAddressOrPhoneNumberExist(newUser.getEmail(), newUser.getPhone())) {
                response = RegisterUser(newUser);
            } else {
                response.setDescription("user already registered");
                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setDescription("please enter a valid email");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

    public BaseResponse RegisterUser(NewUser newUser) throws IOException {
        BaseResponse response = new BaseResponse();
        String validationResult = "failed";
        if (newUser.getPlatform().equals("Android")) {
            validationResult = AndroidPaymentValidation(newUser.getReferenceCode());
        } else if (newUser.getPlatform().equals("iOS")) {
            validationResult = iOSPaymentValidation(newUser.getReferenceCode());
        }

        if (validationResult.equals("success")) {
            Long usertypeid = newUser.getUserTypeID().getId();
            Optional<UserTypes> usertype = userTypesRepository.findById(usertypeid);
            if (usertype.isPresent()) {
                Long planttypeid = newUser.getPlanTypeID().getId();
                Optional<PlanTypes> plantype = planTypesRepository.findById(planttypeid);
                if (plantype.isPresent()) {
                    String password = passwordEncoder.encode(newUser.getPassword());
                    newUser.setStatus("Active");
                    newUser.setAssigned("Pending");
                    Users user = new Users(newUser.getEmail(), newUser.getPhone(), password,
                            newUser.getFirstname(), newUser.getLastname(), newUser.getDatejoined(), newUser.getStatus(),
                            newUser.getDevice_token(), newUser.getAssigned());
                    user.setUserTypeID(usertype.get());
                    Users saved_user = usersRepository.save(user);

                    Plans plan = new Plans(newUser.getStartDate(), newUser.getEndDate());
                    plan.setUserID(saved_user);
                    plan.setPlanTypeID(plantype.get());
                    Plans saved_plan = plansRepository.save(plan);
                    Payments payment = new Payments(newUser.getPaymentdate(), newUser.getPaymenttype(),
                            newUser.getPlatform(), newUser.getReferenceCode());
                    payment.setUserID(saved_user);
                    payment.setPlanTypeID(plantype.get());
                    Payments saved_payment = paymentsRepository.save(payment);
                    response.setData(saved_user);
                    response.setDescription("user created successfully");
                    response.setStatusCode(HttpServletResponse.SC_OK);
                } else {
                    response.setDescription("Please, select a plan type found.");
                    response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                response.setDescription("Please, select a usertype");
                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setDescription("Please, select a usertype");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }

        return response;

    }

    public BaseResponse GetUsersByUserTypID(long userTypeID) {
        BaseResponse response = new BaseResponse();
        List<Users> users = usersRepository.findUsersByUserTypeID(userTypeID);
        if (!users.isEmpty()) {
            response.setData(users);
            response.setDescription("Users by user type found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public String iOSPaymentValidation(String receiptData) throws IOException {
        String result = "success";
//        try {
////	        this.http.post( 'https://buy.itunes.apple.com/verifyReceipt',
//            HttpPost request = new HttpPost("https://sandbox.itunes.apple.com/verifyReceipt");
//            JSONObject requestData = new JSONObject();
//            try {
//                requestData = new JSONObject();
//                requestData.put("receipt-data", receiptData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            StringEntity requestEntity = new StringEntity(requestData.toString());
//            request.addHeader("content-type", "application/x-www-form-urlencoded");
//            request.setEntity((HttpEntity) requestEntity);
//            CloseableHttpResponse response = httpClient.execute(request);
//            HttpEntity entity = response.getEntity();
//            StringBuilder Sbuilder = new StringBuilder();
//            if (entity != null) {
//                try {
//                    BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
//                    String line;
//                    while ((line = rd.readLine()) != null) {
//                        Sbuilder.append(line);
//                    }
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//
//            } else {
//                throw new Exception("Error Occured while connecting to paystack url");
//            }
//            result = Sbuilder.toString();
//        } catch ( Exception ex) {
//            throw new RuntimeException(ex);
//        } finally {
//            httpClient.close();
//        }
        return result;

    }

    public String AndroidPaymentValidation(String ReferenceCode) throws IOException {

        String result = "success";
//        String SecretKey = "";
//        try {
//            HttpGet newRequest = new HttpGet("https://api.paystack.co/transaction/verify/" + ReferenceCode);
//            newRequest.addHeader("Content-type", "application/json");
//            newRequest.addHeader("Authorization", "Bearer " + SecretKey);
//            newRequest.addHeader("Cache-Control", "no-cache");
//            CloseableHttpResponse response = httpClient.execute(newRequest);
//            try {
//
//                // Get HttpResponse Status             // HTTP/1.1
//                System.out.println(response.getStatusLine().getStatusCode());   // 200
//                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
//                System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK
//
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    // return it as a String
//                    String responseBody = EntityUtils.toString(entity);
//                    System.out.println(responseBody);
//
//
//
//                }
//
//            } finally {
//                response.close();
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            httpClient.close();
//        }
        return result;
    }

    public BaseResponse CreateAdviser(NewUser newUser) {
        BaseResponse response = new BaseResponse();
        Long usertypeid = newUser.getUserTypeID().getId();
        Optional<UserTypes> usertype = userTypesRepository.findById(usertypeid);
        if (usertype.isPresent()) {
            newUser.setAssigned("Pending");
            newUser.setStatus("Active");
            Users user = new Users(newUser.getEmail(), newUser.getPhone(), newUser.getPassword(),
                    newUser.getFirstname(), newUser.getLastname(), newUser.getDatejoined(), newUser.getStatus(),
                    newUser.getDevice_token(), newUser.getAssigned());
            user.setUserTypeID(usertype.get());
            Users saved_user = usersRepository.save(user);
            response.setData(saved_user);
            response.setDescription("user created successfully");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No user type found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }

        return response;

    }

    public BaseResponse GetAllUsers() {
        BaseResponse response = new BaseResponse();
        List<Users> users = usersRepository.findAll();
        if (!users.isEmpty()) {
            response.setData(users);
            response.setDescription("usertypes found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse searchByFirstNameOrLastName(Long usertypeid, String searchValue) {
        BaseResponse response = new BaseResponse();
//        List<Users> users = usersRepository.findUsersByFirstnameOrLastnameContaining(searchValue, searchValue);
        List<Users> users = usersRepository.findUsersByFirstnameOrLastnameContainingAndUserTypeID(searchValue, searchValue, usertypeid);
        ArrayList<Object> data = new ArrayList<>();
        if (!users.isEmpty()) {
//            for (Users user : users) {
//                Long usertypeID = user.getUserTypeID().getId();
//                if (usertypeID == usertypeid) {
//                    data.add(user);
//                }
//            }
            response.setData(users);
            response.setDescription("usertypes found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
//            if (!data.isEmpty()) {
//                response.setData(users);
//                response.setDescription("usertypes found succesfully.");
//                response.setStatusCode(HttpServletResponse.SC_OK);
//            } else {
//                response.setDescription("No result found.");
//                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
//            }

        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse GetUserDetailsByID(Long userid) {
        BaseResponse response = new BaseResponse();
        Users users = usersRepository.findById(userid).get();
        if (users != null) {
            response.setData(users);
            response.setDescription("user found succesfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No result found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public String GetUserTypeNameByUserID(Long userid) {
        Users users = usersRepository.findById(userid).get();
        String usertypeName = users.getUserTypeID().getName();
        return usertypeName;
    }

    public String GetUserName(Long userid) {
        Users users = usersRepository.findById(userid).get();
        String userName = users.getLastname() + " " + users.getFirstname();
        return userName;
    }

    public String GetUserEmail(Long userid) {
        Users users = usersRepository.findById(userid).get();
        String userEmail = users.getEmail();
        return userEmail;
    }

    public String GetUserPhone(Long userid) {
        Users users = usersRepository.findById(userid).get();
        String userPhone = users.getPhone();
        return userPhone;
    }

    public BaseResponse UpdateUser(Users users) {
        BaseResponse response = new BaseResponse();
        if (appHelper.isValidEmail(users.getEmail())) {
            Long userid = users.getId();
            String userEmail = GetUserEmail(userid);
            String userPhone = GetUserPhone(userid);
            if (checkEmailAddressOrPhoneNumberExist(users.getEmail(), users.getPhone())) {
                if (userEmail.equals(users.getEmail()) && userPhone.equals(users.getPhone())) {
                    Users updatedusers = usersRepository.save(users);
                    if (updatedusers != null) {
                        response.setData(updatedusers);
                        response.setDescription("User has been updated succesfully.");
                        response.setStatusCode(HttpServletResponse.SC_OK);
                    } else {
                        response.setDescription("User was not updated.");
                        response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } else {
                    response.setDescription("Email or phone number provided is in use by another user.");
                    response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                Users updatedusers = usersRepository.save(users);
                if (updatedusers != null) {
                    response.setData(updatedusers);
                    response.setDescription("User has been updated succesfully.");
                    response.setStatusCode(HttpServletResponse.SC_OK);
                } else {
                    response.setDescription("User was not updated.");
                    response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        } else {
            response.setDescription("please enter a valid email");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

    public boolean checkEmailAddressOrPhoneNumberExist(String email, String phoneNumber) {
        boolean result = false;
        Optional<Users> user = usersRepository.findUsersByEmailOrPhoneContaining(email, phoneNumber);
        if (user.isPresent()) {
            result = true;
        }
        return result;
    }

    public boolean IsUserActive(long UserID) {
        boolean result = false;
        Optional<Users> user = usersRepository.findById(UserID);
        if (user.isPresent()) {
            String status = user.get().getStatus();
            if (status.equals("Active")) {
                result = true;
            }
        }
        return result;
    }
}
