package org.example.mock;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;

public class DynamicMockConfig extends ResourceConfig {

    private MockObjectManager mockObjectManager;

    private static final Logger logger = LoggerFactory.getLogger(DynamicMockConfig.class);

    public DynamicMockConfig() {
        super();
        register(DynamicMockService.class);
        mockObjectManager = new MockObjectManager();
    }

    protected void registerMock(Class<?> clazz) {
        logger.info("Registering mockable class {}", clazz.getSimpleName());
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Mockable.class)) {
                String handle = method.getAnnotation(Mockable.class).handle();
                String methodName = method.getName();
                String className = method.getDeclaringClass().getCanonicalName();
                Class<?> returnType = method.getReturnType();
                logger.info("Found mockable method {}.{} returning {}", className, methodName, returnType.getCanonicalName());
                mockObjectManager.recordMockType(handle, returnType);
            }
        }
    }

    @Bean
    public MockObjectManager getMockObjectManager() {
        return mockObjectManager;
    }

}