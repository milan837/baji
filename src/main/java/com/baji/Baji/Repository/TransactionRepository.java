package com.baji.Baji.Repository;

import com.baji.Baji.Model.Transaction;
import com.baji.Baji.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TransactionRepository extends CrudRepository<Transaction,Integer> {

    Page<Transaction> findAllByUser(User user, Pageable pageable);
    Page<Transaction> findAll(Pageable pageable);

    boolean existsByOrderIdAndUserAndTimeStamp(String orderId,User user,String timeStamp);
}
