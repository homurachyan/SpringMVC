package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.entity.User;

@Controller
@RequestMapping("/jackson/view")
public class JacksonJsonViewController {
	@ResponseBody  
    @RequestMapping("/doBeanJsonView")
    public Object doBeanJsonView() {
        System.out.println("#################ViewController doBeanJsonView##################");
        ModelAndView mav = new ModelAndView("jsonView");
        User user = new User();
        user.setName("lalala");
        user.setPassword("123456");       
        mav.addObject(user);
        return mav;
    }
}
