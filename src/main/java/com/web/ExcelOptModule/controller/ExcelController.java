package com.web.ExcelOptModule.controller;

import cn.afterturn.easypoi.cache.manager.FileLoaderImpl;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelStyleType;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.web.ExcelOptModule.entity.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/excel")
@PropertySource("classpath:config.properties")
public class ExcelController {

    @Value("${excel.filesavepath}")
    private String path;


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

    /**
     * ???????????????
     * @throws IOException
     */
    @GetMapping("/import2")
    public void importExcel2() throws IOException {//@RequestParam("file") MultipartFile file
        //???????????????,Excel??????????????????????????????????????????
        File file = new File("C:/Users/86137/Documents/excels/appemp.xls");
        //?????????????????????????????????
        ImportParams params = new ImportParams();
        //????????????
        params.setTitleRows(1);
        //????????????
        params.setHeadRows(1);
        //??????????????????
        List<StudentEntity> users = ExcelImportUtil.importExcel(file, StudentEntity.class, params);
        //????????????
        users.forEach(System.out :: println);
    }

    /**
     * ??????
     * @throws IOException
     */
    @GetMapping("/export1")
    public void export1(HttpServletResponse response) throws IOException {
        log.info("-------------------????????????--------------------");
        log.info(path);
        List<StudentEntity> list = new ArrayList<>();
        list.add(new StudentEntity("1","zhangsna",1,new Date(),new Date()));
        list.add(new StudentEntity("2","lisi",1,new Date(),new Date()));
        list.add(new StudentEntity("3","wangwu",2,new Date(),new Date()));
        Workbook workbook = ExcelExportUtil.exportExcel(
                new ExportParams("?????????????????????","??????", ExcelType.XSSF), StudentEntity.class,list
        );
//        FileOutputStream fos = new FileOutputStream("C:/Users/86137/Documents/excels/appemp.xls");
        String fileName = "appemp";
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        workbook.write(response.getOutputStream());
        log.info("-------------------????????????--------------------");
    }

    /**
     * ?????????????????????
     * @throws IOException
     */
    @GetMapping("/export2")
    public void export2(HttpServletResponse response) throws IOException {
        log.info("-------------------????????????--------------------");

        //??????---?????????????????????linux??????jar?????????
//        ClassPathResource classPathResource = new ClassPathResource("D:/excel/???????????????.xlsx");
//        String resource = classPathResource.getURL().getPath();

        TemplateExportParams params = new TemplateExportParams("D:/excel/???????????????.xlsx");
        Map<String, Object> data = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        data.put("date", sdf.format(new Date()));
        data.put("title", "????????????");

        List<StudentEntity> list = new ArrayList<>();
        list.add(new StudentEntity("1","zhangsna",1,new Date(),new Date()));
        list.add(new StudentEntity("2","lisi",1,new Date(),new Date()));
        list.add(new StudentEntity("3","wangwu",2,new Date(),new Date()));
        data.put("list",list);

        Workbook workbook = ExcelExportUtil.exportExcel(params, data);
        String fileName = "temp";
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        workbook.write(response.getOutputStream());
        log.info("-------------------????????????--------------------");
    }
}
