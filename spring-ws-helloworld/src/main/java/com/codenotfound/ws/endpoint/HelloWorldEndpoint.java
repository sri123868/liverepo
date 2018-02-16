package com.codenotfound.ws.endpoint;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.codenotfound.types.helloworld.CGCGiftCardBalanceRequest;
import com.codenotfound.types.helloworld.CGCGiftCardBalanceResponse;
import com.codenotfound.types.helloworld.Greeting;
import com.codenotfound.types.helloworld.ObjectFactory;
import com.codenotfound.types.helloworld.Person;

@Endpoint
public class HelloWorldEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldEndpoint.class);

	@PayloadRoot(namespace = "http://codenotfound.com/types/helloworld", localPart = "person")
	@ResponsePayload
	public Greeting sayHello(@RequestPayload Person request) {
		LOGGER.info("Endpoint received person[firstName={},lastName={}]", request.getFirstName(),
				request.getLastName());

		String greeting = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";

		ObjectFactory factory = new ObjectFactory();
		Greeting response = factory.createGreeting();
		response.setGreeting(greeting);

		LOGGER.info("Endpoint sending greeting='{}'", response.getGreeting());
		return response;
	}

	@PayloadRoot(namespace = "http://codenotfound.com/types/helloworld", localPart = "CGCGiftCardBalanceRequest")
	@ResponsePayload
	public CGCGiftCardBalanceResponse balanceInquiry(@RequestPayload CGCGiftCardBalanceRequest request) {
		LOGGER.info("ENDPOINT received giftcard balance request, CGCGiftCardBalanceRequest[{}]", request);

		CGCGiftCardBalanceResponse response = new ObjectFactory().createCGCGiftCardBalanceResponse();
		response.setApproved(true);
		response.setAuthorizationNumber("12152367");
		response.setBalanceAmount(new BigDecimal(123.45));
		response.setBankReferenceNumber("BK!%$13");
		response.setErrorCode(null);

		LOGGER.info("Endpoint returning the response CGCGiftCardResponse[{}]", response);

		return response;
	}
}
