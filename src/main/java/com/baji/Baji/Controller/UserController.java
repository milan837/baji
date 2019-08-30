package com.baji.Baji.Controller;

import com.baji.Baji.Model.AcceptBids;
import com.baji.Baji.Model.OpenBids;
import com.baji.Baji.Model.User;
import com.baji.Baji.Repository.AcceptBidsRepository;
import com.baji.Baji.Repository.OpenBidsRepository;
import com.baji.Baji.Repository.UserRepository;
import com.baji.Baji.Utils.Constant;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    Map<String,Object> response;

    @Autowired
    UserRepository repository;
    @Autowired
    AcceptBidsRepository acceptBidsRepository;

    @Autowired
    OpenBidsRepository openBidsRepository;

    int size= Constant.PAGE_SIZE;

    @PostMapping("/user/register")
    public Map<String,Object> registerUser(@RequestBody Map<String,Object> request){
        response=new HashMap<>();
        String phoneNumber="";

        if(request.containsKey("phoneNumber")){
            phoneNumber=request.get("phoneNumber").toString();
            if(request.get("phoneNumber").toString().isEmpty()){
                response.put("status","200");
                response.put("message","please pass the phone number");
            }else if(request.get("phoneNumber").toString().length() != 10){
                response.put("status","200");
                response.put("message","please pass valid  phone number");
            }else{
                User user=new User();
                if(repository.existsByPhoneNumber(phoneNumber)){
                    user=repository.findByPhoneNumber(phoneNumber);
//                    repository.updateUserOtp(phoneNumber,getOTP());
                    //TODO:: check the active value to proceect for
                }else{
                    user.setPhoneNumber(phoneNumber);
                    user.setAuthKey(getAuthKey());
                    repository.save(user);
                }
                response.put("user",user);
                response.put("status","200");
                response.put("message","register sucessfully");
            }
        }else{
            response.put("status","200");
            response.put("message","please send the body properly");
        }

        return response;

    }

    public String getOTP(){
        return "2144";
    }

    public String getAuthKey(){
        return "asdasdsadasdasd";
    }

    @PostMapping("/user/otpVerify")
    public Map<String,Object> verifyOtp(@RequestBody Map<String,Object> request){
        response=new HashMap<>();
        String otp="",phoneNumber="";

        if(request.containsKey("otp") && request.containsKey("phoneNumber")){
            otp=request.get("otp").toString();
            phoneNumber=request.get("phoneNumber").toString();

            if(repository.existsByPhoneNumberAndOtp(phoneNumber,otp)){
                response.put("status","200");
                response.put("message","number verified sucessfully");
                User user=repository.findByPhoneNumber(phoneNumber);
                user.setAuthKey(getAuthKey());
                repository.save(user);
                response.put("user_details",user);
            }else{
                response.put("status","200");
                response.put("message","invalid otp & number");
            }
        }else {
            response.put("status","200");
            response.put("message","please send the body properly");
        }

        return response;
    }

    @PostMapping("/user/saveInfo")
    public Map<String,Object> saveInfo(@RequestHeader("Authorization") String authKey,@RequestBody Map<String,Object> request){
        response=new HashMap<>();
        String userId="",username="",imageUrl="";

        if(!authKey.isEmpty() && authKey !=null){
            if(request.containsKey("userId") &&
                    request.containsKey("username") &&
                    request.containsKey("imageUrl")){

                userId=request.get("userId").toString();
                username=request.get("username").toString();
                imageUrl=request.get("imageUrl").toString();

                User user=repository.findById(Integer.valueOf(userId)).get();
                if(user.getAuthKey().equals(authKey)){
                    user.setUsername(username);
                    user.setImageUrl(imageUrl);
                    user.setActive(1);
                    repository.save(user);
                    response.put("status","200");
                    response.put("message","save data sucessfully");
                    response.put("user_details",user);
                }else{
                    response.put("status","200");
                    response.put("message","invalid access Token");
                }


            }else{
                response.put("status","200");
                response.put("message","please send the body properly");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key is missing");
        }

        return response;
    }

    @GetMapping("/user/profile/{id}")
    public Map<String, Object> getUserDetails(@PathVariable("id") String userId){
        response=new HashMap<>();

        if(userId != null && !userId.isEmpty()){
            int uId=Integer.valueOf(userId);
            if(repository.existsById(uId)){
                User user=repository.findById(uId).get();

                //user details response
                    Map<String,Object> userDetails=new HashMap<>();
                    userDetails.put("username",user.getUsername());
                    userDetails.put("email",user.getEmail());
                    userDetails.put("imageUrl",user.getImageUrl());
                    userDetails.put("amount",user.getAmount());
                    userDetails.put("totalBaji","123");
                    userDetails.put("loseBaji","123");
                    userDetails.put("winBaji","123");
                    response.put("user_details",userDetails);


                    //On board baji list via userId
                List<Map> onBoardBajiList=new ArrayList<>();
                Page<AcceptBids> acceptBids=acceptBidsRepository.findAllByUserId(uId, PageRequest.of(0,size, Sort.by("id").descending()));
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


                //open baji list on response via userId
                List<Map> openBidsList = new ArrayList<>();
                Page<OpenBids> openBids=openBidsRepository.findAllByUserIdAndActive(uId,0, PageRequest.of(0,size, Sort.by("id").descending()));
                openBids.getContent().forEach(s->{
                    Map<String,Object> openBidDetails=new HashMap<>();

                    //formatting the response data
                    openBidDetails.put("id",s.getId());
                    openBidDetails.put("amount",s.getAmount());
                    openBidDetails.put("timeStamp",s.getTimeStamp());
                    openBidDetails.put("team",s.getTeam());

                    openBidsList.add(openBidDetails);
                });

                response.put("onboardBaji",onBoardBajiList);
                response.put("openBaji",openBidsList);
                response.put("size",acceptBids.getSize());
                response.put("page",acceptBids.getNumber());
                response.put("status","200");
                response.put("message","user details access sucessfully");
            }else{
                response.put("status","200");
                response.put("message","user details not exist");
            }

        }else{
            response.put("status","200");
            response.put("message","userId empty");
        }

        return response;
    }

    @GetMapping("/user/list")
    public Map<String,Object> getUserList(@RequestHeader("Authorization") String authKey){
        response=new HashMap<>();

        if(authKey != null && !authKey.isEmpty()){
            if(repository.existsByAuthKey(authKey)){
                List<Map> userList=new ArrayList<>();

                Page<User> user=repository.findAll(PageRequest.of(0,size, Sort.by("id").descending()));

                user.getContent().forEach(s->{
                    Map<String,Object> userDetails=new HashMap<>();
                    Map<String,Object> bajiDetails=new HashMap<>();
                    userDetails.put("userId",s.getId());
                    userDetails.put("username",s.getUsername());
                    userDetails.put("imageUrl",s.getImageUrl());
                    userDetails.put("email",s.getEmail());
                    userDetails.put("amount",s.getAmount());

                    //TODO: baji detail from baji table pending
                    bajiDetails.put("total","1234");
                    bajiDetails.put("win","344");
                    bajiDetails.put("lose","22");

                    userDetails.put("baji",bajiDetails);
                    userList.add(userDetails);
                });

                response.put("status","200");
                response.put("page",user.getNumber());
                response.put("size",user.getSize());
                response.put("message","fetch user details");
                response.put("users",userList);
            }else{
                response.put("status","200");
                response.put("message","unAuthorize Auth key");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key not pass");
        }
        return response;
    }

    @PostMapping("/user/details/update")
    public Map<String,Object> updateUserDetails(@RequestHeader("Authorization") String authKey,@RequestBody Map<String,Object> request){
        response=new HashMap<>();

        if(authKey != null && !authKey.isEmpty()){
            if(repository.existsByAuthKey(authKey)){

                if(request.containsKey("userId") && request.containsKey("username") && request.containsKey("imageUrl")){
                    String userId,username,imageUrl;
                    userId=request.get("userId").toString();
                    username=request.get("username").toString();
                    imageUrl=request.get("imageUrl").toString();

                    User user=repository.findById(Integer.valueOf(userId)).get();
                    user.setImageUrl(imageUrl);
                    user.setUsername(username);
                    repository.save(user);
                    response.put("status","200");
                    response.put("message","update sucess fully");
                }else{
                    response.put("status","200");
                    response.put("message","request body is missing some field");
                }
            }else{
                response.put("status","200");
                response.put("message","unAuthorize Auth key");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key not pass");
        }

        return response;
    }


    @GetMapping("/test")
    public String getTest(){
        return "ok";
    }

}
