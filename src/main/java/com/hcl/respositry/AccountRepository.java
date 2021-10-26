package com.hcl.respositry;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hcl.entity.Account;
import com.hcl.entity.Customer;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByAccountNumber(long accountNumber);
	
	Optional<Account> findByCustomerDetails(Customer customer);

	

}
