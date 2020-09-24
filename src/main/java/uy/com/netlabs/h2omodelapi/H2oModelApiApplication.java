package uy.com.netlabs.h2omodelapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class H2oModelApiApplication {

	public static void main(String[] args) {
		String path = args[0];
		ModelExecutor.getInstance().init(path);

		SpringApplication.run(H2oModelApiApplication.class, args);
	}

}
