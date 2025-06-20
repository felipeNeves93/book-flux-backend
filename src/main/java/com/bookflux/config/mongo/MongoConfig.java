package com.bookflux.config.mongo;

import com.bookflux.config.tenant.TenantMongoTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.bookflux.repository", mongoTemplateRef = "tenantMongoTemplate")
@Profile("!test")
public class MongoConfig {

  @Value("${spring.data.mongodb.uri}")
  private String mongoDBUri;

  @Bean(name = "tenantMongoTemplate")
  public MongoTemplate mongoTemplate() {
    return new TenantMongoTemplate(new SimpleMongoClientDatabaseFactory(mongoDBUri));
  }

}
