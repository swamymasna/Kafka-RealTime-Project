package com.swamy.kafka.producer;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.swamy.handler.WikiMediaChangesHandler;

@Service
public class WikimediaProducer {

	@Autowired
	private KafkaTemplate<String, String>kafkaTemplate;
	
	public void sendMessage() throws Exception {
		String topic = "wikimedia_kafka";
		
		//To read real time stream data from wikimedia, we use event source
		EventHandler eventHandler = new WikiMediaChangesHandler(kafkaTemplate, topic);
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
		EventSource eventSource = builder.build();
		eventSource.start();
		
		TimeUnit.MINUTES.sleep(10);
		
	}
}
