package com.web.ExcelOptModule.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/excelss")
//@PropertySource("classpath:config.properties")
public class ExportController {

    /**
     * 从本地加载
     * @throws IOException
     */
    @GetMapping("/imports")
    public void importExcel2() throws IOException {//@RequestParam("file") MultipartFile file

    }

}
