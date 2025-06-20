package com.bookflux;

import com.bookflux.config.TestMongoConfig;
import com.bookflux.config.TestOauth2Config;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

/**
 * @author Felipe Neves
 * <p>
 * Spring Boot test context excluding mongo and oauth2 autoconfiguration, and containing test
 * properties to generate tokens and test google api url with mocked value
 */
@ActiveProfiles("test")
@SpringBootTest(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,"
        +
        "org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration"
})
@Import({TestMongoConfig.class, TestOauth2Config.class})
@TestPropertySource(properties = {
    "application.security.jwt.secret-key=GHwSkQ5UYlV30rLX6Fuw3ILNxxTHQkm0FY4BGOpbBoDi4DLcg8dACvSZB+w/gJsbaMvTvdd/u3pG8CThArAGGQ==",
    "google.books.api.url=test"})
@AutoConfigureMockMvc
public class WebMvcTestContext {

}
