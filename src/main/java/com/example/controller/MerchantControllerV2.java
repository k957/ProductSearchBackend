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

import com.example.exception.ResourceNotFoundException;
import com.example.model.Merchant;
import com.example.repository.IMerchantRepository;
import com.example.service.IMerchantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/merchant")
@Api(value="Merchant Controller REST Endpoint unsecured version 2",description="Merchant Info API")
public class MerchantControllerV2 {
	
	@Autowired
	private IMerchantService merchantService;

	@Autowired
	private IMerchantRepository merchantRepository;
	
	@ApiOperation(value="Returns the list of all merchants registered",response=Merchant.class)
	@GetMapping
	public ResponseEntity<?> viewAll() {
			List<Merchant> merchant = merchantService.viewAll();
			HttpHeaders responseHeader = new HttpHeaders();
		//	responseHeader.add("Access-Control-Allow-Origin", "*");
			responseHeader.add("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
			responseHeader.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
			return new ResponseEntity<>(merchant, responseHeader, HttpStatus.OK);
	}

	@ApiOperation(value="Returns one Merchant details whose ID is provided in the URL",response=Merchant.class)
	@GetMapping("/merchantId/{id}")
	public ResponseEntity<?> viewOne(@PathVariable(value = "id") Long id) {
			Merchant merchant = merchantRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Merchant", "id", id));
			HttpHeaders responseHeaders = new HttpHeaders();
		//	responseHeaders.add("Access-Control-Allow-Origin", "*");
			responseHeaders.add("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
			responseHeaders.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
			return new ResponseEntity<>(merchant, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("displayName/{displayName}")
	@ApiOperation(value="Returns one merchant details whose display name provided in url",response=Merchant.class)
	public ResponseEntity<?> viewByDisplayName(@PathVariable("displayName") String displayName){
		Merchant merchant = merchantService.findByDisplayName(displayName);
		HttpHeaders responseHeaders = new HttpHeaders();
		//responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
		responseHeaders.add("Access-Control-Allow-Headers", "Content-Type");
		if(merchant!=null) {
		return new ResponseEntity<>(merchant, responseHeaders, HttpStatus.OK);
	}else {
		throw new ResourceNotFoundException("Merchant", "display name", displayName);
	}
}
}
