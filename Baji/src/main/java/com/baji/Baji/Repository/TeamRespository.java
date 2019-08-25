package com.baji.Baji.Repository;

import com.baji.Baji.Model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRespository extends CrudRepository<Team,Integer> {

    boolean existsById(int id);
}
