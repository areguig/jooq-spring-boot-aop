package org.jooq.example.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by areguig on 19/02/2017.
 */
@Component
@Aspect
public class TestAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @AfterReturning("execution(* org.jooq.example.spring.service..*.*(..))")
    public void logAround(JoinPoint joinPoint) throws Throwable {
        log.debug("> Enter: {} with argument[s] = {}", joinPoint.getSignature().toLongString(),
                Arrays.toString(joinPoint.getArgs()));
    }
}
