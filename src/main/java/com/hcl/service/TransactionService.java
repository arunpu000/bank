package com.hcl.service;

import java.util.List;

import javax.validation.Valid;

import com.hcl.dto.FundDTO;
import com.hcl.dto.PhoneDTO;
import com.hcl.dto.StatementDTO;
import com.hcl.exception.UserDefinedException;

public interface TransactionService {
	
	String fundTransfer( FundDTO fundTransferRequestDTO) throws UserDefinedException;
	
	List<StatementDTO> getMonthlyStatement(long accountNo, int month, int year) throws UserDefinedException;

	Boolean transferByPhoneNumber(@Valid PhoneDTO phoneDTO)throws UserDefinedException;

}
