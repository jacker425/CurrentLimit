package com.hkl.slide.window;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName SystemLimitAspect
 * @Description 切面，代理所有标注@SlideWindowLimit的方法，进行限流
 * @Author jacker425
 * @Date 2021/4/7 上午11:02
 **/
@Aspect
@Component
@Slf4j
public class SystemLimitAspect {

    @Autowired
    private SlideWindow slideWindow;

    @Pointcut(value = "@annotation(SlideWindowLimit)")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object aroud(ProceedingJoinPoint pjp) throws Throwable {
        String targetName = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        log.info("limit targetName {}, methodName {} began", targetName, methodName);
        //判断是否被限流
        if(slideWindow.isGo(targetName+"."+methodName)){
            log.info("There is no current limit. Continue {}", targetName+"."+methodName);
            return pjp.proceed();
        }
        log.info("The current has been limited targetName {}, methodName {} over", targetName, methodName);
        return "throttle";
    }
}
