package com.baji.Baji.Controller;

import com.baji.Baji.Model.Transaction;
import com.baji.Baji.Model.User;
import com.baji.Baji.Repository.TransactionRepository;
import com.baji.Baji.Repository.UserRepository;
import com.baji.Baji.Utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TransactionController {
    Map<String,Object> response;

    @Autowired
    TransactionRepository repository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/save/transaction")
    public Map<String,Object> getTransactionResponse(@RequestHeader("Authorization") String authKey, @RequestBody Map<String,Object> request){
        response=new HashMap<>();

        if(authKey != null && !authKey.isEmpty()){
            if(userRepository.existsByAuthKey(authKey)){
                String orderId=request.get("orderId").toString();
                String userId=request.get("userId").toString();
                String timeStamp=request.get("timeStamp").toString();
                String amount=request.get("amount").toString();

                User user=userRepository.findById(Integer.valueOf(userId)).get();

                if(!repository.existsByOrderIdAndUserAndTimeStamp(orderId,user,timeStamp)){
                    Transaction transaction=new Transaction();
                    transaction.setAmount(Integer.parseInt(amount));
                    transaction.setOrderId(orderId);
                    transaction.setUser(user);
                    transaction.setTimeStamp(timeStamp);
                    repository.save(transaction);

                    response.put("status","200");
                    response.put("message","transaction inserted");
                }else{
                    response.put("status","200");
                    response.put("message","transaction already inserted");
                }

            }else{
                response.put("status","200");
                response.put("message","Invalid auth key");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key not pass");
        }
        return  response;
    }

    @GetMapping("/list/transaction/{userId}")
    public Map<String,Object> getTransactionByUser(@RequestHeader("Authorization") String authKey, @PathVariable("userId") String userId, @RequestParam("page") int page){
        response=new HashMap<>();

        if(authKey != null && !authKey.isEmpty()){
            if(userRepository.existsByAuthKey(authKey)){

                Page<Transaction> transactions= repository.findAllByUser(userRepository.findById(Integer.valueOf(userId)).get(), PageRequest.of(page, Constant.PAGE_SIZE, Sort.by("id").descending()));

                List<Map> transList=new ArrayList<>();
                transactions.forEach(s->{
                    Map<String,Object> data=new HashMap<>();
                    data.put("id",s.getId());
                    data.put("orderId",s.getOrderId());
                    data.put("amount",s.getAmount());
                    data.put("timeStamp",s.getTimeStamp());

                    transList.add(data);
                });
                response.put("transactions",transList);
                response.put("status","200");
                response.put("message","transaction inserted");
            }else{
                response.put("status","200");
                response.put("message","Invalid auth key");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key not pass");
        }
        return  response;
    }


    @GetMapping("/transaction/list")
    public Map<String,Object> getTransactionList(@RequestHeader("Authorization") String authKey, @RequestParam("page") int page){
        response=new HashMap<>();

        if(authKey != null && !authKey.isEmpty()){
            if(userRepository.existsByAuthKey(authKey)){

                Page<Transaction> transactions= repository.findAll(PageRequest.of(page, Constant.PAGE_SIZE, Sort.by("id").descending()));

                List<Map> transList=new ArrayList<>();
                transactions.forEach(s->{

                    Map<String,Object> data=new HashMap<>();
                    Map<String,Object> user=new HashMap<>();

                    user.put("userId",s.getUser().getId());
                    user.put("username",s.getUser().getUsername());
                    user.put("imageUrl",s.getUser().getImageUrl());
                    user.put("amount",s.getUser().getAmount());
                    user.put("phoneNumber",s.getUser().getPhoneNumber());

                    data.put("id",s.getId());
                    data.put("orderId",s.getOrderId());
                    data.put("amount",s.getAmount());
                    data.put("timeStamp",s.getTimeStamp());
                    data.put("user",user);

                    transList.add(data);
                });
                response.put("transactions",transList);
                response.put("status","200");
                response.put("message","transaction inserted");
            }else{
                response.put("status","200");
                response.put("message","Invalid auth key");
            }
        }else{
            response.put("status","200");
            response.put("message","auth key not pass");
        }
        return  response;
    }
}
