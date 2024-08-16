package com.example.Taskmanagement.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class AddUuidFilter extends OncePerRequestFilter {
    private static final String REQUEST_ID = "RequestID";

    public AddUuidFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {
        try {
            String uuid = UUID.randomUUID().toString().substring(0, 5);
            MDC.put("RequestID", uuid);
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove("RequestID");
        }

    }
}
