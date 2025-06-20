package com.bookflux;

import com.bookflux.config.TestMongoConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Felipe Neves
 * <p>
 * MongoDB respository test context. Extend this class when you need to use test mongo repositories
 */
@ActiveProfiles("test")
@SpringBootTest(classes = TestMongoConfig.class)
public abstract class MongoRepositoryTestContext {

}
