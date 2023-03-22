package com.cafe.jwt;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {



    @Autowired
   private  JwtUtil jwtUtil;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    private String userName =null;

    private Claims claims;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {




        if(request.getServletPath().matches("/user/signup|/user/token|/user/login|/user/forgetPassword|/user/otp|/user/optVerify"))
        {
            filterChain.doFilter(request,response);
        }

        else {


            String authorizationHeader = request.getHeader("Authorization");
            String token =null;


            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
            {
                token = authorizationHeader.replace("Bearer ", "");
                userName =  jwtUtil.getUsername(token);
                claims = jwtUtil.getClaims(token);

                log.info("Token inside doFilterInternal  "+token);


            }

            if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
               log.info("Inside jwt filter and loadFliterInternal method");
                UserDetails userDetails = customerUserDetailsService.loadUserByUsername(userName);

                if(jwtUtil.isValidToken(token,userDetails.getUsername()) )
                {

                    log.info("inside IsvalidToken");
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails ,null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    log.info("SecurityContextHolder build");
                }


            }


            filterChain.doFilter(request,response);

        }










    }

    public boolean isAdmin()
    {
        return
                "ADMIN".equalsIgnoreCase((String) claims.get("role"));
    }

    public boolean isUser()
    {
        return
                "User".equalsIgnoreCase((String) claims.get("role"));
    }

    public  String getCurrentUser()
    {
        return  userName;
    }

}
