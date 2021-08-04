package com.xl.controller;

import com.xl.domain.User;
import com.xl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author XLong
 * @create 2021-08-04 21:36
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String toLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/doLogin")
    public String login(String username,
                        String userPassword,
                        String valideCode, //用户输入的验证码
                        HttpSession session,
                        Model model) {
        String randomCode = (String) session.getAttribute("vrifyCode"); //产生的随机验证码
        if (!valideCode.equals(randomCode)) { //验证码不一致
            model.addAttribute("errorMsg","验证码输入错误！");
        } else {
            List<User> userList = this.userService.loginByUsernameAndPwd(username,userPassword);
            if (userList != null && userList.size() > 0) { //登录成功
                //将用户信息存在session中
                session.setAttribute("user_session",userList);
                //跳转到首页
                return "index";
            } else { //失败
                model.addAttribute("errorMsg","用户名或密码错误！");
                return "login";
            }
        }
        return "login";
    }
}
