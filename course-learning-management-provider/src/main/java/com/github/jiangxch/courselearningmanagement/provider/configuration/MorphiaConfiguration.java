package com.github.jiangxch.courselearningmanagement.provider.configuration;

import com.google.common.collect.Lists;
import com.mongodb.*;
import com.mongodb.client.ListDatabasesIterable;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

/**
 * @author: sanjin
 * @date: 2020/2/25 下午5:44
 */
@Configuration
public class MorphiaConfiguration implements EnvironmentAware {
    @Value("${mongo.mongoDbName}")
    private String mongoDbName;
    @Value("${mongo.host}")
    private String host;
    @Value("${mongo.port}")
    private Integer port;
    @Value("${mongo.username}")
    private String username;
    @Value("${mongo.password}")
    private String password;

    @Bean
    public MongoClient mongoClient() {
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(
                username,
                mongoDbName,
                password.trim().toCharArray());

        MongoClient mongoClient;
        mongoClient = new MongoClient(
                Lists.newArrayList(new ServerAddress(host, port)),
                mongoCredential,
                new MongoClientOptions.Builder().build());
        ListDatabasesIterable<Document> documents = mongoClient.listDatabases();
        return mongoClient;
    }

    @Bean
    public Datastore datastore(@Autowired MongoClient mongoClient) throws ClassNotFoundException {
        Morphia morphia = new Morphia();
        Datastore datastore = morphia.createDatastore(mongoClient, mongoDbName);
        return datastore;
    }

    @Override
    public void setEnvironment(Environment environment) {
        String property = environment.getProperty("mongo.host");
        String port = environment.getProperty("mongo.port");
    }
}
