package com.swamy.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.swamy.entity.WikimediaData;
import com.swamy.repository.WikimediaRepository;

@Service
public class KafkaDatabaseConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	@Autowired
	private WikimediaRepository wikimediaRepository;
	
	@KafkaListener(topics = "wikimedia_kafka" , groupId = "myGroup")
	public void consume(String eventMessage) {
		LOG.info(String.format("Event Message Received -> %s" , eventMessage));
		
		WikimediaData wikimediaData = new WikimediaData();
		wikimediaData.setWikiEventData(eventMessage);
		
		wikimediaRepository.save(wikimediaData);
	}
}

