package com.baji.Baji.Controller;

import com.baji.Baji.Model.GameTitle;
import com.baji.Baji.Model.Matches;
import com.baji.Baji.Model.Team;
import com.baji.Baji.Repository.GameTitleRepository;
import com.baji.Baji.Repository.MatchesRepository;
import com.baji.Baji.Repository.TeamRespository;
import com.baji.Baji.Repository.UserRepository;
import com.baji.Baji.Utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GameTitleController {
    Map<String,Object> response;

    @Autowired
    GameTitleRepository repository;
    @Autowired
    MatchesRepository matchesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamRespository teamRespository;

    @PostMapping("/game/title/add")
    public Map<String,Object> addTeam(@RequestBody GameTitle request){
        response=new HashMap<String,Object>();

        if(!request.getName().isEmpty() && !request.getImageUrl().isEmpty()){
            repository.save(request);
            response.put("status","200");
            response.put("message","save sucessfully");
        }else{
            response.put("status","200");
            response.put("message","body not correct");
        }

        return response;
    }

    @GetMapping("/game/title/details/{id}")
    public Map<String,Object> getTeamDetails(@PathVariable("id") int gameId){
        response=new HashMap<>();

        if(repository.existsById(gameId)){
            GameTitle gameTitle=repository.findById(gameId).get();
            response.put("team",gameTitle);
            response.put("status","200");
            response.put("message","team details");
        }else{
            response.put("status","200");
            response.put("message","team not exist");
        }


        return response;
    }

    @GetMapping("/game/title/list")
    public Map<String,Object> getTeamList(){
        response=new HashMap<>();
        List<GameTitle> teams=new ArrayList<>();
        repository.findAll().forEach(teams::add);
        response.put("game",teams);
        response.put("status","200");
        response.put("message","list of team");
        return response;
    }

    //return the list of games with nested matches list main page api
    @GetMapping("/game/match/list")
    public Map<String,Object> getGameWithMatchList(){
        response=new HashMap<>();
        List<Map> matchesListMap=new ArrayList<>();
        List<Map> gameListMap=new ArrayList<>();

        repository.findAll().forEach(s->{
            Map<String,Object> games=new HashMap<>();

            matchesRepository.findAllByGameTitleId(s.getId(), PageRequest.of(0, Constant.PAGE_SIZE, Sort.by("id").descending())).forEach(m->{

                Map<String,Object> matches=new HashMap<>();

                matches.put("id",m.getId());
                matches.put("timeStamp",m.getTimeStamp());
                matches.put("winnerTeam",m.getWinnerTeam());
                matches.put("teamOne",m.getTeamOne());
                matches.put("teamTwo",m.getTeamTwo());

                matchesListMap.add(matches);
            });

            games.put("id",s.getId());
            games.put("name",s.getName());
            games.put("imageUrl",s.getImageUrl());
            games.put("matches",matchesListMap);

            gameListMap.add(games);
        });

        response.put("games",gameListMap);
        response.put("status","200");
        response.put("message","list of team");
        return response;
    }
}
