package org.example.config;

import org.example.mock.DynamicMockConfig;
import org.example.resource.DoggoResource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends DynamicMockConfig {
    public JerseyConfig() {
        super();
        register(DoggoResource.class);
        registerMock(DoggoResource.class);
    }
}