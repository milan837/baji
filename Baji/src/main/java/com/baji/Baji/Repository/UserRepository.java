package com.baji.Baji.Repository;

import com.baji.Baji.Model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User,Integer> {

    boolean existsByAuthKey(String authKey);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndOtp(String phoneNumber,String otp);
    User findByPhoneNumber(String phoneNumber);


    @Modifying
    @Query(value = "update `user` set otp=?2 where phone_number=?1",nativeQuery = true)
    int updateUserOtp(@Param("number") String phoneNumber,@Param("otp") String otp);

}
