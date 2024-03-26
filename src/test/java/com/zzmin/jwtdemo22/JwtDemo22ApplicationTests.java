package com.zzmin.jwtdemo22;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class JwtDemo22ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	 void encStr() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String plainPwd="adminPWD";
		plainPwd="userPWD";
		String pass1 = passwordEncoder.encode(plainPwd);
		String pass2 = passwordEncoder.encode(plainPwd);

		System.out.println("pass1 = " + pass1);
		System.out.println("pass2 = " + pass2);

		System.out.println(passwordEncoder.matches(plainPwd,pass1));
		System.out.println(passwordEncoder.matches(plainPwd,pass2));
		System.out.println(passwordEncoder.matches(pass1,pass2));

	}

}
