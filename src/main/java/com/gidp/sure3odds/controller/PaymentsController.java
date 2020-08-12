package com.gidp.sure3odds.controller;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.PlanTypes;
import com.gidp.sure3odds.service.PaymentsService;
import com.gidp.sure3odds.service.PlanTypesService;
import com.gidp.sure3odds.service.PlansService;
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
	ResponseEntity<?> deletePlantype(@PathVariable long id) {
		BaseResponse response = planTypesService.DeletePlanTypes(id);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/payments/plantype/update")
	ResponseEntity<?> updatePlantype(@RequestBody PlanTypes plantype) {
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
	ResponseEntity<?> getPermission(@PathVariable Long id) {
		BaseResponse response = planTypesService.GetPlantype(id);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping(value = "/payments/get_by_userid/{id}")
	ResponseEntity<?> GetPaymentsByUserID(@RequestParam Long id) {
		BaseResponse response = paymentsService.GetPaymentsByUserID(id);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/payments/plan/update")
	ResponseEntity<?> UpdatePlan(@RequestParam Long UserID, @RequestParam Long PlanTypeID, @RequestParam String Platform, @RequestParam String TransactionObject) throws IOException {
		BaseResponse response = plansService.UpdatePlan(UserID, PlanTypeID, Platform, TransactionObject);
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}


}
