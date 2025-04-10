package br.italolima.meetime.dtos;

public record HubspotWebhookEventDTO(
		String eventId,
	    String objectId,
	    String propertyName,
	    String propertyValue,
	    Long occurredAt,
	    String changeSource,
	    String subscriptionId,
	    String portalId,
	    String appId,
	    String eventType,
	    Long attenptNumber) {

}
