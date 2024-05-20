package com.riwi.ticketShowWeb.infraestructure.helpers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException
    {
        final String token = getTokenFromRequest(request);

        if (token == null) 
        {
            filterChain.doFilter(request, response);
            return;
        }

        String userName = this.jwtService.getUsernameFromToken(token);

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) 
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            if(this.jwtService.isTokenValid(token, userDetails))
            {
               var authToken = new UsernamePasswordAuthenticationToken(userName, null, userDetails.getAuthorities());
               authToken.setDetails(new WebAuthenticationDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    public String getTokenFromRequest(HttpServletRequest request)
    {
        final String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer "))
        {
            return authHeader.substring(7);
        }

        return authHeader;
    }
}
