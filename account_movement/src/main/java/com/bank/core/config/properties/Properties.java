package com.bank.core.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@PropertySource(value = "classpath:application.properties")
public class Properties {

    @Value("${client.endpoint.endpoint}")
    private String clientUrl;


}
