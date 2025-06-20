package com.bookflux.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.commands.ServerAddress;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import de.flapdoodle.embed.mongo.transitions.RunningMongodProcess;
import de.flapdoodle.reverse.TransitionWalker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.bookflux.repository")
@Profile("test")
public class TestMongoConfig {

  @Bean
  public TransitionWalker.ReachedState<RunningMongodProcess> mongodProcess() {
    return Mongod.builder().build().start(Version.Main.V7_0);
  }

  @Bean
  public MongoTemplate mongoTemplate(
      TransitionWalker.ReachedState<RunningMongodProcess> mongodProcess) {
    ServerAddress serverAddress = mongodProcess.current().getServerAddress();
    MongoClient mongoClient = MongoClients.create(
        "mongodb://" + serverAddress.getHost() + ":" + serverAddress.getPort());
    return new MongoTemplate(mongoClient, "bookFlux");
  }
}
