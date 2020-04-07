package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.HealthShopUserDetails;
import com.example.demo.model.authentication.AuthenticationRequest;
import com.example.demo.repositories.CustomerRepo;
import com.example.demo.services.CustomerService;
import com.example.demo.services.HealthShopUserDetailsService;

@RestController("/")
public class HomeController {
	
	@Autowired
	CustomerService mCustomerService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	HealthShopUserDetailsService healthShopUserDetailsService;
	
	
	///APIs
	
	@GetMapping
	public String homeTest() {
		return "Home controller reached";
	}
	
	@GetMapping("/dos")
	public String testDos() {
		return "reached dos";
	}
	
	@PostMapping("/newcust")
	public String testcreateNewCustomer() {
		Customer cust = new Customer("jj@email", "password", "21 whitehouse avenue", "12345678");
		return mCustomerService.createNewCustomerEntity(cust);
		 
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
				);
		} catch (BadCredentialsException e){
			throw new Exception("Incorrect email or password", e);
		}
		
		final HealthShopUserDetails userDetails = HealthShopUserDetailsService.
		return null;
		
	}

}
