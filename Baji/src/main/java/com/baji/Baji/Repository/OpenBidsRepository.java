package com.baji.Baji.Repository;

import com.baji.Baji.Model.OpenBids;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OpenBidsRepository extends CrudRepository<OpenBids,Integer> {

    @Query(value = "Select * from open_bids where active= ?2 and matches_id=?1",nativeQuery = true)
    List<OpenBids> findAllByMatchesIdAAndActive(int matchesId, int active);
}
