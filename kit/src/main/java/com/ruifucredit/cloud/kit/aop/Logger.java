package com.ruifucredit.cloud.kit.aop;

import com.ruifucredit.cloud.kit.json.GsonKit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Aspect
@Order(1001)
@Slf4j
public class Logger {

    @Around(value="execution(* com.ruifucredit..* (..)) && (@annotation(com.ruifucredit.cloud.kit.aop.Log) || @target(com.ruifucredit.cloud.kit.aop.Log))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Instant t1 = Instant.now();

        String info = String.format("Method Signature: %s, Invoke Arguments: %s, Start Time: %s", joinPoint.getSignature().toLongString(), GsonKit.toJson(joinPoint.getArgs()), t1);

        log.info(info);

        Object result = null;

        try {
            return (result = joinPoint.proceed());
        } catch (Throwable e) {
            info += String.format(", Method Exception: %s", e.toString());
            log.error(info, e);
            throw e;
        } finally {
            Instant t2 = Instant.now();
            info += String.format(", Invoke Result: %s, Finish Time: %s, Duration Millis: %d", GsonKit.toJson(result), t2, Duration.between(t1, t2).toMillis());
            log.info(info);
        }

    }


}
