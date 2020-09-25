package com.lambdaschool.secretfamilyrecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableJpaAuditing
@SpringBootApplication
@EnableWebMvc
public class SecretfamilyrecipeApplication {

    @Autowired
    private static Environment env;

    private static boolean stop = false;

    private static void checkEnvironmentVariable(String envvar)
    {
        if (System.getenv(envvar) == null)
        {
            stop = true;
        }
    }

    public static void main(String[] args) {

        checkEnvironmentVariable("OAUTHCLIENTID");
        checkEnvironmentVariable("OAUTHCLIENTSECRET");

        if (!stop)
        SpringApplication.run(SecretfamilyrecipeApplication.class, args);
    }

}
