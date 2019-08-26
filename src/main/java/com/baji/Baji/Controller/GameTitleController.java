package com.baji.Baji.Controller;

import com.baji.Baji.Model.GameTitle;
import com.baji.Baji.Model.Team;
import com.baji.Baji.Repository.GameTitleRepository;
import com.baji.Baji.Repository.TeamRespository;
import org.springframework.beans.factory.annotation.Autowired;
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
        response.put("team",teams);
        response.put("status","200");
        response.put("message","list of team");
        return response;
    }
}
