package com.example.demo.log;

import com.alibaba.fastjson.JSON;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: liyang27
 * @Date: 2020/8/17 10:58
 * @Description:
 */
public class MyLogInterceptor extends HandlerInterceptorAdapter {
    private static final ThreadLocal<Long> startTimeThreadLocal=new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if(null!=myLog){
            long startTime = System.currentTimeMillis();
            startTimeThreadLocal.set(startTime);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if(null!=myLog){
            long startTime = startTimeThreadLocal.get();
            long endTime=System.currentTimeMillis();
            long expendTime = endTime-startTime;

            String requestUri = request.getRequestURI();
            String methodName = method.getDeclaringClass().getName()+"#"+method.getName();
            String desc = myLog.desc();
            String parameters = JSON.toJSONString(request.getParameterMap());

            System.out.println("描述："+desc+"\n路径："+requestUri+"\n方法："+methodName+"\n参数："+parameters+"\n耗时："+expendTime);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
