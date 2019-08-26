package com.baji.Baji.Repository;

import com.baji.Baji.Model.OpenBids;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OpenBidsRepository extends CrudRepository<OpenBids,Integer> {

    Page<OpenBids> findAllByMatchesIdAndActive(int matchesId, int active,Pageable pageable);
}
