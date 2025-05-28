package com.example.kurs.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Log request details for debugging
            log.info("Processing request: {} {}", request.getMethod(), request.getRequestURI());
            log.info("Authorization header: {}", request.getHeader("Authorization"));

            String token = getTokenFromRequest(request);

            if (token != null) {
                log.info("JWT token found in request");
                try {
                    if (jwtTokenProvider.validateToken(token)) {
                        String username = jwtTokenProvider.getUsername(token);
                        log.info("Token valid for user: {}", username);

                        SecurityContextHolder.getContext().setAuthentication(
                                new PreAuthenticatedAuthenticationToken(username, null, null));
                    } else {
                        log.warn("Invalid JWT token");
                    }
                } catch (Exception e) {
                    log.error("Error validating JWT token", e);
                    // Don't set authentication
                }
            } else {
                log.info("No JWT token found in request");
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Unexpected error in JWT filter", e);
            filterChain.doFilter(request, response);
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        // Don't apply filter to auth endpoints or OPTIONS requests (for CORS preflight)
        return path.startsWith("/api/auth/") || request.getMethod().equals("OPTIONS");
    }
}

