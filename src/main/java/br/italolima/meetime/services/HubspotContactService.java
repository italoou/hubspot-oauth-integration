package br.italolima.meetime.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.RateLimiter;

import br.italolima.meetime.dtos.ContactDTO;
import br.italolima.meetime.requests.HubspotClient;
import br.italolima.meetime.utils.Exceptions.RateLimitExcededException;

@Service
public class HubspotContactService {
		
	private HubspotClient hubspotClient;
	
	private HubspotOAuthService hubspotOAuthService;
	
	private final RateLimiter rateLimiter = RateLimiter.create(9.0);

	public HubspotContactService(HubspotClient hubspotClient, HubspotOAuthService hubspotOAuthService) {
		this.hubspotClient = hubspotClient;
		this.hubspotOAuthService = hubspotOAuthService;
	}
	
	public ResponseEntity<String> getContacts() {
		
		if(hubspotOAuthService.getTokenHandler().isAuthorized()) {
			
			String token = hubspotOAuthService.getAccessToken();
			
			return hubspotClient.getContactsOnHubspot("Bearer " + token);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access token not found.");
	}
	
	@Retryable(
			retryFor = RateLimitExcededException.class,
		    maxAttempts = 3, 
		    backoff = @Backoff(delay = 2000, multiplier = 2)
		)
	public ResponseEntity<String> createContacts(ContactDTO dto) {
        if (!hubspotOAuthService.getTokenHandler().isAuthorized()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access token not found.");
        }
    	 
        rateLimiter.acquire();
        
        String accessToken = hubspotOAuthService.getAccessToken();
        
        return hubspotClient.createContactsOnHubspot("Bearer " + accessToken, dto);
    }
	
}
