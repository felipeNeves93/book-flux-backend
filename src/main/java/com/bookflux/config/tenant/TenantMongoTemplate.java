package com.bookflux.config.tenant;

import com.bookflux.config.mongo.SharedEntity;
import java.util.Objects;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;


public class TenantMongoTemplate extends MongoTemplate {

  public TenantMongoTemplate(SimpleMongoClientDatabaseFactory factory) {
    super(factory);
  }

  @Override
  public String getCollectionName(Class<?> entityClass) {
    if (entityClass.isAnnotationPresent(SharedEntity.class)) {
      return super.getCollectionName(entityClass);
    }

    String tenantId = TenantContext.getTenantId();

    Objects.requireNonNull(tenantId, "Tenant ID is required!");

    return super.getCollectionName(entityClass) + "_" + tenantId.replace("@", "_");
  }

}
