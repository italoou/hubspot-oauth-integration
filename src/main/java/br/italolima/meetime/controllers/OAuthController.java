package br.italolima.meetime.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class OAuthController {

	@Value("${hubspot.client-id}")
	private String clientId;
	
	@Value("${hubspot.scope}")
	private String scope;
	
	@Value("${hubspot.redirect-uri}")
	private String redirectUri;
	
	@GetMapping("/login")
	public void Authenticate(HttpServletResponse response) throws IOException{
		response.sendRedirect("https://app.hubspot.com/oauth/authorize?client_id=" + clientId
																	+"&scope=" + scope
																	+"&redirect_uri=" + redirectUri);
	}
	
	@GetMapping("/redirect-url")
	public ResponseEntity<String> redirectUrl(HttpServletRequest request){
		
		System.out.println();
		
		return ResponseEntity.ok("ok");
	}
}
