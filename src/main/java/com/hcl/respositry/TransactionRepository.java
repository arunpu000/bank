package com.hcl.respositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcl.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("from Transaction where accountNo=:accountNo and month(transactionDate)=:month and year(transactionDate)=:year")
	List<Transaction> getTransactionHistory(@Param("accountNo")long accountNo, @Param("month")int month, @Param("year")int year);

}
