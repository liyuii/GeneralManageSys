package com.web.ManageModule.controller;

import cn.hutool.extra.qrcode.QrCodeException;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.web.ManageModule.service.EmailService;
import com.web.base.common.Result;
import com.web.ManageModule.entity.auth_user;
import com.web.base.common.WxProperties;
import com.web.util.EmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.web.util.whyUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "统一系统-基础方法",tags = "统一系统-基础方法")
@RestController
@RequestMapping("/mytest")
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    EmailService emailService;

    /**
     * 测试在工具类中获取bean
     * @param
     * @return
     */
    @ApiOperation(value = "测试")
    @RequestMapping("/util")
    @ResponseBody
    public void testUtil(){
//        UserQuery userQuery = new UserQuery();
//        userQuery.setQueryType("0");
//        List<User> users = userService.userPage(userQuery);
//        System.out.println(users);
        List<auth_user> methods = whyUtil.methods();
        System.out.println(methods);
    }

    /**
     * 测试配置类
     * @param
     * @return
     */
    @RequestMapping("/peoper")
    @ResponseBody
    public void peoper(){
        System.out.println("测试配置类");
        WxProperties properties = new WxProperties();
        System.out.println(properties.getToken());
    }

    /**
     *
     * @param method
     * @param args
     */
    @GetMapping("/selectMethod")
    @ResponseBody
    public Result<Map<String,Object>> selectMethod(String method, Map<String,Object> args){
        Result<Map<String,Object>> result = new Result<>();

        try {
            Class<?> aClass = Class.forName("com.web.base.common.AutoSelectMethod");
//            aClass.getDeclaredMethod(method,args);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

//    @ApiOperation(value = "发送邮件", notes = "")
    @GetMapping("/sendemail")
    public void sendEmail() {
        System.out.println("邮件系统--- 启动");
        EmailUtils emailUtils = new EmailUtils();
//        String content = "尊敬的受益人您好：<br/> 被保证人在保函通申请的履约保函已在2022年4月15日完成出函。您可以点击<a href=\"https://www.baidu.com/\"></a>获取，也可以使用微信扫描如下二维码获取保函信息。";
        String content = "<div style=\"width:100%;\">\n" +
                "        <div>尊敬的（受益人）您好：</div>\n" +
                "        <div style=\"text-indent: 40px;\">被保证人在保函通申请的履约保函已在2022年4月15日完成出函。您可以点击<a href=\"https://www.baidu.com/\">我是超链接</a>获取，也可以使用微信扫描如下二维码获取保函信息</div>\n" +
                "        <div style=\"width:50%; margin-left: 500px;\" >二维码</div>\n" +
                "        <p><img src='cid:image'></p>\n" +
                "    </div>\n";
//        emailUtils.SendEmail("690227436@qq.com", "被保证人 履约保函", content);
//        String content = "<div style=\"width:100%;\">\n" +
//                "        <div>尊敬的（受益人）您好：</div>\n" +
//                "        <div style=\"text-indent: 40px;\">被保证人在保函通申请的履约保函已在2022年4月15日完成出函。您可以点击<a href=\"https://www.baidu.com/\">我是超链接</a>获取，也可以使用微信扫描如下二维码获取保函信息</div>\n" +
//                "        <div  style=\"width:50%; margin-left: 500px;\" ><img src=\"https://www.baidu.com/link?url=PApERqviCMTv50_G56GuF3AcK8IOVZ6Jam60dR51LSm8pMe_DVsRGwE3itHSTNPzRJ4nj-HimYcw7FKVUVkxJXCcnguB-h3YqBuVtNCt-ukz92iDgziP2lDTPb8dRlxxySfv55EDi6-UHXzAXHoesJ8o7QNBmc5tnvxosXR27QGdPfkEZzGRV9bipWIqjTh80jNTkWkpVkxJlRVNpvqqeEpXBMCfljW7ALsDr8rb9BZLO3Yxm8vcsaGYS2Es78hbkOBoIR30485i_yVzbtfi8w7REkS2AQ2F_WjGl4GrEkiS56GqxbiRf1mBFL8FOP5xRx4-DC59Kl9ADuBDwL-CTyrikEl9hZ4EHWab3jusrYMXS2fUibhdTYlB8dH4SSG-RKXH150JP_xn-vGvkgoxhUmv3U95Pk2nCPz2VFh-4yB9lgPJmssJqv2w8oX81-IjYf2Xh7S3Xa1lVUsrMZNV5bIyRjrRjzcUMAG_D9HPB0fzCt-KwV-ff2CyV12v9OkIRObApy5R-P5zJqlAlZkVe2HjofadyAL-1IoJAwkxfcpQ5tBZTBaT5a42D9KKViPfnjLbxaR2Prt80EIfK80JF582q62BlRIBq8wWC-EUAM2e7kp6Z3KggRfCkUZiG9IEi0My58c9gblkFPUmEBnuwq&wd=&eqid=90392ec00001c94b0000000362592a57\" alt=\"\"></div>\n" +
//                "    </div>";
//        emailUtils.SendEmail("690227436@qq.com", "被保证人 履约保函", content);
        emailUtils.SendEmail("3503007019@qq.com", "被保证人 履约保函", content);
        System.out.println("邮件发送成功");
    }

    /**
     * 获取二维码
     * yuanzidu
     * @return
     */
    @GetMapping("/getqrcode")
    public void getQRCode(HttpServletResponse response) throws IOException {
        String qrCodeUrl = "测试内容";
        try {
            QrConfig qrConfig = new QrConfig(300, 300);
            QrCodeUtil.generate(qrCodeUrl, qrConfig, "png", response.getOutputStream());
//            log.info("生成二维码成功!");
            System.out.println("成功");
        } catch (QrCodeException | IOException e) {
//            log.error("发生错误！ {}！", e.getMessage());
            System.out.println("错误");
        }
    }

    @GetMapping("/testemail")
    public void testemail(HttpServletResponse response) throws IOException, WriterException {
//        String content = "<div style=\"width:100%;\">\n" +
//                "        <div>尊敬的（受益人）您好：</div>\n" +
//                "        <div style=\"text-indent: 40px;\">被保证人在保函通申请的履约保函已在2022年4月15日完成出函。您可以点击< a href=\"https://www.baidu.com\">我是超链接</ a>获取，也可以使用微信扫描如下二维码获取保函信息</div>\n" +
//                "        <div style=\"width:50%; margin-left: 500px;\" >二维码</div>\n" +
//                "    </div>";
        String content = "<div style=\"width:100%;\">\n" +
                "        <div>尊敬的（受益人）您好：</div>\n" +
                "        <div style=\"text-indent: 40px;\">被保证人在保函通申请的履约保函已在2022年4月15日完成出函。您可以点击<a href=\"https://www.baidu.com/\">我是超链接</a>获取，也可以使用微信扫描如下二维码获取保函信息</div>\n" +
                "        <div style=\"width:50%; margin-left: 500px;\" >二维码</div>\n" +
                "        <p><img src='cid:image'></p>\n" +
                "    </div>\n";
        List<String> recivers = new ArrayList<>();
        recivers.add("3503007019@qq.com");
        ByteArrayOutputStream outputStream = generateQRCodeImage("hello,it's a test", 300, 300);
//        emailService.sendMailFromQQ(EmailService.HOST_QQ,EmailService.MESSAGE_TEXT,recivers, "被保证人 履约保函", content,null);
        emailService.sendMailFromQQ(EmailService.HOST_QQ,EmailService.MESSAGE_IMAGE,recivers, "被保证人 履约保函", content,outputStream);
    }

    private ByteArrayOutputStream generateQRCodeImage(String text, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
//        Path path = FileSystems.getDefault().getPath(filePath);
//        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", outputStream);
        return outputStream;
    }
}
