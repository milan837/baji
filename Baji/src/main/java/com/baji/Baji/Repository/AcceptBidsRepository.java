package com.baji.Baji.Repository;

import com.baji.Baji.Model.AcceptBids;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
public interface AcceptBidsRepository extends CrudRepository<AcceptBids,Integer> {

    @Query(value = "select * from accept_bids where open_bids_id=?1",nativeQuery = true)
    List<AcceptBids> findAllByOpenBidsId(int openBidsId);

    @Query(value = "select * from accept_bids where matches_id=?1",nativeQuery = true)
    List<AcceptBids> findAllByMatchesId(int matchesId);

}
