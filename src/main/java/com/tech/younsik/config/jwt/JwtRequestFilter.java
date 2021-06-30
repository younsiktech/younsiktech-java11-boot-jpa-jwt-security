package com.tech.younsik.config.jwt;

import com.tech.younsik.constant.AuthConst;
import com.tech.younsik.exception.AuthException;
import com.tech.younsik.exception.AuthException.Type;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtAuthProvider jwtAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(AuthConst.AUTHORIZATION_HEADER);

        String subject = null;

        String jwtToken =null;

        // JWT 토큰은 "Beare token"에 있다. Bearer단어를 제거하고 토큰만 받는다.
        if(requestTokenHeader != null && requestTokenHeader.startsWith(AuthConst.AUTHORIZATION_TYPE)){
            jwtToken = requestTokenHeader.substring(AuthConst.AUTHORIZATION_TYPE.length()).trim();

            try{
                subject = jwtAuthProvider.getSubjectFromToken(jwtToken);
            } catch (IllegalArgumentException ex){
                log.error(ex.getMessage());
                throw new AuthException("Unable to get token", Type.EMPTY_AUTH);
            } catch (ExpiredJwtException ex){
                log.error(ex.getMessage());
                throw new AuthException("Token has expired", Type.EXPIRED_AUTH);
            } catch (JwtException ex) {
                log.error(ex.getMessage());
                throw new AuthException("Token valid error", Type.INVALID_AUTH);
            } catch (Exception ex) {
                log.error(ex.getMessage());
                throw new AuthException("Token error", Type.UNDEFINED_AUTH_ERROR);
            }
        }else{
            log.warn("JWT token does not begin with Bearer String");
        }

        if(subject != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(subject);
            if(jwtAuthProvider.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );
                usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
