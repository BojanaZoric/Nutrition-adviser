package sbnz.projekat.nutritionadviser.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import sbnz.projekat.nutritionadviser.security.auth.TokenBasedAuthentication;
import sbnz.projekat.nutritionadviser.service.UserDetailsServiceImpl;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		String username;
		String authToken = tokenUtils.getToken((HttpServletRequest)request);
		if (authToken != null) {
			// uzmi username iz tokena
			username = tokenUtils.getUsernameFromToken(authToken);
			if (username != null) {
				// uzmi user-a na osnovu username-a
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				// proveri da li je prosledjeni token validan
				if (tokenUtils.validateToken(authToken, userDetails)) {
					// kreiraj autentifikaciju
					TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
					authentication.setToken(authToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		chain.doFilter(request, response);
	}
}
