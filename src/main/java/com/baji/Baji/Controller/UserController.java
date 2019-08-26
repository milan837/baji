package com.baji.Baji.Controller;

import com.baji.Baji.Model.User;
import com.baji.Baji.Repository.UserRepository;
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
                    repository.updateUserOtp(phoneNumber,getOTP());
                    response.put("status","200");
                    response.put("message","change otp sucessfully");

                }else{
                    user.setPhoneNumber(phoneNumber);
                    user.setOtp(getOTP());
                    repository.save(user);
                    response.put("status","200");
                    response.put("message","register sucessfully");
                }
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
                response.put("status","200");
                response.put("message","user details access sucess fully");
                    Map<String,Object> userDetails=new HashMap<>();
                    Map<String,Object> onBoardBaji=new HashMap<>();
                    Map<String,Object> ofBoardBaji=new HashMap<>();
                    Map<String,Object> pendingBaji=new HashMap<>();

                    List onBoardList=new ArrayList();
                    List ofBoardList=new ArrayList();
                    List pendingList=new ArrayList();

                    //user details response
                    userDetails.put("username",user.getUsername());
                    userDetails.put("email",user.getEmail());
                    userDetails.put("imageUrl",user.getImageUrl());
                    userDetails.put("amount",user.getAmount());
                    userDetails.put("totalBaji","123");
                    userDetails.put("loseBaji","123");
                    userDetails.put("winBaji","123");
                    response.put("user_details",userDetails);


                    //TODO: list onboarind ofboardn and pending baji from baji table in profile
                    response.put("onboardBaji",onBoardList);
                    response.put("ofboardBaji",ofBoardList);
                    response.put("pendingBaji",pendingList);

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

                Page<User> user=repository.findAll(PageRequest.of(1,2, Sort.by("id").descending()));

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

}
