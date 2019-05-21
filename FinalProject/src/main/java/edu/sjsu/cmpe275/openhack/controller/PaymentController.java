package edu.sjsu.cmpe275.openhack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.sjsu.cmpe275.openhack.model.PaymentDetails;
import edu.sjsu.cmpe275.openhack.service.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;

	@RequestMapping(method=RequestMethod.GET,value = "/payments/{teamId}", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<PaymentDetails>> getPaymentDetails(@PathVariable Long teamId) {
		List<PaymentDetails> details = paymentService.getPayments(teamId);
		return ResponseEntity.ok(details);
	}
}
