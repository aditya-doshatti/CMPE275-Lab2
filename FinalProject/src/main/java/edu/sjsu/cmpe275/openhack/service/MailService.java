package edu.sjsu.cmpe275.openhack.service;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class MailService {
	private String API_KEY="75d0c69acf2971377e954132becfa00d-e566273b-48e6c5f3";
	private String DOMAIN_NAME="sandbox90131ed62bfc412bade756f1deb3549b.mailgun.org";
	
	public JsonNode sendEmail(String toEmail, String msg) throws UnirestException {	
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + DOMAIN_NAME + "/messages")
            .basicAuth("api", API_KEY)
            .field("from", "pratik.bhandarkar@sjsu.edu")
            .field("to", toEmail)
            .field("subject", "hello")
            .field("text", msg)
            .asJson();

        return request.getBody();
    }
}
