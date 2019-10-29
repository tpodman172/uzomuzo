package com.tpodman172.uzomuzo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UzomuzoApplication {

//	@RequestMapping
//	String index(){
//		return "Hello World";
//	}

	public static void main(String[] args) {
		SpringApplication.run(UzomuzoApplication.class, args);
	}

}
