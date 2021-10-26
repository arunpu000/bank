package com.hcl.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.dto.FundDTO;
import com.hcl.dto.PhoneDTO;
import com.hcl.dto.StatementDTO;
import com.hcl.entity.Account;
import com.hcl.entity.Customer;
import com.hcl.entity.Transaction;
import com.hcl.exception.UserDefinedException;
import com.hcl.respositry.AccountRepository;
import com.hcl.respositry.CustomerRepository;
import com.hcl.respositry.TransactionRepository;
import com.hcl.service.TransactionService;

@Service
public class TransactionServiceImplmention implements TransactionService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	   private CustomerRepository customerRepository;
	
	
	@Override
	public String fundTransfer(FundDTO fundTransferRequestDTO) throws UserDefinedException {
		Transaction sourceAccount = new Transaction();
		Transaction destinationAccount = new Transaction();
	
		Optional<Account> fromAccount = accountRepository.findByAccountNumber(fundTransferRequestDTO.getFromAccount());
		Optional<Account> toAccount = accountRepository.findByAccountNumber(fundTransferRequestDTO.getToAccount());
		Account toAccountDetails = toAccount.get();
		Account fromAccountDetails = fromAccount.get();
		
		//  modify the code 
		if (accountRepository.findByAccountNumber(fromAccountDetails.getAccountNumber()).isPresent() & accountRepository.findByAccountNumber(toAccountDetails.getAccountNumber()).isPresent())
		{
       
	
		//if (accountRepository.findByAccountNumber(fromAccountDetails.getAccountNumber()).isPresent()) {
		//}

			//if (accountRepository.findByAccountNumber(toAccountDetails.getAccountNumber()).isPresent()) {}

				//if (fromAccountDetails.getAccountNumber() != toAccountDetails.getAccountNumber()) {}

					if ((fromAccountDetails.getCurrentBalance() > fundTransferRequestDTO.getAmount())
							&& fundTransferRequestDTO.getAmount() > 0) {

						double fromAccountBalance = fromAccountDetails.getCurrentBalance()
								- fundTransferRequestDTO.getAmount();
						fromAccountDetails.setCurrentBalance(fromAccountBalance);

						accountRepository.save(fromAccountDetails);
						double toAccountBalance = toAccountDetails.getCurrentBalance()
								+ fundTransferRequestDTO.getAmount();
						toAccountDetails.setCurrentBalance(toAccountBalance);
						accountRepository.save(toAccountDetails);

						sourceAccount.setAccountNo(fromAccountDetails.getAccountNumber());
						sourceAccount.setAmount(fundTransferRequestDTO.getAmount());
						sourceAccount.setCurrentBal(fromAccountBalance);
						sourceAccount.setCredit_debit("debit");
						sourceAccount.setMessage(fundTransferRequestDTO.getRemarks());
						//Added code
						sourceAccount.setTransferMoney("credit Amount");
						System.out.println(fromAccountDetails);
						sourceAccount.setAccount(fromAccountDetails);
						// to set local time 04/10/2021 Arun 
						java.util.Date date = new java.util.Date();
						long time = date.getTime();
						Timestamp transactiondate = new Timestamp(time);
						sourceAccount.setTransactionDate(transactiondate);
						transactionRepository.save(sourceAccount);

						destinationAccount.setAccountNo(toAccountDetails.getAccountNumber());
						destinationAccount.setAmount(fundTransferRequestDTO.getAmount());
						destinationAccount.setCurrentBal(toAccountBalance);
						destinationAccount.setCredit_debit("credit");
						destinationAccount.setMessage(fundTransferRequestDTO.getRemarks());
						//Added 30-092021
						destinationAccount.setTransferMoney(fundTransferRequestDTO.getTransferMoney());

						destinationAccount.setTransactionDate(transactiondate);
						destinationAccount.setAccount(toAccountDetails);
						transactionRepository.save(destinationAccount);

					} else {
						throw new UserDefinedException(" No Balance");
					}
				} else {
					throw new UserDefinedException(
							"Please enter the valid Account Number");

				}
			 /*else {
				throw new UserDefinedException(" ToAccount not found.Please check the account number");
			}
		} else {
			throw new UserDefinedException("FromAccount not found.Please the account number");
		}*/
		return "Succesfull done the transaciton.";
	}


	@SuppressWarnings("deprecation")
	@Override
	public List<StatementDTO> getMonthlyStatement(long accountNo, int month, int year)
			throws UserDefinedException {
		List<Transaction> response = new ArrayList<Transaction>();
		List<StatementDTO> transactionHistory = new ArrayList<>();
		//changes 10/04/2021
		java.util.Date date = new java.util.Date();
		long checkYear = date.getYear() + 1900;
		if (accountRepository.findByAccountNumber(accountNo).isPresent() && (month > 0 && month <= 12)
				&& (year <= checkYear)) {
			if (!transactionRepository.getTransactionHistory(accountNo, month, year).isEmpty()) {

				System.out.println("Inside transaction history");
				response = transactionRepository.getTransactionHistory(accountNo, month, year);

				System.out.println("Inside response block history"+response);
			} else {

				throw new UserDefinedException("There is no tranasaction history");

			}
		} else {
			System.out.println("else  block2");
			throw new UserDefinedException("Please enter valid details");
		
		}
		response.stream().forEach(history -> {
			StatementDTO monthlyHistory = new StatementDTO();
			BeanUtils.copyProperties(history, monthlyHistory);
			transactionHistory.add(monthlyHistory);

		});
		return transactionHistory;

	}


	@Override
	public Boolean transferByPhoneNumber(@Valid PhoneDTO phoneDTO) throws UserDefinedException {
		
		Boolean result = false;
	      Customer fromPhoneNumberSend = new Customer();
	      Customer toPhoneNumberSend = new Customer();
	      fromPhoneNumberSend = customerRepository.findByPhoneNumber(phoneDTO.getFromPhoneNumber());
	      toPhoneNumberSend =customerRepository.findByPhoneNumber(phoneDTO.getToPhoneNumber());
	      
	      Transaction toPhoneSend = new Transaction();
	      Transaction fromPhoneSend = new Transaction();
	      Optional<Account> sendAccount = accountRepository.findByCustomerDetails
	            (fromPhoneNumberSend);
	      Account sendAccountDetails = sendAccount.get();
	      Optional<Account> receiveAccount = accountRepository.
	            findByCustomerDetails(toPhoneNumberSend);
	      Account receiveAccountDetails = receiveAccount.get();
	      if ((sendAccountDetails.getCurrentBalance() > phoneDTO.getAmount())) {
	         sendAccountDetails.setCurrentBalance(sendAccountDetails.getCurrentBalance()
	               - phoneDTO.getAmount());
	         accountRepository.save(sendAccountDetails);
	         receiveAccountDetails.setCurrentBalance(receiveAccountDetails.getCurrentBalance()
	               + phoneDTO.getAmount());
	         accountRepository.save(receiveAccountDetails);
	         toPhoneSend.setAccountNo(sendAccountDetails.getAccountNumber());
	         toPhoneSend.setAmount(phoneDTO.getAmount());
	         
	         toPhoneSend.setCurrentBal(sendAccountDetails.getCurrentBalance());
	         toPhoneSend.setMessage(phoneDTO.getRemarks());
	         toPhoneSend.setTransferMoney("credit Amount");
	         toPhoneSend.setCredit_debit("Credit");


	         toPhoneSend.setAccount(sendAccountDetails);
	         
	            java.util.Date date = new java.util.Date();
				long time = date.getTime();
				Timestamp transactiondate = new Timestamp(time);
				toPhoneSend.setTransactionDate(transactiondate);
	         
	         transactionRepository.save(toPhoneSend);
	         fromPhoneSend.setAccountNo(receiveAccountDetails.getAccountNumber());
	         fromPhoneSend.setAmount(phoneDTO.getAmount());
	         fromPhoneSend.setCurrentBal(receiveAccountDetails.getCurrentBalance());
	         fromPhoneSend.setMessage(phoneDTO.getRemarks());
	         fromPhoneSend.setTransferMoney(phoneDTO.getTransferMoney());
	         fromPhoneSend.setCredit_debit("debit");

	         java.util.Date date1 = new java.util.Date();
				long time1 = date1.getTime();
				Timestamp transactiondate1 = new Timestamp(time1);
				fromPhoneSend.setTransactionDate(transactiondate1);
	         
	         fromPhoneSend.setAccount(receiveAccountDetails);
	         transactionRepository.save(fromPhoneSend);
	         result = true;
	      } else {
	         result = false;
	         throw new UserDefinedException(" balance not there!!!!!!!!!!!!!1");
	      }
	      return result;
	   }
}