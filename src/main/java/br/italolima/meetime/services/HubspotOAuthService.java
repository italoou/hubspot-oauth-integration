package br.italolima.meetime.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.italolima.meetime.dtos.HubstopAuthResponseDTO;
import br.italolima.meetime.requests.HubspotClient;
import br.italolima.meetime.utils.Handlers.TokenHandler;

@Service
public class HubspotOAuthService {

	@Value("${hubspot.client-id}")
	private String clientId;
	
	@Value("${hubspot.client-secret}")
	private String clientSecret;
	
	@Value("${hubspot.scope}")
	private String scope;
	
	@Value("${hubspot.redirect-uri}")
	private String redirectUri;
	
	private TokenHandler tokenHandler;
	
	private HubspotClient hubspotClient;
	
	public HubspotOAuthService(HubspotClient hubspotClient, TokenHandler tokenHandler) {
		this.hubspotClient = hubspotClient;
		this.tokenHandler = tokenHandler;
	}
	
	public TokenHandler getTokenHandler(){
		return this.tokenHandler;
	}
	
	public String getOAuthRedirectUrl() {
		return "https://app.hubspot.com/oauth/authorize?client_id=" + clientId
				+"&scope=" + scope
				+"&redirect_uri=" + redirectUri;
	}
	
	public String getJwtToken(String code) {
		
		MultiValueMap<String, String> hubspotAuthForm = new LinkedMultiValueMap<>();
	    hubspotAuthForm.add("grant_type", "authorization_code");
	    hubspotAuthForm.add("client_id", this.clientId);
	    hubspotAuthForm.add("client_secret", this.clientSecret);
	    hubspotAuthForm.add("redirect_uri",this.redirectUri);
	    hubspotAuthForm.add("code", code);
		
		HubstopAuthResponseDTO responseDTO = null;

		responseDTO = obtainJwtToken(hubspotAuthForm);		
				
		return responseDTO.access_token();
	}
	
	
	public String getAccessToken() {
		
		String token = tokenHandler.getAccessToken();
		
		if(token == null && tokenHandler.getRefreshToken() != null) {
			token = refreshToken();
		}
		
		return token;
		
	}
	
	public String refreshToken() {
				
		MultiValueMap<String, String> hubspotAuthForm = new LinkedMultiValueMap<>();
	    hubspotAuthForm.add("grant_type", "refresh_token");
	    hubspotAuthForm.add("client_id", this.clientId);
	    hubspotAuthForm.add("client_secret", this.clientSecret);
	    hubspotAuthForm.add("redirect_uri",this.redirectUri);
	    hubspotAuthForm.add("refresh_token", tokenHandler.getRefreshToken());
	    	    
		HubstopAuthResponseDTO responseDTO = null;
		
		responseDTO = obtainJwtToken(hubspotAuthForm);
		
		return responseDTO.access_token();
	}
	
	public HubstopAuthResponseDTO obtainJwtToken(MultiValueMap<String, String> hubspotAuthForm) {
		
		HubstopAuthResponseDTO responseDTO = null;
		
		responseDTO = hubspotClient.getAuthTokenOnHubspot(hubspotAuthForm).getBody();

		this.tokenHandler.setTokens(responseDTO); 
		
		return responseDTO;
	}
	
	
	
}
