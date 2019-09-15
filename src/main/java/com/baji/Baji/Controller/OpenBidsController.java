package com.baji.Baji.Controller;

import com.baji.Baji.Model.Matches;
import com.baji.Baji.Model.OpenBids;
import com.baji.Baji.Repository.MatchesRepository;
import com.baji.Baji.Repository.OpenBidsRepository;
import com.baji.Baji.Repository.TeamRespository;
import com.baji.Baji.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OpenBidsController {
    Map<String,Object> response;

    @Autowired
    OpenBidsRepository repository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamRespository teamRespository;
    @Autowired
    MatchesRepository matchesRepository;

    int size=5;

    @PostMapping("/openbaji/create")
    public Map<String,Object> createOpenBid(@RequestHeader("Authorization") String authKey, @RequestBody Map<String,Object> request){
        response=new HashMap<>();
        String amount="",matchesId="",teamId="",userId="";

        if(authKey != null && !authKey.isEmpty()){
            if(userRepository.existsByAuthKey(authKey)){
                if(request.containsKey("amount") &&
                        request.containsKey("matchesId") &&
                        request.containsKey("teamId") &&
                        request.containsKey("userId")
                ){
                    amount=request.get("amount").toString();
                    matchesId=request.get("matchesId").toString();
                    teamId=request.get("teamId").toString();
                    userId=request.get("userId").toString();
                    if(!amount.isEmpty() && !matchesId.isEmpty() && !teamId.isEmpty() && !userId.isEmpty()){
                        OpenBids openBids=new OpenBids();
                        openBids.setActive(0);
                        openBids.setAmount(Integer.valueOf(amount));
                        openBids.setTeam(teamRespository.findById(Integer.valueOf(teamId)).get());
                        openBids.setMatches(matchesRepository.findById(Integer.valueOf(matchesId)).get());
                        Date date=new Date();
                        openBids.setTimeStamp(String.valueOf(date.getTime()));
                        openBids.setUser(userRepository.findById(Integer.valueOf(userId)).get());
                        repository.save(openBids);
                        response.put("status","200");
                        response.put("message","baji created sucess fully");
                    }else{
                        response.put("status","200");
                        response.put("message","field missing in request body");
                    }
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



    @PostMapping("/openBaji/list")
    public Map<String,Object> getOpenBajiPage(@RequestHeader("Authorization") String authKey,@RequestBody Map<String,Object> request){
        response=new HashMap<>();

        if(authKey != null && !authKey.isEmpty()) {
            if (userRepository.existsByAuthKey(authKey)) {
                if(request.containsKey("matchesId") && request.containsKey("page")){
                    int matchesId=Integer.valueOf(request.get("matchesId").toString());
                    int page=Integer.valueOf(request.get("page").toString());
                    if(repository.existsById(matchesId)) {
                        Matches matches = matchesRepository.findById(matchesId).get();
                        List<Map> openBidsList = new ArrayList<>();

                        Page<OpenBids> openBids=repository.findAllByMatchesIdAndActive(matches.getId(),0, PageRequest.of(page,size, Sort.by("id").descending()));

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

                        response.put("size",openBids.getSize());
                        response.put("page",openBids.getNumber());
                        response.put("status", "200");
                        response.put("message", "list of open bid of match id");
                        response.put("openBids", openBidsList);

                    }else{
                        response.put("size",0);
                        response.put("page",0);
                        response.put("status", "200");
                        response.put("message", "match id do not exist ");
                        response.put("openBids", new ArrayList<>());
                    }
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

    @PostMapping("/openBaji/list/user")
    public Map<String,Object> getOpenBajiPageByUserId(@RequestHeader("Authorization") String authKey,@RequestBody Map<String,Object> request){
        response=new HashMap<>();
        if(authKey != null && !authKey.isEmpty()) {
            if (userRepository.existsByAuthKey(authKey)) {
                if(request.containsKey("userId") && request.containsKey("page")){
                    int userId=Integer.valueOf(request.get("userId").toString());
                    int page=Integer.valueOf(request.get("page").toString());
                    if(userRepository.existsById(userId)) {
                        List<Map> openBidsList = new ArrayList<>();

                        Page<OpenBids> openBids=repository.findAllByUserIdAndActive(userId,0, PageRequest.of(page,size, Sort.by("id").descending()));

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

                        response.put("size",openBids.getSize());
                        response.put("page",openBids.getNumber());
                        response.put("status", "200");
                        response.put("message", "list of open bid of match id");
                        response.put("openBids", openBidsList);

                    }else{
                        response.put("size",0);
                        response.put("page",0);
                        response.put("status", "200");
                        response.put("message", "user id do not exist");
                        response.put("openBids", new ArrayList<>());
                    }
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
