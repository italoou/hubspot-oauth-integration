package br.italolima.meetime.controllers;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.italolima.meetime.services.HubspotOAuthService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class HubspotOAuthController {
	
	private HubspotOAuthService service;
	
	public HubspotOAuthController(HubspotOAuthService hubspotService) {
		this.service = hubspotService;
	}
	
	@GetMapping("/authorize")
	public void authorize(HttpServletResponse response) throws IOException{
		response.sendRedirect(service.getOAuthRedirectUrl());
	}
	
	@GetMapping("/authorize/url")
	public ResponseEntity<String> getAuthorizationUrl() {
	    return ResponseEntity.ok(service.getOAuthRedirectUrl());
	}
	
	@GetMapping("/callback")
	public ResponseEntity<String> callback(HttpServletResponse response, @RequestParam String code){
        return ResponseEntity.ok().body(service.getJwtToken(code));
	}
	
	@GetMapping("/error")
	public ResponseEntity<String> error(@RequestParam String msg){
		
		return ResponseEntity.badRequest().body(msg);

	}
}
