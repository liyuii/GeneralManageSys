package com.web.WechatModule.controller;

import com.web.WechatModule.service.WxCheckSignatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class WechatSignController {

    @Autowired
    WxCheckSignatureService checkSignatureService;

    @PostMapping("/verifyUrl")
    public String checkToken(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
                             @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {
        System.out.println("验签");
        return checkSignatureService.checkSignature(signature, timestamp, nonce, echostr);
    }
}
