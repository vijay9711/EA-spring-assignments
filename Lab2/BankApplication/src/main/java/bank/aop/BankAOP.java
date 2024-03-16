package bank.aop;

import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class BankAOP {
    private final Logger logger;
    public BankAOP(Logger logger) {
        this.logger = logger;
    }
    @Before("execution(* bank.repository..*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        logger.log("Method Call: " + joinPoint.getSignature());
    }
    @Around("execution(* bank.service..*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        logger.log("Method Execution Time: " + joinPoint.getSignature() + " - " + stopWatch.getTotalTimeMillis() + "ms");
        return result;
    }
//    @After("execution(* bank.integration.jms.JMSSenderImpl.*(..))")
//    public void logJMSMessage(Object result) {
//        if (result instanceof JMSSender jmsSender) {
//            logger.log("Log JMS Message Sent" + jmsSender);
//        }
//    }

    @AfterThrowing(pointcut = "execution(* bank.service..*(..))", throwing = "exception")
    public void logExceptionInService(JoinPoint joinPoint, Exception exception) {
        logger.log("Exception in " + joinPoint.getSignature() + ": " + exception.getMessage());
    }
}
