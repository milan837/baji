package com.baji.Baji.Repository;

import com.baji.Baji.Model.AcceptBids;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
public interface AcceptBidsRepository extends CrudRepository<AcceptBids,Integer> {

    @Query(value = "select * from accept_bids where open_bids_id=?1",nativeQuery = true)
    List<AcceptBids> findAllByOpenBidsId(int openBidsId);

    Page<AcceptBids> findAllByMatchesId(int matchesId, Pageable pageable);

    Page<AcceptBids> findAllByUserId(int userId,Pageable pageable);

}
