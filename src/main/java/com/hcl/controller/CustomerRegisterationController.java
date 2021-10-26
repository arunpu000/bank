package com.hcl.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.CustomerDTO;
import com.hcl.exception.UserDefinedException;
import com.hcl.service.CustomerRegisterationService;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("/customersBanking")
public class CustomerRegisterationController {

	@Autowired
	private CustomerRegisterationService customerService;
	
	@Autowired
	Environment environment;

	@GetMapping("/port")
	public String getPortNo() {
		String port = environment.getProperty("local.server.port");
		return "From Bank app : " + port;
	}


	@PostMapping
	public ResponseEntity<String> customerRegistration(@Valid @RequestBody CustomerDTO customerDTO) throws UserDefinedException {
		String response = customerService.addCustomer(customerDTO);
		 return new ResponseEntity<String>(response,HttpStatus.OK);

	}

@GetMapping("/phoneExist/{phoneNumber}")
public ResponseEntity<Boolean> PhoneNumberExist(@PathVariable  String phoneNumber){
	Boolean data=customerService.checkphoneNumberExist(phoneNumber);
	return new ResponseEntity<Boolean>(data,HttpStatus.OK);
}
	
}
