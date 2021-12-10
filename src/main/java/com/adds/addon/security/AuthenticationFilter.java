package com.adds.addon.security;

import com.adds.addon.security.util.RolesUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            com.adds.addon.entities.User user = new ObjectMapper().readValue(request.getInputStream(), com.adds.addon.entities.User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                    RolesUtil.getGrantedAuthorities(RolesUtil.getPrivilegies(user.getRoles()))));
        }
        catch (IOException ex){
            throw new RuntimeException("Could not read request");
        }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication){
        String user = ((User)authentication.getPrincipal()).getUsername();
        if(Jwts.parserBuilder().build().isSigned(request.getHeader("Authorization"))){
            response.setStatus(401);
            return;
        }
        String token = Jwts.builder().setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(Keys.keyPairFor(SignatureAlgorithm.HS512).getPrivate(),SignatureAlgorithm.HS512).compact();
        response.addHeader("Authorization", token);
    }
}
