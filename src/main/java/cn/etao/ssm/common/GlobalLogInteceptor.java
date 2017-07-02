package cn.etao.ssm.common;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangli on 2016/8/18.
 */
public class GlobalLogInteceptor implements HandlerInterceptor {

    //执行时机：controller方法没有没执行，ModelAndView没有没返回 。
    //使用场景：权限验证
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("===========HandlerInterceptor1 preHandle");
        return true;
    }

    //执行时机：Controller方法已经执行，ModelAndView没有返回
    //使用场景：可以在此防范重设置全局的数据处理业务
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("===========HandlerInterceptor1 postHandle");
    }

    //执行时机：Cotroller已经执行，modelandview已经返回
    //使用场景：记录操作日志，记录登陆ip、时间
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("===========HandlerInterceptor1 afterCompletion");
    }
}
