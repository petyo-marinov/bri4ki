package bri4ka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Bri4kaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bri4kaApplication.class, args);
	}
}
