package cn.ivanzhu.controller.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ivanzhu
 * @date 2020/4/29
 * @time 11:26
 */
@Aspect
@Component
public class TestAspect {

    @Resource
    private HttpServletResponse response;

    @Pointcut("@annotation(AspectAnnotation)")
    private void cutMethod(){

    }

    @Around(value = "cutMethod()")
    public void around(ProceedingJoinPoint pjp) throws Throwable{
        Signature signature = pjp.getSignature();
        if (signature instanceof MethodSignature){
            String methodName = signature.getName();
            Class<?> clazz = pjp.getTarget().getClass();
            Method method = clazz.getDeclaredMethod(methodName, ((MethodSignature) signature).getParameterTypes());
            AspectAnnotation annotation = method.getAnnotation(AspectAnnotation.class);
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes instanceof ServletRequestAttributes){
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
                if (annotation.editAge()){
                    String name = request.getParameter("name");
                    System.out.println("姓名：" + name);
                }
                if (annotation.editName()){
                    String age = request.getParameter("age");
                    System.out.println("年龄：" + age);
                }
            }
        }
        Object proceed = pjp.proceed();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(proceed));
    }
}
