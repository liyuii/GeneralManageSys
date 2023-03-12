package com.web.ExcelOptModule.controller;

//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.ExcelImportUtil;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import cn.afterturn.easypoi.excel.entity.ImportParams;
//import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
//import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
//import com.web.ExcelOptModule.entity.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/excel")
@PropertySource("classpath:config.properties")
public class ExcelController {

//    @Value("${excel.filesavepath}")
//    private String path;
//
//
//    @PostMapping("/import")
//    public void importExcel(@RequestParam("file") MultipartFile file) throws IOException {
//
////        String[] params = new String[]{"username","shbj","scbj"};
////        List<Map<String, Object>> objects = ExcelUtil.importExcel(file,params);
////        System.out.println(objects.toString());
//        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
//        List<List<Object>> read = reader.read(2, reader.getRowCount());
//        for (List<Object> objects : read) {
//            System.out.println(objects.get(0));
//            objects.get(1);
//        }
//    }
//
//    /**
//     * 从本地加载
//     * @throws IOException
//     */
//    @GetMapping("/import2")
//    public void importExcel2() throws IOException {//@RequestParam("file") MultipartFile file
//        //文件的位置,Excel文件的说明需要和实体类所对应
//        File file = new File("C:/Users/86137/Documents/excels/appemp.xls");
//        //导入的标题和说明的设置
//        ImportParams params = new ImportParams();
//        //设置标题
//        params.setTitleRows(1);
//        //设置说明
//        params.setHeadRows(1);
//        //导入获取集合
//        List<StudentEntity> users = ExcelImportUtil.importExcel(file, StudentEntity.class, params);
//        //遍历集合
//        users.forEach(System.out :: println);
//    }
//
//    /**
//     * 导出
//     * @throws IOException
//     */
//    @GetMapping("/export1")
//    public void export1(HttpServletResponse response) throws IOException {
//        log.info("-------------------导出开始--------------------");
//        log.info(path);
//        List<StudentEntity> list = new ArrayList<>();
//        list.add(new StudentEntity("1","zhangsna",1,new Date(),new Date()));
//        list.add(new StudentEntity("2","lisi",1,new Date(),new Date()));
//        list.add(new StudentEntity("3","wangwu",2,new Date(),new Date()));
//        Workbook workbook = ExcelExportUtil.exportExcel(
//                new ExportParams("计算机一班学生","学生", ExcelType.XSSF), StudentEntity.class,list
//        );
////        FileOutputStream fos = new FileOutputStream("C:/Users/86137/Documents/excels/appemp.xls");
//        String fileName = "appemp";
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("content-Type", "application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
//        workbook.write(response.getOutputStream());
//        log.info("-------------------导出结束--------------------");
//    }
//
//    /**
//     * 导出，使用模板
//     * @throws IOException
//     */
//    @GetMapping("/export2")
//    public void export2(HttpServletResponse response) throws IOException {
//        log.info("-------------------导出开始--------------------");
//
//        //优点---这种方法不会在linux或者jar上失效
////        ClassPathResource classPathResource = new ClassPathResource("D:/excel/学生信息表.xlsx");
////        String resource = classPathResource.getURL().getPath();
//
//        TemplateExportParams params = new TemplateExportParams("D:/excel/学生信息表.xlsx");
//        Map<String, Object> data = new HashMap<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        data.put("date", sdf.format(new Date()));
//        data.put("title", "学生信息");
//
//        List<StudentEntity> list = new ArrayList<>();
//        list.add(new StudentEntity("1","zhangsna",1,new Date(),new Date()));
//        list.add(new StudentEntity("2","lisi",1,new Date(),new Date()));
//        list.add(new StudentEntity("3","wangwu",2,new Date(),new Date()));
//        data.put("list",list);
//
//        Workbook workbook = ExcelExportUtil.exportExcel(params, data);
//        String fileName = "temp";
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("content-Type", "application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
//        workbook.write(response.getOutputStream());
//        log.info("-------------------导出结束--------------------");
//    }
}
