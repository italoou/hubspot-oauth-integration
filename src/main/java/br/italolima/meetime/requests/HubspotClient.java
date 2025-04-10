package br.italolima.meetime.requests;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.italolima.meetime.dtos.ContactDTO;
import br.italolima.meetime.dtos.HubstopAuthResponseDTO;


@FeignClient(name = "hubspot-client", url = "${hubspot.api-url}")
public interface HubspotClient {

	@PostMapping(value= "/oauth/v1/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseEntity<HubstopAuthResponseDTO> getAuthTokenOnHubspot(
			@RequestBody MultiValueMap<String, String> hubspotAuthForm);
	
	@GetMapping(value= "/crm/v3/objects/contacts/")
	ResponseEntity<String> getContactsOnHubspot(
			@RequestHeader("Authorization") String accessToken);
	
	@PostMapping(value= "/crm/v3/objects/contacts")
	ResponseEntity<String> createContactsOnHubspot(
			@RequestHeader("Authorization") String accessToken, @RequestBody ContactDTO contactDTO);
}
