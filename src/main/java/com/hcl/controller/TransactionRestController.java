package com.hcl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.FundDTO;
import com.hcl.dto.PhoneDTO;
import com.hcl.dto.StatementDTO;
import com.hcl.exception.UserDefinedException;
import com.hcl.service.TransactionService;

@RestController
@RequestMapping("/transactionsBanking")
public class TransactionRestController {

	@Autowired
	private TransactionService transactionService;  
	
	@PostMapping
	public ResponseEntity<String> fundTransferAccount(@Valid @RequestBody FundDTO fundTransferRequestDTO)
			throws UserDefinedException {
		String response = transactionService.fundTransfer(fundTransferRequestDTO);

		 return new ResponseEntity<String>(response,HttpStatus.OK);

	}
	
	@GetMapping("/{accountNo}/{month}/{year}")
	public List<StatementDTO> getStatement(@Param("accountNo") long accountNo,@Param("month") int month,@Param("year") int year ) throws UserDefinedException
	{
		List<StatementDTO> statement = transactionService.getMonthlyStatement(accountNo,month,year);

		return statement;
		
	}


	@PostMapping("/transferPhone")
	public  ResponseEntity<Boolean> transferByPhone(@Valid @RequestBody PhoneDTO phoneDTO) throws UserDefinedException{
		System.out.println("transfer method>>>>>>>>>>>");
		Boolean data=transactionService.transferByPhoneNumber(phoneDTO);
		System.out.println("Data>>>-------------->>>>>>>"+ data);
		return new ResponseEntity<Boolean>(data,HttpStatus.OK);
		
	}
	

}
