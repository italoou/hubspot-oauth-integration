package br.italolima.meetime.dtos;

public record HubstopAuthResponseDTO(
		String token_type,
		String refresh_token,
		String access_token,
		Integer expires_in) {

}
