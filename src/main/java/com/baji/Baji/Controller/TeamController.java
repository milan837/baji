package com.baji.Baji.Controller;

import com.baji.Baji.Model.Team;
import com.baji.Baji.Repository.TeamRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeamController {
    Map<String,Object> response;

    @Autowired
    TeamRespository repository;


    @PostMapping("/team/add")
    public Map<String,Object> addTeam(@RequestBody Team request){
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

    @GetMapping("/team/details/{id}")
    public Map<String,Object> getTeamDetails(@PathVariable("id") int teamId){
        response=new HashMap<>();

        if(repository.existsById(teamId)){
            Team team=repository.findById(teamId).get();
            response.put("team",team);
            response.put("status","200");
            response.put("message","team details");
        }else{
            response.put("status","200");
            response.put("message","team not exist");
        }


        return response;
    }

    @GetMapping("/team/list")
    public Map<String,Object> getTeamList(){
        response=new HashMap<>();
        List<Team> teams=new ArrayList<>();
        repository.findAll().forEach(teams::add);
        response.put("team",teams);
        response.put("status","200");
        response.put("message","list of team");
        return response;
    }


}
