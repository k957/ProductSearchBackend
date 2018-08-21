package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.PaymentMethod;
import com.example.service.IPaymentMethodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/PaymentMethod")
@Api(value="Payment method controller REST Endpoint",description="Payment Method API")
public class PaymentMethodControllerV2 {
	
	@Autowired
	private IPaymentMethodService paymentMethodservice;
	
	@CrossOrigin
	@GetMapping
	@ApiOperation(value="returns the list of all Payment Methods available",response=PaymentMethod.class)
	public ResponseEntity<List<PaymentMethod>> viewAll() {
			List<PaymentMethod> paymentMethod = paymentMethodservice.viewAll();
			HttpHeaders responseHeader = new HttpHeaders();
			return new ResponseEntity<>(paymentMethod, responseHeader, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/{id}")
	@ApiOperation(value="returns one Payment Method whose ID provided in the URL",response=PaymentMethod.class)
	public ResponseEntity<?> viewOne(@PathVariable("id") Long id) {
			PaymentMethod paymentMethod = paymentMethodservice.viewOne(id);
			HttpHeaders responseHeader = new HttpHeaders();
			return new ResponseEntity<>(paymentMethod, responseHeader, HttpStatus.OK);
	}
}
