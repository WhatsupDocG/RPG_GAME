package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.core.location.Location;

@Component
public class LocationProducer {

    private final KafkaTemplate<String, Location> kafkaTemplate;
    private final String locationTopic = "location-topic";

    @Autowired
    public LocationProducer(KafkaTemplate<String, Location> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLocationInfo(Location locationInfo) {
        kafkaTemplate.send(locationTopic, locationInfo);
    }
}