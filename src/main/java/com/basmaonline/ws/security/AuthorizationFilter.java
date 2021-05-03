package com.basmaonline.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoderJwkSupport;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.Base64Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;


public class AuthorizationFilter extends BasicAuthenticationFilter{

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(SecurityConstants.HEADER_STRING);
		if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);

	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
	String token = req.getHeader(SecurityConstants.HEADER_STRING);
	
if (token != null) {
	token = token.replace(SecurityConstants.TOKEN_PREFIX, " ");
	
	String user = Jwts.parser()
			.setSigningKey(SecurityConstants.TOKEN_SECRET)
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	
	if (user != null) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET)).build();
        String jwt = token.substring(SecurityConstants.TOKEN_PREFIX.length());

        DecodedJWT jwt1 = verifier.verify(jwt);
       String role =  jwt1.getClaim("role").asString();
        
		 Collection<GrantedAuthority> authorities = new ArrayList<>();
		 authorities.add(
				 new SimpleGrantedAuthority(role)
				 );
		return new UsernamePasswordAuthenticationToken(user, null , authorities);
	}
	return null;
}
return null;
	}

}
