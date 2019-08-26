package com.baji.Baji.Controller;

import com.baji.Baji.Model.*;
import com.baji.Baji.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MatchesController {
    Map<String,Object> response;

    @Autowired
    MatchesRepository repository;
    @Autowired
    TeamRespository teamRespository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameTitleRepository gameTitleRepository;
    @Autowired
    OpenBidsRepository openBidsRepository;
    @Autowired
    AcceptBidsRepository acceptBidsRepository;

    @PostMapping("/matches/add")
    public Map<String,Object> addTeam(@RequestBody Map<String,Object> request){
        response=new HashMap<String,Object>();
        int teamOneId,teamTwoId,gameTitleId;
        String matchTime;
        if(request.containsKey("teamOneId") && request.containsKey("teamTwoId") && request.containsKey("time") && request.containsKey("gameTitleId")){
            teamOneId=Integer.valueOf(request.get("teamOneId").toString());
            teamTwoId=Integer.valueOf(request.get("teamTwoId").toString());
            matchTime=request.get("time").toString();
            gameTitleId=Integer.valueOf(request.get("gameTitleId").toString());

           if(teamRespository.existsById(teamOneId) && teamRespository.existsById(teamTwoId) && gameTitleRepository.existsById(gameTitleId)){
               if(repository.existsByTeamOneIdAndTeamTwoIdAndGameTitleId(teamOneId,teamTwoId,gameTitleId)){
                   response.put("status","200");
                   response.put("message","match already schedule");
               }else{
                   Matches matches=new Matches();
                   matches.setTeamOne(teamRespository.findById(teamOneId).get());
                   matches.setTeamTwo(teamRespository.findById(teamTwoId).get());
                   matches.setTimeStamp(matchTime);
                   matches.setGameTitle(gameTitleRepository.findById(gameTitleId).get());
                   repository.save(matches);
                   response.put("status","200");
                   response.put("message","match scedulle sucess fully");
               }
           }else{
               response.put("status","200");
               response.put("message","team donot exist");
           }
        }else{
            response.put("status","200");
            response.put("message","send request body properly");
        }
        return response;
    }

    @GetMapping("/matches/details/{id}")
    public Map<String,Object> getTeamDetails(@RequestHeader("Authorization") String authKey,@PathVariable("id") int matchesId){
        response=new HashMap<>();
        if(userRepository.existsByAuthKey(authKey)){
            List<Map> openBidsList=new ArrayList<>();
            List<AcceptBids> acceptBidsList=new ArrayList<>();
            //TODO: add the open bidas and onBoard bids in a response

            if(repository.existsById(matchesId)){
                Matches matches=repository.findById(matchesId).get();

                //makeing response to open baji list elements
                Page<OpenBids> openBids=openBidsRepository.findAllByMatchesIdAndActive(matches.getId(),0,PageRequest.of(0,3,Sort.by("id").descending()));
                openBids.getContent().forEach(s->{

                    Map<String,Object> userDetails=new HashMap<>();
                    Map<String,Object> openBidDetails=new HashMap<>();
                    userDetails.put("id",s.getUser().getId());
                    userDetails.put("username",s.getUser().getUsername());
                    userDetails.put("imageUrl",s.getUser().getImageUrl());

                    //formattting the response data
                    openBidDetails.put("id",s.getId());
                    openBidDetails.put("amount",s.getAmount());
                    openBidDetails.put("timeStamp",s.getTimeStamp());

                    openBidDetails.put("user",userDetails);
                    openBidDetails.put("team",s.getTeam());
                    openBidsList.add(openBidDetails);

                });

                //making list to onBoard baji list elements

                List<Map> onBoardBajiList=new ArrayList<>();
                Page<AcceptBids> acceptBids=acceptBidsRepository.findAllByMatchesId(matchesId,PageRequest.of(0,3,Sort.by("id").descending()));
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

                response.put("page",openBids.getNumber());
                response.put("size",openBids.getSize());

                response.put("status","200");
                response.put("matches",matches);
                response.put("openBids",openBidsList);
                response.put("onboardBids",onBoardBajiList);
                response.put("message","match details");
            }else{
                response.put("status","200");
                response.put("message","match not exist");
            }
        }else{
            response.put("status","200");
            response.put("message","invalid auth key");
        }


        return response;
    }

    @GetMapping("/matches/list")
    public Map<String,Object> getTeamList(@RequestHeader("Authorization") String authKey){
        response=new HashMap<>();
        if(userRepository.existsByAuthKey(authKey)){
            List<Matches> matchesList=new ArrayList<>();
            Page<Matches> matches=repository.findAll(PageRequest.of(0,4, Sort.by("id").descending()));

            matches.getContent().forEach(s->{
                matchesList.add(s);
            });

            response.put("page",matches.getNumber());
            response.put("size",matches.getSize());
            response.put("status","200");
            response.put("matches",matchesList);
            response.put("message","match list");
        }else{
            response.put("status","200");
            response.put("message","invalid auth key");
        }

        return response;
    }


}
