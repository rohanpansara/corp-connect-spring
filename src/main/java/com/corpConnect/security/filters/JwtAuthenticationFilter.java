package com.corpConnect.security.filters;

import com.corpConnect.entities.user.User;
import com.corpConnect.security.CorpConnectUserContext;
import com.corpConnect.security.services.JwtService;
import com.corpConnect.utils.constants.MessageConstants;
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
            "/user/login",
            "/user/new-user"
    };

    private static final String BEARER_PREFIX = "Bearer ";

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
            final String jwtToken;
            final String userEmail;
            if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
                filterChain.doFilter(request, response);
                throw new RuntimeException("Authorization token missing");
            }
            jwtToken = authHeader.substring(BEARER_PREFIX.length());
            userEmail = jwtService.extractEmail(jwtToken);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    CorpConnectUserContext.setCurrentUser((User) userDetails);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e){
            throw new RuntimeException(MessageConstants.UserError.AUTHORIZATION_FAILED);
        } finally {
            CorpConnectUserContext.clear();
        }
    }
}
