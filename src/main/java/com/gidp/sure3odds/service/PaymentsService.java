package com.gidp.sure3odds.service;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.Payments;
import com.gidp.sure3odds.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class PaymentsService {

	@Autowired
	PaymentsRepository paymentsRepository;



	public BaseResponse GetPaymentsByUserID(long userID) {
		BaseResponse response = new BaseResponse();
		List<Payments> payments = null;
		if(userID == 1){
			payments = paymentsRepository.findAll();
		}else {
			payments = paymentsRepository.findPaymentsByUserID(userID);
		}
		if (!payments.isEmpty()) {
			response.setData(payments);
			response.setDescription("Payments found successfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}



}
