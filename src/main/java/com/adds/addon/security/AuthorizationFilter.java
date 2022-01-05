package com.adds.addon.security;

import com.adds.addon.component.RolesUtil;
import com.adds.addon.entities.User;
import com.adds.addon.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private RolesUtil rolesUtil;
    private UserRepo userRepo;

    public AuthorizationFilter(AuthenticationManager authenticationManager, RolesUtil rolesUtil, UserRepo userRepo) {
        super(authenticationManager);
        this.rolesUtil = rolesUtil;
        this.userRepo = userRepo;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if(header == null){
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws SignatureException
    {
        String token = request.getHeader("Authorization");
        if(token !=null){
            String user = Jwts.parserBuilder().setSigningKey(RolesUtil.KEY).build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            User user1 = userRepo.findByUsername(user).get();
            if (user != null){
                return new UsernamePasswordAuthenticationToken(user, null, rolesUtil.getGrantedAuthorities(rolesUtil.getPrivilegies(user1.getRoles())));
            }
            return null;
        }
        return null;
    }
}
