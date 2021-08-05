package com.xl.util;


import com.xl.domain.AuctionCustomerException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author XLong
 * @create 2021-08-05 16:21
 */
@Component
public class MyHandlerExeception implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        String errMsg = "未知异常！";
        AuctionCustomerException auctionCustomerException = null;
        if (e instanceof  AuctionCustomerException) {
            auctionCustomerException = (AuctionCustomerException) e;
        }else { //未知异常

        }
        errMsg = auctionCustomerException.getMessage();
        //将错误信息存在model域中
        modelAndView.addObject("errorMsg", errMsg);
        modelAndView.setViewName("errorPage");
        return modelAndView;
    }
}
