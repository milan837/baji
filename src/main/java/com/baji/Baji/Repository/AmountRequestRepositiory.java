package com.baji.Baji.Repository;

import com.baji.Baji.Model.AmountRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AmountRequestRepositiory extends CrudRepository<AmountRequest,Integer>, PagingAndSortingRepository<AmountRequest,Integer> {
}
