package com.example.demoproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(DemoProducerApplication.class, args);
	}
	@Autowired
	private Sender sender;

	@Override
	public void run(String... strings) throws Exception {
		int i = 0;
		while (true) {
			i += 1;
			sender.send("Spring Kafka Producer and Consumer Example: "+i);
			Thread.sleep(200);
		}
	}
}
