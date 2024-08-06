package com.shl.demo.design;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "suggest.requirement")
public class HandleConfig {

    /**
     * handler1
     */
    private List<String> handler1;

    /**
     * handler2
     */
    private List<String> handler2;
}
