package com.scsb.springsecurity01.controller;

import com.google.code.kaptcha.Producer;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class VerifyCodeController {
    @Autowired
    Producer producer;

    @GetMapping("/verify-code.jpg")
    public void verifyCode(HttpServletResponse resp, HttpSession session) throws IOException {

        resp.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("kaptcha",text);
        BufferedImage image = producer.createImage(text);
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }
}



