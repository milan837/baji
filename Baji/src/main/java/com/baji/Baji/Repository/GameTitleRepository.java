package com.baji.Baji.Repository;

import com.baji.Baji.Model.GameTitle;
import org.springframework.data.repository.CrudRepository;

public interface GameTitleRepository extends CrudRepository<GameTitle,Integer> {
    boolean existsById(int id);
}
