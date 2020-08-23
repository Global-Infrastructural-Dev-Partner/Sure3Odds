package com.gidp.sure3odds.service.payments;

import com.gidp.sure3odds.entity.payments.Payments;
import com.gidp.sure3odds.entity.payments.PlanTypes;
import com.gidp.sure3odds.entity.payments.Plans;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.helper.AppHelper;
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

	AppHelper appHelper = new AppHelper();


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
				Date ExpiryDate = appHelper.convertToDateViaInstant(DueDate);
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
				users.get().setStatus("Active");
				usersRepository.save(users.get());
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



}
