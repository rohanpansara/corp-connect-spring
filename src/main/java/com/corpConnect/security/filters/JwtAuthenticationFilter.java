package com.corpConnect.security.filters;

import com.corpConnect.entities.users.Users;
import com.corpConnect.security.EssUserContext;
import com.corpConnect.security.services.JwtService;
import com.corpConnect.utils.constants.CorpConnectConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String[] PUBLIC_URLS = {
            "/user/login"
    };

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            // Check if the request is for a public URL
            String servletPath = request.getServletPath();
            for (String url : PUBLIC_URLS) {
                if (servletPath.equals(url)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String userEmail;
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                throw new RuntimeException("Authorization token missing");
            }
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractEmail(jwt);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    EssUserContext.setCurrentUser((Users) userDetails);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e){
            throw new RuntimeException(CorpConnectConstants.UserError.AUTHORIZATION_FAILED);
        } finally {
            EssUserContext.clear();
        }
    }
}