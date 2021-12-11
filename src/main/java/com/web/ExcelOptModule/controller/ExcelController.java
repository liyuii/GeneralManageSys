package com.web.ExcelOptModule.controller;

import cn.hutool.extra.mail.MailUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excel")
public class ExcelController {


    @PostMapping("/import")
    public void importExcel(@RequestParam("file") MultipartFile file) throws IOException {

//        String[] params = new String[]{"username","shbj","scbj"};
//        List<Map<String, Object>> objects = ExcelUtil.importExcel(file,params);
//        System.out.println(objects.toString());
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<List<Object>> read = reader.read(2, reader.getRowCount());
        for (List<Object> objects : read) {
            System.out.println(objects.get(0));
            objects.get(1);
        }
    }
}
