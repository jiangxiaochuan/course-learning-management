package com.github.jiangxch.courselearningmanagement.provider.configuration;

import com.google.common.collect.Lists;
import com.mongodb.*;
import com.mongodb.client.ListDatabasesIterable;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author: sanjin
 * @date: 2020/2/25 下午5:44
 */
@Configuration
public class MorphiaConfiguration {

    private String mongoDbName = "course_learning_management";
    private String host = "127.0.0.1";
    private Integer port = 27017;
    private String username = "admin";
    private String password = "123456789";

    // test是数据库名称
    private String uri = "mongodb://username:password@127.0.0.1:27017/test";


    @Bean
    public MongoClient mongoClient() {
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(
                username,
                mongoDbName,
                password.toCharArray());

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
}
