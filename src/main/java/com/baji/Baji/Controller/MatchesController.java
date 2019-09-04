package com.baji.Baji.Controller;

import com.baji.Baji.Model.*;
import com.baji.Baji.Repository.*;
import com.baji.Baji.Utils.Constant;
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

    int size= Constant.PAGE_SIZE;

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
                Page<OpenBids> openBids=openBidsRepository.findAllByMatchesIdAndActive(matches.getId(),0,PageRequest.of(0,size,Sort.by("id").descending()));
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
                Page<AcceptBids> acceptBids=acceptBidsRepository.findAllByMatchesId(matchesId,PageRequest.of(0,size,Sort.by("id").descending()));
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
            Page<Matches> matches=repository.findAll(PageRequest.of(0,size, Sort.by("id").descending()));

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

    // return the list of matches based on games id ex UEFA=>Match1,match2...
    @PostMapping("/matches/list/game_title/{gameTitleId}")
    public Map<String,Object> getTeamList(@RequestHeader("Authorization") String authKey,@PathVariable("gameTitleId") int gameTitleId,@RequestBody Map<String,Object> request){
        response=new HashMap<>();

        if(userRepository.existsByAuthKey(authKey)){
            List<Map> matchesList=new ArrayList<>();
            if(request.containsKey("page")){
                int page=Integer.valueOf(request.get("page").toString());
                Page<Matches> matches=repository.findAllByGameTitleId(gameTitleId,PageRequest.of(page,size, Sort.by("id").descending()));

                matches.getContent().forEach(s->{
                    Map<String,Object> matchesObj=new HashMap<>();
                    matchesObj.put("id",s.getId());
                    matchesObj.put("timeStamp",s.getTimeStamp());
                    matchesObj.put("winner",s.getWinnerTeam());
                    matchesObj.put("teamOne",s.getTeamOne());
                    matchesObj.put("teamTwo",s.getTeamTwo());

                    matchesList.add(matchesObj);
                });

                response.put("page",matches.getNumber());
                response.put("size",matches.getSize());
                response.put("status","200");
                response.put("matches",matchesList);
                response.put("message","match list");
            }else{
                response.put("status","200");
                response.put("message","play load missing key");
            }

        }else{
            response.put("status","200");
            response.put("message","invalid auth key");
        }

        return response;
    }


}
