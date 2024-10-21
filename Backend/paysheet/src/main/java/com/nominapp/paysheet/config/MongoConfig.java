package com.nominapp.paysheet.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        mongoTemplate.getDb().listCollectionNames().forEach(collection ->
                mongoTemplate.dropCollection(collection)
        );
    }
}