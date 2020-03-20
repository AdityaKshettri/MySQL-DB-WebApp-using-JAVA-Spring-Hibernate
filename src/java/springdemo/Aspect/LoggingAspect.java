package springdemo.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect 
{
    private Logger myLogger = Logger.getLogger(getClass().getName());
    
    @Pointcut("execution(* springdemo.Controller.*.*(..))")
    private void forControllerPackage()
    {}
    
    @Pointcut("execution(* springdemo.Service.*.*(..))")
    private void forServicePackage()
    {}
    
    @Pointcut("execution(* springdemo.DAO.*.*(..))")
    private void forDAOPackage()
    {}
    
    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow()
    {}
    
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint)
    {
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("===> in @Before: calling method: " + theMethod);
        
        Object[] args = theJoinPoint.getArgs();
        
        for(Object temp: args)
        {
            myLogger.info("===> argument : " + temp);
        }
    }
    
    @AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
    public void afterReturning(JoinPoint theJoinPoint, Object theResult)
    {
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("===> in @AfterReturning: from method: " + theMethod);
        
        myLogger.info("===> Result : " + theResult);
    }
}
