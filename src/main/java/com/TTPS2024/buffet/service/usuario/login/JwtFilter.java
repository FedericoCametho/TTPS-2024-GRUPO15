package com.TTPS2024.buffet.service.usuario.login;

import com.TTPS2024.buffet.model.carta.DiaSemana;
import jakarta.servlet.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Component
public class JwtFilter implements Filter {

    private static final Set<String> AUTHORIZED_LOGIN_PATHS = new HashSet<>(List.of("/"));

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(this.isAuthorizedPath(httpRequest.getRequestURI()) || HttpMethod.OPTIONS.matches(httpRequest.getMethod())){
            chain.doFilter(request, response);
            return;
        }


        String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !TokenService.validateToken(authHeader)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthorizedPath(String path) {

        if (AUTHORIZED_LOGIN_PATHS.contains(path)) {
            return true;
        }
        if(path.startsWith("/login")){
            return true;
        }

        if (path.startsWith("/cartaDelDia/listarDia/")) {
            String dia = path.substring("/cartaDelDia/listarDia/".length());
            return List.of(DiaSemana.values()).contains(DiaSemana.valueOf(dia.toUpperCase()));
        }

        return false;
    }


}

