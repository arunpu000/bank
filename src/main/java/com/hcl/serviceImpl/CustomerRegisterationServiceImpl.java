package com.hcl.serviceImpl;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dto.CustomerDTO;
import com.hcl.entity.Account;
import com.hcl.entity.Customer;
import com.hcl.exception.UserDefinedException;
import com.hcl.respositry.AccountRepository;
import com.hcl.respositry.CustomerRepository;
import com.hcl.service.CustomerRegisterationService;

@Service
public class CustomerRegisterationServiceImpl implements CustomerRegisterationService {

	@Autowired
	public CustomerRepository customerRepository;

	@Autowired
	public AccountRepository accountRepository;

	@Override
	public String addCustomer(CustomerDTO customerRegistrationDTO) throws UserDefinedException{

		Customer customerDetails = new Customer();
		Account accountDetails = new Account();
		
		Random r=new Random();
		if (customerRepository.findByPanNumber(customerRegistrationDTO.getPanNumber())==null) {

		
		BeanUtils.copyProperties(customerRegistrationDTO, customerDetails);
		customerRepository.save(customerDetails);
		
		accountDetails.setAccountNumber(r.nextInt(999999999));
		accountDetails.setAccountType("Saving");
		accountDetails.setIfsccode("AXIS00001276");
		accountDetails.setBranchAddress("Chennai");
		accountDetails.setOpeningBalance(10000.00);
		accountDetails.setCurrentBalance(10000.00);
		accountDetails.setCustomerDetails(customerDetails);
		
		accountRepository.save(accountDetails);

		return "Success and Account Number "+accountDetails.getAccountNumber()+" open Bal is 10000.00";
	}
	
	else {
		throw new UserDefinedException(">>>>>>>>>>>>>>PanCard Already Exists......>>>>>>>>");
	}

}

	@Override
	public Boolean checkphoneNumberExist(String phoneNumber) {
		
		boolean data=false;
		if(customerRepository.findByPhoneNumber(phoneNumber)==null) {
			data=false;
		}
		else {
			data=true;
		}
		return data;
	}
}