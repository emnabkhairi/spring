package com.example.SysEve;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.SysEve.business.services.UserService;


@SpringBootApplication
public class SysEveApplication {

	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(SysEveApplication.class, args);
	}

/* @PostConstruct
    public void init() {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        userService.saveUser(new User(null, "user", "user", "user@gmail.com", roles));
    }  */

}
