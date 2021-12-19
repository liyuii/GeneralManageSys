package com.web.WechatModule.service;

import com.web.util.ShaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WxCheckSignatureService {

    @Value("${wx.token}")
    public String token;

    public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        log.info("signature:{},token:{},timestamp:{},nonce:{}",signature,token,timestamp,nonce);
        String tmpStr = ShaUtil.getSHA1(token,  timestamp,  nonce);
        //TODO 进行对比
        log.info("随机字符串echostr:{}",echostr);
        log.info("tmpStr:{}",tmpStr);
        if (tmpStr.equals(signature.toUpperCase())) {
            return echostr;
        }
        return null;
    }
}
