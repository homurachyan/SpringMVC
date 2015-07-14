package com.springmvc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private final Logger log = Logger.getLogger(LoginInterceptor.class);  
	private List<String> unCheckUrls; 
	@Override    
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {    
				
        if ("GET".equalsIgnoreCase(request.getMethod())) {  
            //RequestUtil.saveRequest();  
        }
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());  
        
        log.info("requestUri:"+requestUri);    
        log.info("contextPath:"+contextPath);    
        log.info("url:"+url); 
        if(unCheckUrls.contains(url)){
        	return true; 
        }else{
            log.info("==============执行顺序: 1、preHandle================");    
                       
            String username =  (String)request.getSession().getAttribute("user");   
            if(username == null){  
                log.info("Interceptor：跳转到login页面！");  
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);  
                return false;  
            }else  
                return true;          	
        }
	}
	
    @Override    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {     
        log.info("==============执行顺序: 2、postHandle================");    
        if(modelAndView != null){  //加入当前时间    
            modelAndView.addObject("var", "测试postHandle");    
        }    
    }    
    
    /**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     *   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {    
        log.info("==============执行顺序: 3、afterCompletion================");    
    }

	public List<String> getUnCheckUrls() {
		return unCheckUrls;
	}

	public void setUnCheckUrls(List<String> unCheckUrls) {
		this.unCheckUrls = unCheckUrls;
	}    	
	
	
}
