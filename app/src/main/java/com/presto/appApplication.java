package com.presto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by shihao on 16/10/23.
 */
@SpringBootApplication
public class appApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(appApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(appApplication.class, args);
    }

}
