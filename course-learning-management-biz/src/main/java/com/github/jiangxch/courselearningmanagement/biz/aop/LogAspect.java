package com.github.jiangxch.courselearningmanagement.biz.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author: sanjin
 * @date: 2020/1/3 下午11:26
 */
@Aspect
@Component
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.github.jiangxch.courselearningmanagement.biz.controller..*)" +
            " || within(com.github.jiangxch.courselearningmanagement.provider.service..*)" +
            " || within(com.github.jiangxch.courselearningmanagement.provider.dao..*)")
    public void applicationPackagePointcut() {
    }

    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        long start = System.nanoTime();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String methodName = point.getSignature().getName();
        String className = AopUtils.getTargetClass(point.getTarget()).getSimpleName();
        String args = Arrays.toString(point.getArgs());
        try {
            checkException(point);
            Object result = point.proceed();
            long end = System.nanoTime();
            // [全类名]#[方法],args=[方法参数列表],result=[返回结果],cost=[执行时间(ms)]
            log.info("{}#{},args={},result={},cost={} ms",
                    className,
                    methodName,
                    args,
                    result == null ? null : result.toString(),
                    caculateDuration(start, end));//stopWatch.getLastTaskTimeMillis()
            return result;
        } catch (Throwable e) {
            long end = System.nanoTime();
            log.info("{}#{},args={},\n exception= {}",
                    className,
                    methodName,
                    args,
                    getExceptionStackMsg(e));//stopWatch.getLastTaskTimeMillis()
            throw e;
        }
    }

    private float caculateDuration(long start, long end) {
        // nonoTime 单位 毫微妙,转为ms并且保留小数点后面一位
        float cost = (float) ((end - start) / 100000);
        cost = cost / 10;
        return cost;
    }

    private String getExceptionStackMsg(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        sb.append(throwable.getClass().getTypeName()).append("\n");
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        for (StackTraceElement e : stackTrace) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * 当某个方法发生了可以被 {@link GlobalExceptionController} 捕获,则LogAspect不会进入异常
     * 处理,故无法获取异常堆栈信息,但是{@link ProceedingJoinPoint#getArgs()} 可以获取到异常参数,
     * 通过参数是否有异常来打印异常堆栈信息
     * <p>
     * 为了解决全局异常捕获
     *
     * @param point
     * @throws Throwable
     */
    private void checkException(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        for (Object obj : args) {
            if (obj instanceof Throwable) {
                throw (Throwable) obj;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());

        long start = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        long end = System.nanoTime();
        // nonoTime 单位 毫微妙,转为ms并且保留小数点后面一位
        float cost = (float) ((end - start) / 100000);
        cost = cost / 10;
        System.out.println(cost);
    }
}
