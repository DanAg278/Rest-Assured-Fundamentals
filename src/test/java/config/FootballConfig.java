package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FootballConfig {
    private static final Logger logger = Logger.getLogger(FootballConfig.class.getName());


    @BeforeClass
    public static void setup() {
        Properties prop = new Properties();
        String authToken = null;
        try (InputStream configFile = new FileInputStream("src/test/config.properties")) {
            prop.load(configFile);
            authToken = prop.getProperty("football.api.token");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "config.properties file not found", e);
        }
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.football-data.org")
                .setBasePath("/v4")
                .addHeader("X-Auth-Token", authToken)
                .addHeader("X-Response-Control", "minified")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

}
