package com.gidp.sure3odds.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gidp.sure3odds.entity.BaseResponse;
import com.gidp.sure3odds.entity.Payments;

@RestController
@RequestMapping("/sure3odds")
public class PaymentsController {

	@PutMapping(value = "/update_payment") // activate user
	ResponseEntity<?> updatePayment(@RequestParam Payments payments) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get_payments")
	ResponseEntity<?> getPayments() {
		BaseResponse response = null;
		if (response.getStatusCode() == 200) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/add_payment")
	ResponseEntity<?> addPayment(@RequestParam Payments payments) {
		BaseResponse response = null;
		if (response.getStatusCode() == 201) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
