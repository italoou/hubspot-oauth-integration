package br.italolima.meetime.dtos;

public record HubspotAuthDTO(
		String grant_type, 
		String client_id, 
		String client_secret, 
		String redirect_uri, 
		String code) {
	
}
