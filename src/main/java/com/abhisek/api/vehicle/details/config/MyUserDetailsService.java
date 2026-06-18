package com.abhisek.api.vehicle.details.config;

import com.abhisek.api.vehicle.details.dao.UserRepo;
import com.abhisek.api.vehicle.details.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails=null;
        if(username!=null && !username.isEmpty()){
            UserEntity userEntity = userRepo.findByUsername(username);
            if(userEntity!=null){
                userDetails = new MyUserDetails(userEntity);
            }
        }
        return userDetails;
    }
}
