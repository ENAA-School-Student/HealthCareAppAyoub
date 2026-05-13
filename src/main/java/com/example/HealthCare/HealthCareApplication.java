package com.example.HealthCare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})

<<<<<<< HEAD
@SpringBootApplication
public class     HealthCareApplication {

=======
public class    HealthCareApplication {
>>>>>>> AuthenticationBranch
	public static void main(String[] args) {
		SpringApplication.run(HealthCareApplication.class, args);
	}


}
