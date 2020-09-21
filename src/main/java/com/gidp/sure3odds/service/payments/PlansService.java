package com.gidp.sure3odds.service.payments;

import com.gidp.sure3odds.entity.games.Status;
import com.gidp.sure3odds.entity.payments.Payments;
import com.gidp.sure3odds.entity.payments.PlanTypes;
import com.gidp.sure3odds.entity.payments.Plans;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.helper.AppHelper;
import com.gidp.sure3odds.repository.games.StatusRepository;
import com.gidp.sure3odds.repository.payments.PaymentsRepository;
import com.gidp.sure3odds.repository.payments.PlanTypesRepository;
import com.gidp.sure3odds.repository.payments.PlansRepository;
import com.gidp.sure3odds.repository.users.UsersRepository;
import com.gidp.sure3odds.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PlansService {

	@Autowired
	PlansRepository plansRepository;

	@Autowired
	UsersService usersService;

	@Autowired
	PlansService plansService;

	@Autowired
	PlanTypesRepository planTypesRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	PaymentsRepository paymentsRepository;

	AppHelper appHelper = new AppHelper();


	public BaseResponse UpdatePlan(long UserID, long PlanTypeID, String Platform, String TransactionID) throws IOException {
		BaseResponse response = new BaseResponse();
		String PaymentResult = "failed";
		Optional<Users> users = usersRepository.findById(UserID);
		if(users.isPresent()){
			Plans plans = plansRepository.findPlansByUser(users.get());
			if(plans != null){
				if(Platform.equals("Android")){
					PaymentResult = usersService.AndroidPaymentValidation(TransactionID);
				}else  if (Platform.equals("iOS")){
					PaymentResult = usersService.iOSPaymentValidation(TransactionID);
				} else if (Platform.equals("Manual")) {
					PaymentResult = "success";
				}

				if(PaymentResult.equals("success")){
					//update the plantypeid in the plan table
					Optional<PlanTypes> planTypes = planTypesRepository.findById(PlanTypeID);


					plans.setPlantype(planTypes.get());
					plans.setUser(users.get());
					plans.setId(plans.getId());
					LocalDate CurrentDate = LocalDate.now();

					//update the startdate to currentdate
					plans.setStartDate(CurrentDate);
					//add 31 to the startdate to get the next due date
					LocalDate ExpiryDate = CurrentDate.plusMonths(1);
					plans.setEndDate(ExpiryDate);
					Plans saved_plan = plansRepository.save(plans);

					Payments payments = new Payments();
					payments.setUser(users.get());
					payments.setPlantype(planTypes.get());
					payments.setPaymentdate(CurrentDate);
					payments.setPaymenttype("Renewal");
					payments.setReferenceCode(TransactionID);
					payments.setPlatform(Platform);
					//create the payment
					Payments saved_payment = paymentsRepository.save(payments);
					//update the user table and set status to active
					Status status = statusRepository.findByName("Active").get();
					users.get().setStatus(status);
					usersRepository.save(users.get());
					response.setData(saved_plan);
//					String userName = newUser.getFirstname() + " " + newUser.getLastname();
//					emailService.sendEmail(user.getEmail(), userName, plantype.get().getName());
					response.setDescription("User plan renewal was successful.");
					response.setStatusCode(HttpServletResponse.SC_OK);
				}else{
					response.setDescription("Payment validation was not successful, Please, contact Sure3Odds Support on 08188888320 / send an email to support@sure3odds.com with the proof of payment!");
					response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
				}
			}else{
				response.setDescription("Please, select a plan.");
				response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
			}
		}else{
			response.setDescription("Please, no records found for the selected User");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}

		return response;

	}



}
