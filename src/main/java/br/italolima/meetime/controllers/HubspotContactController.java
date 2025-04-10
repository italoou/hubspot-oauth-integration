package br.italolima.meetime.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.italolima.meetime.dtos.ContactDTO;
import br.italolima.meetime.dtos.HubspotWebhookEventDTO;
import br.italolima.meetime.services.HubspotContactService;

@RestController
@RequestMapping("/contact")
public class HubspotContactController {
	
	private HubspotContactService service;
	
	public HubspotContactController(HubspotContactService hubspotContactService) {
		this.service = hubspotContactService;
	}
	
	@GetMapping("")
	public ResponseEntity<String> getContacts(){
		
		return ResponseEntity.ok().body(service.getContacts().getBody());

	}
	
	@PostMapping("")
	public ResponseEntity<String> createContact(@RequestBody ContactDTO dto){
		
	        ResponseEntity<String> response = service.createContacts(dto);
	        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

    }
	
	@PostMapping("/webhook")
	public ResponseEntity<String> webhookContactCreation(@RequestBody List<HubspotWebhookEventDTO> eventList){
		
		List<HubspotWebhookEventDTO> contactCreatedList = eventList.stream()
				.filter(e -> "contact.creation".equalsIgnoreCase(e.eventType()))
				.collect(Collectors.toList());
				
		
		contactCreatedList.forEach(c -> {
			System.out.println("New contact.creation event received: " + c.eventId());
		});
		
		return ResponseEntity.ok().body("Information processed successfully");
	}
	
	@GetMapping("/error")
	public ResponseEntity<String> error(@RequestParam String msg){
		
		return ResponseEntity.badRequest().body(msg);

	}
}
