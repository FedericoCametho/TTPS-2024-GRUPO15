package com.TTPS2024.buffet.service.usuario.login;

import jakarta.servlet.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JwtFilter implements Filter {

    private final String SECRET_KEY = "secret";
    private static final Set<String> AUTHORIZED_LOGIN_PATHS = new HashSet<>(List.of("/","/login/alumno", "/login/administrador", "/login/responsableDeTurno"));

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(AUTHORIZED_LOGIN_PATHS.contains(httpRequest.getRequestURI()) || HttpMethod.OPTIONS.matches(httpRequest.getMethod())){
            chain.doFilter(request, response);
            return;
        }


        String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || TokenService.validateToken(authHeader)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }


}

