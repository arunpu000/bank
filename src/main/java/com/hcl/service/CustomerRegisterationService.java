package com.hcl.service;


import com.hcl.dto.CustomerDTO;
import com.hcl.exception.UserDefinedException;

public interface CustomerRegisterationService {

	String addCustomer( CustomerDTO customerRegistrationDTO) throws UserDefinedException;

	Boolean checkphoneNumberExist(String phoneNumber);

     
}
