package com.example.app;


import com.example.app.config.DomainConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@TestConfiguration
@EnableWebMvc
@EnableAutoConfiguration
@Import(DomainConfiguration.class)
public class ITConfiguration {

}
