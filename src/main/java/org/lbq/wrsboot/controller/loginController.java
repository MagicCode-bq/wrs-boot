package org.lbq.wrsboot.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.GifCaptcha;
import org.lbq.wrsboot.Vo.RespVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@RestController
public class loginController {

    @GetMapping("/login")
    public RespVo toLogin(){
        return  new RespVo(402,"您还没有登陆，请先登陆","");
    }

    @GetMapping("/code")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义图形验证码的长和宽
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 50, 4, 20);

        //获取生成的验证码,存储到Session
        String code = captcha.getCode();
        request.getSession().setAttribute("login_code",code);

        //输出到浏览器：
        captcha.write(response.getOutputStream());

    }
}
