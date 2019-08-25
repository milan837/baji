package com.baji.Baji.Repository;

import com.baji.Baji.Model.Matches;
import org.springframework.data.repository.CrudRepository;

public interface MatchesRepository extends CrudRepository<Matches,Integer> {
    boolean existsById(int id);
    boolean existsByTeamOneIdAndTeamTwoIdAndGameTitleId(int teamOneId,int teamTwoId,int gameTitleId);
}
