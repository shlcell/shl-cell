package com.shl.demo.aop;//package com.shl.demo.aop;
//
//import com.alibaba.fastjson.JSONObject;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//@Aspect
//public class GlobalActuator {
//
//    private static final Logger log = LoggerFactory.getLogger(GlobalActuator.class);
//
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//    ThreadLocal<Long> startTime = new ThreadLocal<>();
//
//    ConcurrentHashMap<Object, Object> countMap = new ConcurrentHashMap<Object, Object>();
//
//    /**
//     * 匹配控制层层通知 这里监控controller下的所有接口
//     */
//    @Pointcut("execution(* com.sf.controller.*Controller.*(..))")
//    private void controllerPt() {
//
//    }
//
//
//    /**
//     * 在接口原有的方法执行前，将会首先执行此处的代码
//     */
//    @Before("com.sf.actuator.GlobalActuator.controllerPt()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//
//        startTime.set(System.currentTimeMillis());
//    }
//
//    /**
//     * 只有正常返回才会执行此方法
//     * 如果程序执行失败，则不执行此方法
//     */
//    @AfterReturning(returning = "returnVal", pointcut = "com.sf.actuator.GlobalActuator.controllerPt()")
//    public void doAfterReturning(JoinPoint joinPoint, Object returnVal) throws Throwable {
//
//        Signature signature = joinPoint.getSignature();
//        String declaringName = signature.getDeclaringTypeName();
//        String methodName = signature.getName();
//        String mapKey = declaringName + methodName;
//
//        // 执行成功则计数加一
//        int increase = AtomicCounter.getInstance().increase();
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//
//        synchronized (this) {
//            //在项目启动时，需要在Redis中读取原有的接口请求次数
//            if (countMap.size() == 0) {
//                JSONObject jsonObject = RedisUtils.objFromRedis(StringConst.INTERFACE_ACTUATOR);
//                if (jsonObject != null) {
//                    Set<String> strings = jsonObject.keySet();
//                    for (String string : strings) {
//                        Object o = jsonObject.get(string);
//                        countMap.putIfAbsent(string, o);
//                    }
//                }
//            }
//        }
//
//        // 如果此次访问的接口不在countMap，放入countMap
//        countMap.putIfAbsent(mapKey, 0);
//        countMap.compute(mapKey, (key, value) -> (Integer) value + 1);
//
//
//        synchronized (this) {
//            // 内存计数达到30 更新redis
//            if (increase == 30) {
//                RedisUtils.objToRedis(StringConst.INTERFACE_ACTUATOR, countMap, Constants.AVA_REDIS_TIMEOUT);
//                //删除过期时间
//                stringRedisTemplate.persist(StringConst.INTERFACE_ACTUATOR);
//                //计数器置为0
//                AtomicCounter.getInstance().toZero();
//            }
//        }
//
//        //log.info("方法执行次数:" + mapKey + "------>" + countMap.get(mapKey));
//        //log.info("URI:[{}], 耗费时间:[{}] ms", request.getRequestURI(), System.currentTimeMillis() - startTime.get());
//    }
//
//
//    /**
//     * 当接口报错时执行此方法
//     */
//    @AfterThrowing(pointcut = "com.sf.actuator.GlobalActuator.controllerPt()")
//    public void doAfterThrowing(JoinPoint joinPoint) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        log.info("接口访问失败，URI:[{}], 耗费时间:[{}] ms", request.getRequestURI(), System.currentTimeMillis() - startTime.get());
//    }
//
//}
