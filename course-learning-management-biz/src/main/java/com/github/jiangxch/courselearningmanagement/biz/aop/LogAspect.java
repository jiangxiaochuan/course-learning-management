package com.github.jiangxch.courselearningmanagement.biz.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author: sanjin
 * @date: 2020/1/3 下午11:26
 */
@Aspect
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
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            long end = System.nanoTime();
            // [全类名]#[方法],args=[方法参数列表],result=[返回结果],cost=[执行时间(ms)]
            log.info("{}#{},args={},result={},cost={} ms",
                    getClass().getName(), joinPoint.getSignature().getDeclaringTypeName(),
                    Arrays.toString(joinPoint.getArgs()),
                    result.toString(), caculateDuration(start, end));//stopWatch.getLastTaskTimeMillis()
            return result;
        } catch (Throwable e) {
            long end = System.nanoTime();
            log.info("{}#{},args={},exception={}",
                    getClass().getName(), joinPoint.getSignature().getDeclaringTypeName(),
                    Arrays.toString(joinPoint.getArgs()),
                    e.getClass().getTypeName() + ":" + e.getMessage());//stopWatch.getLastTaskTimeMillis()
            throw e;
        }
    }

    private float caculateDuration(long start,long end) {
        // nonoTime 单位 毫微妙,转为ms并且保留小数点后面一位
        float cost = (float)((end - start) / 100000);
        cost = cost / 10;
        return cost;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());

        long start = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        long end = System.nanoTime();
        // nonoTime 单位 毫微妙,转为ms并且保留小数点后面一位
        float cost = (float)((end - start) / 100000);
        cost = cost / 10;
        System.out.println(cost);
    }
}
