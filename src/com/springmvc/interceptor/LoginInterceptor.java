package com.springmvc.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.entity.Session;
import com.springmvc.util.SessionUtil;
import com.springmvc.util.SpringContextHolder;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private final Logger log = Logger.getLogger(LoginInterceptor.class);  
	private List<String> unCheckUrls; 
	
	/**
	 * 在执行Controller之前进行预处理
	 * */
	@Override    
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {    
				
        if ("GET".equalsIgnoreCase(request.getMethod())) {  
            //RequestUtil.saveRequest();  
        }
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());  
        
        System.out.println("requestUri:"+requestUri);    
        System.out.println("contextPath:"+contextPath);    
        System.out.println("url:"+url); 
        System.out.println("content-Type:"+request.getContentType());
        
        if(unCheckUrls.contains(url)){
        	return true; 
        }else{
            log.info("==============执行顺序: 1、preHandle================"); 
            Session session = new Session();
            String clientType = request.getParameter("client");
            if(request.getParameter("client")!=null&&request.getParameter("client").equals("pc")){
                //使用浏览器Cookie模式
    	            Cookie[] cookies = request.getCookies();
    	            for (Cookie cookie : cookies) {  
    	                String name = cookie.getName();  
    	                if (name.compareTo("token") == 0) {  
    	                	session.setSessionid(cookie.getValue());
    	                }  
    	                if (name.compareTo("uid") == 0) {  
    	                	session.setUserid(cookie.getValue());
    	                }  
    	            }  
            }else{
                //也可以实现Token模式
                session.setUserid(request.getParameter("userid")!=null?request.getParameter("userid"):null);
                session.setSessionid(request.getParameter("token")!=null?request.getParameter("token"):null);          	
            }
            System.out.println(session.getSessionid()+" "+session.getUserid());
            SessionUtil sessionutil = (SessionUtil)SpringContextHolder.getBean("sessionUtil");    
            if(session.getSessionid()!=null&&sessionutil.checkSession(session)){  
                return true;   
            }else            
                if("application/json".equals(request.getContentType())){
                	response.setContentType("application/json;charset=UTF-8");//设置response的传输格式为json 
                	response.getWriter().write("{\"error_code\":\"001\",\"view\":\"login.jsp\",\"message\":\"login first\"}");
                }else{
                	request.getRequestDispatcher("/login.jsp").forward(request, response);  
                }
	            
	            return false;  
        }
	}
	/**
	 * 在视图渲染之前进行处理
	 * */
    @Override    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {     
        log.info("==============执行顺序: 2、postHandle================");    
        if(modelAndView != null){  //包装
        	modelAndView.addObject("request",  "http://"+request.getServerName()+request.getRequestURI());
        	if("error".equals(modelAndView.getViewName())){
                //对于异常请求进行包装
        		modelAndView.addObject("error", "错误");
        		modelAndView.addObject("error_code", "110");
        	}else{
                modelAndView.addObject("message", "success");   
        	} 
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
