package com.abhisek.api.vehicle.details.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtill jwtUtill;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authToken = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;
        if(authToken!=null && authToken.startsWith("Bearer ")){

            jwtToken = authToken.substring(7);
            if(jwtToken!=null && !jwtToken.isEmpty()){
                username = jwtUtill.extractUsername(jwtToken);
                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if(userDetails!=null && jwtUtill.validateToken(jwtToken,userDetails) &&
                            userDetails.isEnabled()){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                                = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }

                }
            }

        }
        filterChain.doFilter(request,response);

    }
}
