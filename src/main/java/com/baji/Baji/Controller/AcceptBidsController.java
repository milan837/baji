package com.baji.Baji.Controller;


import com.baji.Baji.Model.AcceptBids;
import com.baji.Baji.Model.GameTitle;
import com.baji.Baji.Model.Matches;
import com.baji.Baji.Model.OpenBids;
import com.baji.Baji.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AcceptBidsController {
    Map<String,Object> response;

    @Autowired
    AcceptBidsRepository repository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamRespository teamRespository;
    @Autowired
    MatchesRepository matchesRepository;
    @Autowired
    OpenBidsRepository openBidsRepository;

    int size=4;


    // accept the baji request
    @PostMapping("/accept/baji")
    public Map<String,Object> createOpenBid(@RequestHeader("Authorization") String authKey, @RequestBody Map<String,Object> request){
        response=new HashMap<>();
        String matchesId="",openBajiId="",userId="";

        if(authKey != null && !authKey.isEmpty()){
            if(userRepository.existsByAuthKey(authKey)){
                if(request.containsKey("openBajiId") &&
                        request.containsKey("userId") &&
                        request.containsKey("matchesId")
                ){
                    matchesId=request.get("matchesId").toString();
                    openBajiId=request.get("openBajiId").toString();
                    userId=request.get("userId").toString();
                    int teamId = 0;
                    Matches matches=matchesRepository.findById(Integer.valueOf(matchesId)).get();
                    OpenBids openBids=openBidsRepository.findById(Integer.valueOf(openBajiId)).get();

                    if(openBids.getTeam().getId() == matches.getTeamOne().getId()){
                        teamId=matches.getTeamTwo().getId();
                    }else if(openBids.getTeam().getId() == matches.getTeamTwo().getId()){
                        teamId=matches.getTeamOne().getId();
                    }

                    AcceptBids acceptBids=new AcceptBids();
                    acceptBids.setUser(userRepository.findById(Integer.valueOf(userId)).get());
                    acceptBids.setTeam(teamRespository.findById(teamId).get());
                    acceptBids.setMatches(matchesRepository.findById(Integer.valueOf(matchesId)).get());
                    Date date=new Date();
                    acceptBids.setTimeStamp(String.valueOf(date.getTime()));
                    acceptBids.setOpenBids(openBidsRepository.findById(Integer.valueOf(openBajiId)).get());
                    repository.save(acceptBids);

                    //setting open bid is active for one user
                    openBids.setActive(1);
                    openBidsRepository.save(openBids);

                    response.put("status","200");
                    response.put("message","accept the baji");

                }
            }else{
                response.put("status","200");
                response.put("message","invalid auth key");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key not specified");
        }
        return response;
    }


    // return all onbording baji list in descending order based on matches id
    @PostMapping("/onboard/baji/list")
    public Map<String,Object> getOpenBajiPage(@RequestHeader("Authorization") String authKey,@RequestBody Map<String,Object> request){
        response=new HashMap<>();
        if(authKey != null && !authKey.isEmpty()) {
            if (userRepository.existsByAuthKey(authKey)) {
                if(request.containsKey("matchesId") && request.containsKey("page")){
                    List<Map> onBoardBajiList=new ArrayList<>();
                    int matchesId=Integer.valueOf(request.get("matchesId").toString());
                    int page=Integer.valueOf(request.get("page").toString());

                    Page<AcceptBids> acceptBids=repository.findAllByMatchesId(matchesId, PageRequest.of(page,size, Sort.by("id").descending()));
                    acceptBids.getContent().forEach(s->{

                        Map<String,Object> teamOne=new HashMap<>();
                        Map<String,Object> teamTwo=new HashMap<>();
                        Map<String,Object> onBoardBaji=new HashMap<>();

                        teamTwo.put("teamId",s.getTeam().getId());
                        teamTwo.put("teamName",s.getTeam().getName());
                        teamTwo.put("teamImageUrl",s.getTeam().getImageUrl());
                        teamTwo.put("userId",s.getUser().getId());
                        teamTwo.put("username",s.getUser().getUsername());
                        teamTwo.put("userImageUrl",s.getUser().getImageUrl());

                        teamOne.put("teamId",s.getOpenBids().getTeam().getId());
                        teamOne.put("teamName",s.getOpenBids().getTeam().getName());
                        teamOne.put("teamImageUrl",s.getOpenBids().getTeam().getImageUrl());
                        teamOne.put("userId",s.getOpenBids().getUser().getId());
                        teamOne.put("username",s.getOpenBids().getUser().getUsername());
                        teamOne.put("userImageUrl",s.getOpenBids().getUser().getImageUrl());

                        onBoardBaji.put("amount",s.getOpenBids().getAmount());
                        onBoardBaji.put("timeStamp",s.getTimeStamp());
                        onBoardBaji.put("id",s.getId());
                        onBoardBaji.put("teamOne",teamOne);
                        onBoardBaji.put("teamTwo",teamTwo);

                        onBoardBajiList.add(onBoardBaji);
                    });

                    response.put("size",acceptBids.getSize());
                    response.put("page",acceptBids.getNumber());
                    response.put("status", "200");
                    response.put("message", "on bording baji list");
                    response.put("onboardBaji",onBoardBajiList);
                }else{
                    response.put("status", "200");
                    response.put("message", "request body invalid");
                }
            } else {
                response.put("status", "200");
                response.put("message", "invalid auth key");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key not specified");
        }

        return response;
    }


    // return all onbording baji list in descending order based on user id
    @PostMapping("/onboard/baji/list/user")
    public Map<String,Object> getOpenBajiPageByUserId(@RequestHeader("Authorization") String authKey,@RequestBody Map<String,Object> request){
        response=new HashMap<>();

        if(authKey != null && !authKey.isEmpty()) {
            if (userRepository.existsByAuthKey(authKey)) {
                if(request.containsKey("userId") && request.containsKey("page")){
                    List<Map> onBoardBajiList=new ArrayList<>();
                    int userId=Integer.valueOf(request.get("userId").toString());
                    int page=Integer.valueOf(request.get("page").toString());

                    Page<AcceptBids> acceptBids=repository.findAllByUserId(userId, PageRequest.of(page,size, Sort.by("id").descending()));
                    acceptBids.getContent().forEach(s->{

                        Map<String,Object> teamOne=new HashMap<>();
                        Map<String,Object> teamTwo=new HashMap<>();
                        Map<String,Object> onBoardBaji=new HashMap<>();

                        teamTwo.put("teamId",s.getTeam().getId());
                        teamTwo.put("teamName",s.getTeam().getName());
                        teamTwo.put("teamImageUrl",s.getTeam().getImageUrl());
                        teamTwo.put("userId",s.getUser().getId());
                        teamTwo.put("username",s.getUser().getUsername());
                        teamTwo.put("userImageUrl",s.getUser().getImageUrl());

                        teamOne.put("teamId",s.getOpenBids().getTeam().getId());
                        teamOne.put("teamName",s.getOpenBids().getTeam().getName());
                        teamOne.put("teamImageUrl",s.getOpenBids().getTeam().getImageUrl());
                        teamOne.put("userId",s.getOpenBids().getUser().getId());
                        teamOne.put("username",s.getOpenBids().getUser().getUsername());
                        teamOne.put("userImageUrl",s.getOpenBids().getUser().getImageUrl());

                        onBoardBaji.put("amount",s.getOpenBids().getAmount());
                        onBoardBaji.put("timeStamp",s.getTimeStamp());
                        onBoardBaji.put("id",s.getId());
                        onBoardBaji.put("teamOne",teamOne);
                        onBoardBaji.put("teamTwo",teamTwo);

                        onBoardBajiList.add(onBoardBaji);
                    });

                    response.put("size",acceptBids.getSize());
                    response.put("page",acceptBids.getNumber());
                    response.put("status", "200");
                    response.put("message", "on bording baji list");
                    response.put("onboardBaji",onBoardBajiList);
                }else{
                    response.put("status", "200");
                    response.put("message", "request body invalid");
                }
            } else {
                response.put("status", "200");
                response.put("message", "invalid auth key");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key not specified");
        }

        return response;
    }

    // return all onbording baji list in descending order
    @PostMapping("/active/onboardbaji/list")
    public Map<String,Object> getOnboardBajiList(@RequestHeader("Authorization") String authKey,@RequestBody Map<String,Object> request){
        response=new HashMap<>();
        if(authKey != null && !authKey.isEmpty()) {
            if (userRepository.existsByAuthKey(authKey)) {
                if(request.containsKey("page")){
                    List<Map> onBoardBajiList=new ArrayList<>();
                    int page=Integer.valueOf(request.get("page").toString());

                    Page<AcceptBids> acceptBids=repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
                    acceptBids.getContent().forEach(s->{

                        Map<String,Object> teamOne=new HashMap<>();
                        Map<String,Object> teamTwo=new HashMap<>();
                        Map<String,Object> onBoardBaji=new HashMap<>();

                        teamTwo.put("teamId",s.getTeam().getId());
                        teamTwo.put("teamName",s.getTeam().getName());
                        teamTwo.put("teamImageUrl",s.getTeam().getImageUrl());
                        teamTwo.put("userId",s.getUser().getId());
                        teamTwo.put("username",s.getUser().getUsername());
                        teamTwo.put("userImageUrl",s.getUser().getImageUrl());

                        teamOne.put("teamId",s.getOpenBids().getTeam().getId());
                        teamOne.put("teamName",s.getOpenBids().getTeam().getName());
                        teamOne.put("teamImageUrl",s.getOpenBids().getTeam().getImageUrl());
                        teamOne.put("userId",s.getOpenBids().getUser().getId());
                        teamOne.put("username",s.getOpenBids().getUser().getUsername());
                        teamOne.put("userImageUrl",s.getOpenBids().getUser().getImageUrl());

                        onBoardBaji.put("amount",s.getOpenBids().getAmount());
                        onBoardBaji.put("timeStamp",s.getTimeStamp());
                        onBoardBaji.put("id",s.getId());
                        onBoardBaji.put("gameTitle",s.getMatches().getGameTitle().getName());
                        onBoardBaji.put("teamOne",teamOne);
                        onBoardBaji.put("teamTwo",teamTwo);

                        onBoardBajiList.add(onBoardBaji);
                    });

                    response.put("size",acceptBids.getSize());
                    response.put("page",acceptBids.getNumber());
                    response.put("status", "200");
                    response.put("message", "on bording baji list");
                    response.put("onboardBaji",onBoardBajiList);
                }else{
                    response.put("status", "200");
                    response.put("message", "request body invalid");
                }
            } else {
                response.put("status", "200");
                response.put("message", "invalid auth key");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key not specified");
        }

        return response;
    }

}
