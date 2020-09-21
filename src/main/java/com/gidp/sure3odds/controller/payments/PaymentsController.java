package com.gidp.sure3odds.controller.payments;

import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Parameters;
import com.gidp.sure3odds.entity.payments.PlanTypes;
import com.gidp.sure3odds.service.users.ParametersService;
import com.gidp.sure3odds.service.payments.PaymentsService;
import com.gidp.sure3odds.service.payments.PlanTypesService;
import com.gidp.sure3odds.service.payments.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/sure3odds")
public class PaymentsController {

    @Autowired
    PlanTypesService planTypesService;

    @Autowired
    PaymentsService paymentsService;

    @Autowired
    PlansService plansService;

    @Autowired
    ParametersService parametersService;

    @PostMapping(value = "/payments/plantype/create")
    ResponseEntity<?> createPlanTypes(@RequestBody PlanTypes plantype) {
        BaseResponse response = planTypesService.CreatePlanTypes(plantype);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/payments/plantype/delete/{id}")
    ResponseEntity<?> deletePlanTypes(@PathVariable long id) {
        BaseResponse response = planTypesService.DeletePlanTypes(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/payments/plantype/update")
    ResponseEntity<?> updatePlanTypes(@RequestBody PlanTypes plantype) {
        BaseResponse response = planTypesService.UpdatePlantype(plantype);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/payments/plantype/getall")
    ResponseEntity<?> getAllPlanTypes() {
        BaseResponse response = planTypesService.GetAllPlantypes();
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/payments/plantype/get_by_id/{id}")
    ResponseEntity<?> GetPlanTypesByID(@PathVariable Long id) {
        BaseResponse response = planTypesService.GetPlantype(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/payments/payment/get")
    ResponseEntity<?> GetPaymentsByUserID(@RequestAttribute("UserID") Long UserID, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = paymentsService.GetPaymentsByUserID(UserID, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "/payments/payment/delete/{id}")
    ResponseEntity<?> deletePayment(@PathVariable long id) {
        BaseResponse response = paymentsService.DeletePayment(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/payments/payment/search")
    ResponseEntity<?> searchPayments(@RequestParam String searchValue, @RequestParam int pageNo, @RequestParam int pageSize) {
        BaseResponse response = paymentsService.SearchPayments(searchValue, pageNo, pageSize);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/payments/plan/update")
    ResponseEntity<?> UpdatePlan(@RequestParam long userId, @RequestParam long plantypeId, @RequestParam String planform, @RequestParam String transactionObject) throws IOException {
        BaseResponse response = plansService.UpdatePlan(userId, plantypeId, planform, transactionObject);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/payments/parameter/create")
    ResponseEntity<?> createParameter(@RequestBody Parameters parameters) {
        BaseResponse response = parametersService.CreateParameter(parameters);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/payments/parameter/delete/{id}")
    ResponseEntity<?> deleteParameter(@PathVariable long id) {
        BaseResponse response = parametersService.DeleteParameter(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/payments/parameter/update")
    ResponseEntity<?> updateParameter(@RequestBody Parameters parameters) {
        BaseResponse response = parametersService.UpdateParametere(parameters);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/payments/parameter/getall")
    ResponseEntity<?> getAllParameters() {
        BaseResponse response = parametersService.GetAllParameters();
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/payments/parameter/get_by_id/{id}")
    ResponseEntity<?> getParameterByID(@PathVariable Long id) {
        BaseResponse response = parametersService.GetParameterByID(id);
        if (response.getStatusCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }




}
