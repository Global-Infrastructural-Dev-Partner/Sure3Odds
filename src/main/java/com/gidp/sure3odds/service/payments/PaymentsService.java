package com.gidp.sure3odds.service.payments;

import com.gidp.sure3odds.entity.payments.Payments;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.repository.payments.PaymentsRepository;
import com.gidp.sure3odds.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class PaymentsService {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Autowired
    UsersRepository usersRepository;


    public BaseResponse GetPaymentsByUserID(long userId, int pageNo, int pageSize) {
        BaseResponse response = new BaseResponse();
        Page<Payments> payments = null;
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Optional<Users> users = usersRepository.findById(userId);
        if (users.isPresent()) {
            if (userId == 1l) {
                payments = paymentsRepository.findAll(paging);
            } else {
                payments = paymentsRepository.findByUser(users.get(), paging);
            }
            if (!payments.isEmpty()) {
                response.setData(payments);
                response.setDescription("Payments found successfully.");
                response.setStatusCode(HttpServletResponse.SC_OK);
            } else {
                response.setDescription("No results found.");
                response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }

        return response;

    }


    public BaseResponse DeletePayment(long payemntId) {
        BaseResponse response = new BaseResponse();
        Optional<Payments> payments = paymentsRepository.findById(payemntId);
        if (payments.isPresent()) {
            paymentsRepository.deleteById(payemntId);
            response.setDescription("Payment deleted successfully");
            response.setStatusCode(HttpServletResponse.SC_CREATED);
        } else {
            response.setDescription("No records found");
            response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
        }
        return response;

    }

    public BaseResponse SearchPayments(String name, int pageNo, int pageSize) {
        BaseResponse response = new BaseResponse();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("paymentdate").descending());
        Page<Payments> payments = paymentsRepository.findByPaymenttypeContainingOrPlatformContainingOrderByPaymentdate(name, name, paging);
        if (!payments.isEmpty()) {
            response.setData(payments);
            response.setDescription("Payments found successfully.");
            response.setStatusCode(HttpServletResponse.SC_OK);
        } else {
            response.setDescription("No results found.");
            response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        }
        return response;

    }

}
