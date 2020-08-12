package com.gidp.sure3odds.service;

import com.gidp.sure3odds.entity.*;
import com.gidp.sure3odds.repository.PaymentsRepository;
import com.gidp.sure3odds.repository.PlanTypesRepository;
import com.gidp.sure3odds.repository.PlansRepository;
import com.gidp.sure3odds.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
	PaymentsRepository paymentsRepository;


	public BaseResponse UpdatePlan(long UserID, long PlanTypeID, String Platform, String TransactionID) throws IOException {
		BaseResponse response = new BaseResponse();
		String PaymentResult = "failed";

		Plans plans = plansRepository.findPlanByUserID(UserID);
		if(plans != null){
			if(Platform.equals("Android")){
				PaymentResult = usersService.AndroidPaymentValidation(TransactionID);
			}else  if (Platform.equals("iOS")){
				PaymentResult = usersService.iOSPaymentValidation(TransactionID);
			}

			if(PaymentResult.equals("success")){
				//update the plantypeid in the plan table
				Optional<PlanTypes> planTypes = planTypesRepository.findById(PlanTypeID);
				Optional<Users> users = usersRepository.findById(UserID);
				Plans updated_plans = new Plans();
				updated_plans.setPlanTypeID(planTypes.get());
				updated_plans.setUserID(users.get());
				updated_plans.setId(plans.getId());
				LocalDate CurrentDate = LocalDate.now();

				//update the startdate to currentdate
				updated_plans.setStartDate(new Date());
				//add 31 to the startdate to get the next due date
				LocalDate DueDate = CurrentDate.plusMonths(1);
				Date ExpiryDate = convertToDateViaInstant(DueDate);
				updated_plans.setEndDate(ExpiryDate);
				Plans saved_plan = plansRepository.save(updated_plans);

				Payments payments = new Payments();
				payments.setUserID(users.get());
				payments.setPlanTypeID(planTypes.get());
				payments.setPaymentdate(new Date());
				payments.setPaymenttype("Renewal");
				payments.setReferenceCode(TransactionID);
				payments.setPlatform(Platform);
				//create the payment
				Payments saved_payment = paymentsRepository.save(payments);

				//update the user table and set status to active
				Users updated_user = new Users();
				updated_user.setId(users.get().getId());
				updated_user.setStatus("Active");
				Users saved_user = usersRepository.save(updated_user);
				response.setData(saved_plan);
				response.setDescription("User plan renewal was successful.");
				response.setStatusCode(HttpServletResponse.SC_OK);
			}else{
				response.setDescription("Your payment validation was not successful, Please contact the admin if your account was debited and send proof of payment!");
				response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
			}
		}else{
			response.setDescription("Please, No record found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public Date convertToDateViaInstant(LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}

}
