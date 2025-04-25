package com.bookflux;

import com.bookflux.config.TestMongoConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = TestMongoConfig.class)
public abstract class MongoRepositoryTestContext {

}
