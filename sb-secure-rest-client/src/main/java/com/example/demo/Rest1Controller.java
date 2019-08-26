package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Rest1Controller {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${secure-rest-ip:secure-rest-server}")
	private String host;
	
	@Value("${secure-rest-port:8443}")
	private String port;
	
	@GetMapping("/https/{customerId}")
	public ResponseEntity<String> getHttps(@PathVariable(name="customerId") String custId) {
		
		String host = System.getenv().getOrDefault("secure-rest-ip", "localhost");
		String port = System.getenv().getOrDefault("secure-rest-port", "8443");
		System.out.println("Connecting to : " +host +" @ port: "+ port);
		try {
			ResponseEntity<String> str =  restTemplate.getForEntity("https://"+host+":"+port+"/customer/"+custId, String.class);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
