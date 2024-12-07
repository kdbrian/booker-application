package io.github.junrdev.booker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        //get auth headers
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //check token
        final String token = authHeader.substring(7);
        final String username = jwtService.extractUsername(token);
        final SecurityContext securityContext = SecurityContextHolder.getContext();

        //validate token
        if (username != null && securityContext.getAuthentication() == null) {

            //check user still in db by username
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //validate token against userdetails
            if (jwtService.isTokenValid(token, userDetails)) {

                //update security context
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        null,
                        userDetails.getAuthorities()
                );

                //chain request details to auth
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //set auth
                securityContext.setAuthentication(authToken);

            }

        }

        //call next filter
        filterChain.doFilter(request, response);
    }
}
