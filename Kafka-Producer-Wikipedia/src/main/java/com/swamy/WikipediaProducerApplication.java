package com.swamy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.swamy.kafka.producer.WikimediaProducer;

@SpringBootApplication
public class WikipediaProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(WikipediaProducerApplication.class);
	}

	@Autowired
	private WikimediaProducer wikimediaProducer;

	@Override
	public void run(String... args) throws Exception {
		wikimediaProducer.sendMessage();
	}
}

