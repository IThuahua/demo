package com.example.demo.ioc;

import com.example.demo.UserVo;
import org.springframework.context.annotation.Bean;

/**
 * @Author zhoushenghua on
 */
//@Configuration
public class Beans {

    @Bean(name = "zsh")
    public UserVo bu(){
        UserVo userVo = new UserVo();
        userVo.setName("zzsshh");
        userVo.setAge(18);
        userVo.setAddress("广水");
        return userVo;
    }


}
