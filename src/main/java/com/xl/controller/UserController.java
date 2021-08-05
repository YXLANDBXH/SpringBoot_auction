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

    /**
     * 登录功能
     * @param username
     * @param userPassword
     * @param valideCode
     * @param session
     * @param model
     * @return
     */
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
                User user = userList.get(0);
                //将用户信息存在session中
                session.setAttribute("user",user);
                //跳转到首页
                return "redirect:/queryAllAuctions";
            } else { //失败
                model.addAttribute("errorMsg","用户名或密码错误！");
                return "login";
            }
        }
        return "login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "/toregesiter")
    public String toRegister() {
        return "register";
    }

    /**
     * 注册功能
     * @param user
     * @return
     */
    @RequestMapping(value = "/userRegister")
    public String userRegister(User user, String valideCode, HttpSession session, Model model) {
        //只能注册普通用户
        user.setUserisadmin(0);
        String randomCode = (String) session.getAttribute("vrifyCode"); //产生的随机验证码
        if (!valideCode.equals(randomCode)) { //验证码不一致
            model.addAttribute("errorMsg","验证码输入错误！");
        } else {
            this.userService.userRegister(user);
            return "redirect:/login";
        }
        return "register";
    }
}
