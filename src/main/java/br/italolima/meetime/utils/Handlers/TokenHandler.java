package br.italolima.meetime.utils.Handlers;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import br.italolima.meetime.dtos.HubstopAuthResponseDTO;

@Component
public class TokenHandler {	
	
    private String refreshToken;
    private final Cache<String, String> accessTokenCache = Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();
    
    private final String ACCESS_TOKEN_KEY = "hubspot-access-token";
    
    public void setTokens(HubstopAuthResponseDTO dto) {
        this.refreshToken = dto.refresh_token();
        accessTokenCache.put(ACCESS_TOKEN_KEY, dto.access_token());
    }
    
    public String getAccessToken() {
        return accessTokenCache.getIfPresent(ACCESS_TOKEN_KEY);
    }
    
    public String getRefreshToken() { 
        return refreshToken;
    }
    
    public boolean isAuthorized() {
    	return getAccessToken() != null;   
	}
    
}
