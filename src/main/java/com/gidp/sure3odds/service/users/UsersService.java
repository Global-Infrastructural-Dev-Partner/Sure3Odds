package com.gidp.sure3odds.service.users;

import com.gidp.sure3odds.entity.games.Comments;
import com.gidp.sure3odds.entity.games.Predictions;
import com.gidp.sure3odds.entity.games.Status;
import com.gidp.sure3odds.entity.games.Votes;
import com.gidp.sure3odds.entity.payments.Payments;
import com.gidp.sure3odds.entity.payments.PlanTypes;
import com.gidp.sure3odds.entity.payments.Plans;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.NewUser;
import com.gidp.sure3odds.entity.users.Parameters;
import com.gidp.sure3odds.entity.users.UserTypes;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.helper.AppHelper;
import com.gidp.sure3odds.repository.games.CommentsRepository;
import com.gidp.sure3odds.repository.games.PredictionsRepository;
import com.gidp.sure3odds.repository.games.StatusRepository;
import com.gidp.sure3odds.repository.games.VotesRepository;
import com.gidp.sure3odds.repository.payments.PaymentsRepository;
import com.gidp.sure3odds.repository.payments.PlanTypesRepository;
import com.gidp.sure3odds.repository.payments.PlansRepository;
import com.gidp.sure3odds.repository.users.ParametersRepository;
import com.gidp.sure3odds.repository.users.UserTypesRepository;
import com.gidp.sure3odds.repository.users.UsersRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    HttpClient httpClient = HttpClients.createDefault();
    HttpClient client = HttpClients.createDefault();
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

    @Autowired
    ParametersRepository parametersRepository;

    AppHelper appHelper = new AppHelper();

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    VotesRepository votesRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    PredictionsRepository predictionsRepository;

    @Autowired
    EmailService emailService;

    public BaseResponse CreateNewUser(NewUser newUser) throws IOException {
        BaseResponse response = new BaseResponse();
        if (appHelper.isValidEmail(newUser.getEmail())) {
            if (!checkEmailAddressOrPhoneNumberExist(newUser.getEmail(), newUser.getPhone())) {
                response = RegisterUser(newUser);
            } else {
                response.setDescription("User is already registered");
                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setDescription("Please enter a valid email");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

    public BaseResponse RegisterUser(NewUser newUser) throws IOException {
        BaseResponse response = new BaseResponse();
        String validationResult = "failed";
        if (newUser.getPlatform().equals("Android")) {
            validationResult = AndroidPaymentValidation(newUser.getReferencecode());
        } else if (newUser.getPlatform().equals("iOS")) {
            validationResult = iOSPaymentValidation(newUser.getReferencecode());
        } else if (newUser.getPlatform().equals("Manual")) {
            validationResult = "success";
        }

        if (validationResult.equals("success")) {
            Long usertypeid = newUser.getUsertypes().getId();
            Optional<UserTypes> usertype = userTypesRepository.findById(usertypeid);
            if (usertype.isPresent()) {
                Long planttypeid = newUser.getPlantype().getId();
                Optional<PlanTypes> plantype = planTypesRepository.findById(planttypeid);
                if (plantype.isPresent()) {
                    Optional<Status> status = statusRepository.findByName("Active");
                    String password = passwordEncoder.encode(newUser.getPassword());
                    LocalDate CurrentDate = LocalDate.now();
                    String unique_id = newUser.getEmail().split("@")[0];
                    Users user = new Users(newUser.getEmail(), newUser.getPhone(), password,
                            newUser.getFirstname(), newUser.getLastname(), unique_id, CurrentDate,
                            "Pending");
                    user.setUsertypes(usertype.get());
                    user.setStatus(status.get());
                    user.setDatejoined(CurrentDate);
                    Users saved_user = usersRepository.save(user);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String CurrentDateString = CurrentDate.format(formatter);
                    LocalDate eDate = LocalDate.parse(CurrentDateString);
                    LocalDate endDate = eDate.plusMonths(1);
                    Plans plan = new Plans(CurrentDate, endDate);
                    plan.setUser(saved_user);
                    plan.setPlantype(plantype.get());
                    Plans saved_plan = plansRepository.save(plan);
                    Payments payment = new Payments(CurrentDate, "Registration",
                            newUser.getPlatform(), newUser.getReferencecode());
                    payment.setUser(saved_user);
                    payment.setPlantype(plantype.get());
                    Payments saved_payment = paymentsRepository.save(payment);
                    response.setData(saved_user);
                    String userName = newUser.getFirstname() + " " + newUser.getLastname();
                    emailService.sendEmail(user.getEmail(), userName, plantype.get().getName());
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
            response.setDescription("Payment validation was not successful.Please, send proof of payment to Sure3Odds Support on 08188888320 / send an email to support@sure3odds.com");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

    public BaseResponse GetUsersByUserTypID(long userTypeId, int pageNo, int pageSize) {
        BaseResponse response = new BaseResponse();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").descending().and(Sort.by("lastname").ascending()));
        UserTypes userTypes = userTypesRepository.findById(userTypeId).get();

        Page<Users> users = usersRepository.findByUsertypes(userTypes, paging);
        if (!users.isEmpty()) {
            response.setData(users);
            response.setDescription("Users by user type found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No records found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public BaseResponse SearchUsersByUserTypID(Long userTypeId, String userName, int pageNo, int pageSize) {
        BaseResponse response = new BaseResponse();
        UserTypes userTypes = userTypesRepository.findById(userTypeId).get();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("lastname").ascending().and(Sort.by("firstname").ascending()));
        Page<Users> users = usersRepository.findByLastnameContainingOrFirstnameContainingAndUsertypesEquals(userName, userName, userTypes, paging);
        if (!users.isEmpty()) {
            response.setData(users);
            response.setDescription("Users found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
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
        Parameters parameters = parametersRepository.findById(2l).get();
        String SecretKey = parameters.getValue();
        try {
            HttpGet newRequest = new HttpGet("https://api.paystack.co/transaction/verify/" + ReferenceCode);
            newRequest.addHeader("Content-type", "application/json");
            newRequest.addHeader("Authorization", "Bearer " + SecretKey);
            newRequest.addHeader("Cache-Control", "no-cache");
            HttpResponse response = this.client.execute((HttpUriRequest) newRequest);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String responseBody = EntityUtils.toString(entity);
                    response.getStatusLine().getStatusCode();
//                    if (response.getStatusLine().getStatusCode() == 200) {
//                        return result = "success";
//                    } else {
//                        return result = "failed";
//                    }
                    return result = "success";
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return result = "failed";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());//RuntimeException(e);
            return result = "failed";
        }
        return result;
    }

    public BaseResponse CreateSubAdmin(Users newUser) {
        BaseResponse response = new BaseResponse();
        Long usertypeid = newUser.getUsertypes().getId();
        Optional<UserTypes> usertype = userTypesRepository.findById(usertypeid);
        if (usertype.isPresent()) {
            Optional<Status> status = statusRepository.findByName("Active");
            String password = passwordEncoder.encode(newUser.getPassword());
            newUser.setStatus(status.get());
            LocalDate CurrentDate = LocalDate.now();
            String unique_id = newUser.getEmail().split("@")[0];
            newUser.setDevice_token("Pending");
            Users user = new Users(newUser.getEmail(), newUser.getPhone(), password,
                    newUser.getFirstname(), newUser.getLastname(), unique_id, CurrentDate,
                    newUser.getDevice_token());
            user.setUsertypes(usertype.get());
            user.setStatus(status.get());
            Users saved_user = usersRepository.save(user);
            response.setData(saved_user);

//            String userName = newUser.getFirstname() + " " + newUser.getLastname();
//            emailService.sendEmail(user.getEmail(), userName, plantype.get().getName());
            response.setDescription("SubAdmin created successfully");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }

        return response;

    }

    public BaseResponse GetUserDetailsByID(Long userid) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> userData = new HashMap<>();
        Optional<Users> users = usersRepository.findById(userid);
        if (users != null) {

            if (users.get().getUsertypes().getName().equals("Member")) {
                Plans plans = plansRepository.findPlansByUser(users.get());
                userData.put("planData", plans);
            }
            userData.put("userData", users);
            response.setData(userData);
            response.setDescription("User found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public String GetUserTypeNameByUserID(Long userid) {
        Users users = usersRepository.findById(userid).get();
        String usertypeName = users.getUsertypes().getName();
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

    public String GetUserPassWord(Long userid) {
        Users users = usersRepository.findById(userid).get();
        String userPassword = users.getPassword();
        return userPassword;
    }

    public BaseResponse UpdateUser(Users newUsers) {
        BaseResponse response = new BaseResponse();
        if (appHelper.isValidEmail(newUsers.getEmail())) {
            Long userid = newUsers.getId();
            String userEmail = GetUserEmail(userid);
            String userPhone = GetUserPhone(userid);
            if (checkEmailAddressOrPhoneNumberExist(newUsers.getEmail(), newUsers.getPhone())) {
                if (userEmail.equals(newUsers.getEmail()) || userPhone.equals(newUsers.getPhone())) {
                    newUsers.setPassword(GetUserPassWord(userid));
                    Users updatedusers = usersRepository.save(newUsers);
                    if (updatedusers != null) {
                        response.setData(updatedusers);
                        response.setDescription("User has been updated successfully.");
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
                newUsers.setPassword(GetUserPassWord(userid));
                Users updatedusers = usersRepository.save(newUsers);
                if (updatedusers != null) {
                    response.setData(updatedusers);
                    response.setDescription("User has been updated successfully.");
                    response.setStatusCode(HttpServletResponse.SC_OK);
                } else {
                    response.setDescription("User was not updated.");
                    response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        } else {
            response.setDescription("Please, enter a valid email");
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

    public boolean IsUserActive(long userId) {
        Optional<Users> user = usersRepository.findById(userId);
        if (user.isPresent()) {
            if (user.get().getUsertypes().getName().equals("Member")) {//members
                Plans plans = plansRepository.findPlansByUser(user.get());
                if (plans.getUser().getId() == user.get().getId()) {
                    LocalDate dueDate = plans.getEndDate();
                    LocalDate currentDate = LocalDate.now();
                    Status status = statusRepository.findByName("Inactive").get();
                    if (currentDate.isAfter(dueDate)) {
                        user.get().setStatus(status);
                        usersRepository.save(user.get());
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
        return false;
    }


    public BaseResponse GetAppReports() {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> result = new HashMap<String, Object>();

        Optional<UserTypes> userTypes = userTypesRepository.findById(2l);
        List<Users> allUser = usersRepository.findUsersByUsertypesEquals(userTypes.get());
        result.put("totalusers", allUser.size());

        Status activeStatus = statusRepository.findByName("Active").get();
        Status inactiveStatus = statusRepository.findByName("Inactive").get();

        List<Users> allActiveUsers = usersRepository.findByStatusEqualsAndUsertypesEquals(activeStatus, userTypes.get());
        result.put("totalactiveusers", allActiveUsers.size());

        List<Users> allInActiveUsers = usersRepository.findByStatusEqualsAndUsertypesEquals(inactiveStatus, userTypes.get());
        result.put("totalinactiveusers", allInActiveUsers.size());

        Optional<PlanTypes> planTypes = planTypesRepository.findById(1l);
        List<Plans> plansUser1 = plansRepository.findByPlantype(planTypes.get());
        result.put("totalvvipusers", plansUser1.size());

        Optional<PlanTypes> planTypes2 = planTypesRepository.findById(2l);
        List<Plans> plansUser2 = plansRepository.findByPlantype(planTypes2.get());
        result.put("totalvipusers", plansUser2.size());


        Optional<PlanTypes> plantypes1 = planTypesRepository.findById(1l);
        List<Payments> planTypes1Users = paymentsRepository.findPaymentsByPlantypeEquals(plantypes1.get());
        result.put("totalvvippay", planTypes1Users.size());

        Optional<PlanTypes> plantypes2 = planTypesRepository.findById(2l);
        List<Payments> planTypes2Users = paymentsRepository.findPaymentsByPlantypeEquals(plantypes2.get());
        result.put("totalvippay", planTypes2Users.size());


        BigDecimal planType1Income = BigDecimal.ZERO;
        planType1Income = planTypes.get().getAmount().multiply(new BigDecimal(planTypes1Users.size()));
        result.put("totalvvipincome", planType1Income);


        BigDecimal planType2Income = BigDecimal.ZERO;
        planType2Income = planTypes2.get().getAmount().multiply(new BigDecimal(planTypes2Users.size()));
        result.put("totalvipincome", planType2Income);

        BigDecimal totalincome = planType1Income.add(planType2Income);
        result.put("totalincome", totalincome);

        if (result != null) {
            response.setData(result);
            response.setDescription("Report found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

    public String ValidateAllUsersPaymentDueDate() {
        String result = "failed";
        List<Users> users = usersRepository.findAll();
        for (Users user : users) {
            IsUserActive(user.getId());
        }
        return result;
    }


    public BaseResponse GetMonthlyReports(LocalDate startDate) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> result = new HashMap<String, Object>();

        LocalDate endDate = startDate.withDayOfMonth(startDate.getMonth().length(startDate.isLeapYear()));


        Optional<UserTypes> userTypes = userTypesRepository.findById(2l);
        List<Users> allUser = usersRepository.findUsersByDatejoinedBetweenAndUsertypesEquals(startDate, endDate, userTypes.get());
        result.put("totalusers", allUser.size());

        Status activeStatus = statusRepository.findByName("Active").get();
        List<Users> allActiveUsers = usersRepository.findUsersByDatejoinedBetweenAndStatusEqualsAndUsertypesEquals(startDate, endDate, activeStatus, userTypes.get());
        result.put("totalactiveusers", allActiveUsers.size());

        Status inactiveStatus = statusRepository.findByName("Inactive").get();
        List<Users> allInActiveUsers = usersRepository.findUsersByDatejoinedBetweenAndStatusEqualsAndUsertypesEquals(startDate, endDate, inactiveStatus, userTypes.get());
        result.put("totalinactiveusers", allInActiveUsers.size());

        Optional<PlanTypes> planTypes = planTypesRepository.findById(1l);
        List<Plans> plansUser1 = plansRepository.findByStartDateBetweenAndPlantypeEquals(startDate, endDate, planTypes.get());
        result.put("totalvvipusers", plansUser1.size());

        Optional<PlanTypes> planTypes2 = planTypesRepository.findById(2l);
        List<Plans> plansUser2 = plansRepository.findByStartDateBetweenAndPlantypeEquals(startDate, endDate, planTypes2.get());
        result.put("totalvipusers", plansUser2.size());


        Optional<PlanTypes> plantypes1 = planTypesRepository.findById(1l);
        List<Payments> planTypes1Users = paymentsRepository.findPaymentsByPaymentdateBetweenAndPlantypeEquals(startDate, endDate, planTypes.get());
        result.put("totalvvippay", planTypes1Users.size());

        Optional<PlanTypes> plantypes2 = planTypesRepository.findById(2l);
        List<Payments> PlanTypes2Users = paymentsRepository.findPaymentsByPaymentdateBetweenAndPlantypeEquals(startDate, endDate, planTypes2.get());
        result.put("totalvippay", PlanTypes2Users.size());

        BigDecimal planType1Income = BigDecimal.ZERO;
        planType1Income = plantypes1.get().getAmount().multiply(new BigDecimal(planTypes1Users.size()));
        result.put("totalvvipincome", planType1Income);


        BigDecimal planType2Income = BigDecimal.ZERO;
        planType2Income = plantypes2.get().getAmount().multiply(new BigDecimal(PlanTypes2Users.size()));
        result.put("totalvipincome", planType2Income);

        BigDecimal totalincome = planType1Income.add(planType2Income);
        result.put("totalincome", totalincome);

        if (result != null) {
            response.setData(result);
            response.setDescription("report found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }


    public BaseResponse DeleteSubAdmin(long userId) {
        BaseResponse response = new BaseResponse();
        Optional<Users> users = usersRepository.findById(userId);
        if (users.isPresent()) {

            List<Votes> votes = votesRepository.findByUser(users.get());
            if (!votes.isEmpty()) {
                votesRepository.deleteAll(votes);
            }


            List<Comments> comments = commentsRepository.findByUser(users.get());
            if (!comments.isEmpty()) {
                commentsRepository.deleteAll(comments);
            }

            List<Predictions> predictions = predictionsRepository.findByUser(users.get());
            if (!predictions.isEmpty()) {
                predictionsRepository.deleteAll(predictions);
            }

            usersRepository.deleteById(userId);
            response.setDescription("SubAdmin has been deleted successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No records found");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }


    public BaseResponse DeleteMember(long userId) {
        BaseResponse response = new BaseResponse();
        Optional<Users> users = usersRepository.findById(userId);
        if (users.isPresent()) {

            List<Votes> votes = votesRepository.findByUser(users.get());
            if (!votes.isEmpty()) {
                votesRepository.deleteAll(votes);
            }
            List<Comments> comments = commentsRepository.findByUser(users.get());
            if (!comments.isEmpty()) {
                commentsRepository.deleteAll(comments);
            }

            Plans plans = plansRepository.findPlansByUser(users.get());
            if (plans.getId() != -1) {
                plansRepository.deleteById(plans.getId());
            }

            List<Payments> payments = paymentsRepository.findByUser(users.get());
            if (!payments.isEmpty()) {
                paymentsRepository.deleteAll(payments);
            }


            usersRepository.deleteById(userId);
            response.setDescription("Member has been deleted successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No records found");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;
    }

    public BaseResponse GetAppVersion() {
        BaseResponse response = new BaseResponse();
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("android", "1.0.1");
        result.put("ios", "1.0.0");
        if (result != null) {
            response.setData(result);
            response.setDescription("server version found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

}
