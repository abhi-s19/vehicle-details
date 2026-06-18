package com.abhisek.api.vehicle.details.controller;

import com.abhisek.api.vehicle.details.config.MyUserDetailsService;
import com.abhisek.api.vehicle.details.dto.UserDto;
import com.abhisek.api.vehicle.details.security.JwtUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api-vehicle-details/v1/auth")
public class AuthController {

    @Autowired
    private JwtUtill jwtUtill;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<Object> generateJwt(@RequestBody UserDto userDto){

        Map<String,Object> resp = new HashMap<>();

        if(userDto.getUsername()!=null && !userDto.getUsername().isEmpty()
        && userDto.getPassword()!=null && !userDto.getPassword().isEmpty()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
            if(userDetails!=null){
                resp.put("status","success");
                resp.put("jwtToken",jwtUtill.generateToken(userDetails));
                return new ResponseEntity<>(resp, HttpStatus.OK);
            }
        }
        throw new UsernameNotFoundException("Please enter valid credential");
    }
}
