package org.example.mock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MockingAspect {

    @Autowired
    private MockObjectManager mockObjectManager;

    private static final Logger logger = LoggerFactory.getLogger(MockingAspect.class);

    @Around("@annotation(org.example.mock.Mockable)")
    public Object skip(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("Entering mockable method");
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String handle = methodSignature.getMethod().getAnnotation(Mockable.class).handle();
        logger.info("Method handle was {}", handle);
        Object mock = mockObjectManager.lookupMock(handle);
        if (mock != null) {
            logger.info("Returning mock response");
            return mock;
        } else {
            logger.info("No mock response set; proceeding with method invocation");
            return pjp.proceed();
        }

    }

}
