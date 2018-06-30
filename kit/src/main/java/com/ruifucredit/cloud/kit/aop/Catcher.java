package com.ruifucredit.cloud.kit.aop;

import com.ruifucredit.cloud.kit.dto.Outgoing;
import com.ruifucredit.cloud.kit.exception.ExceptionKit;
import com.ruifucredit.cloud.kit.json.GsonKit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1001)
@Slf4j
public class Catcher {

    @Around("execution(com.ruifucredit.cloud.kit.dto.Outgoing com.ruifucredit..* (..)) && (@annotation(com.ruifucredit.cloud.kit.aop.Catch) || @target(com.ruifucredit.cloud.kit.aop.Catch))")
    public Outgoing<?> throwing(ProceedingJoinPoint joinPoint) {

        try {
            return (Outgoing<?>) joinPoint.proceed();
        } catch (Throwable t) {

            String info = String.format("Catching Exception: %s", t.toString());

            log.info(info);

            Outgoing<?> outgoing = ExceptionKit.badGoing(t);

            info += String.format(", Outgoing: %s", GsonKit.toJson(outgoing));

            log.info(info);

            return outgoing;
        }

    }

}
