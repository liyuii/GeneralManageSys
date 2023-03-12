package com.web.ExcelOptModule.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.web.ExcelOptModule.entity.tb_test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/web/easyexcel")
public class EasyExcelController {

//    private JdbcOperations jdbcTemplate;


//    @ApiOperation(value = "导入")
    @GetMapping("imports.json")
    public void imports(HttpServletRequest request) throws Exception {
//        Result<Object> result = new Result<>();
        String filename = "D:/tbez/user.xlsx";
        List<tb_test> list = new ArrayList<>();
        EasyExcel.read(filename, tb_test.class, new AnalysisEventListener<tb_test>() {
            @Override
            public void invoke(tb_test o, AnalysisContext analysisContext) {
                System.out.println("导入成功");
                System.out.println(o);
                list.add(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("共导入：" + list.size() + "个");
            }

            @Override
            public void onException(Exception exception, AnalysisContext context) {
//                log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
//                errorMessage.append("解析失败,请联系后台管理员"+exception.getMessage());
//                // 如果是某一个单元格的转换异常 能获取到具体行号
//                // 如果要获取头的信息 配合invokeHeadMap使用
//                if (exception instanceof ExcelDataConvertException) {
//                    ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
//                    formatMessage.append("第"+excelDataConvertException.getRowIndex()+"行第"+excelDataConvertException.getColumnIndex()+"列数据转换异常");
//                    log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
//                            excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
//
//                }
            }
        }).sheet().doRead();

//        result.setCTd("v","");
//        return result;
    }


//    @ApiOperation(value = "导出")
    @GetMapping("exports.json")
    public void exports(HttpServletRequest request) throws Exception {
//        Result<Object> result = new Result<>();
        String fileName = "D:/tbez/" + "User" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, tb_test.class).sheet("测试表").doWrite(addObj());
//        result.setCTd("v","");
//        return result;
    }

    private List<tb_test> addObj() {
        List<tb_test> list = new ArrayList<>();
        tb_test tes = new tb_test();
        tes.setId("1");
        tes.setName("测试人员");
        tes.setMobile("12345678901");
        tes.setEmail("1248579412@qq.com");
        tes.setAdduser("admin");
        tes.setAddtime(new Date());
        list.add(tes);

        tb_test tes2 = new tb_test();
        tes2.setId("2");
        tes2.setName("测试人员2");
        tes2.setMobile("56781234901");
        tes2.setEmail("9412124857@qq.com");
        tes2.setAdduser("admin");
        tes2.setAddtime(new Date());
        list.add(tes2);

        tb_test tes3 = new tb_test();
        tes3.setId("3");
        tes3.setName("测试人员3");
        tes3.setMobile("56749018123");
        tes3.setEmail("4859412127@qq.com");
        tes3.setAdduser("admin");
        tes3.setAddtime(new Date());
        list.add(tes3);
        return list;
    }

    //http://localhost:9201/web/easyexcel/exportcomplex.json
//    @ApiOperation(value = "复杂导出")
    @GetMapping("exportcomplex.json")
    public void exportcomplex(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Result<Object> result = new Result<>();
//        String template = "D:/tbez/template.xlsx";
        String template = Thread.currentThread().getContextClassLoader().getResource("template.xlsx").getPath();
//        System.out.println(path);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("复杂导出.xlsx", "UTF-8"));
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(template).build();
            //构建excel的sheet
            WriteSheet writeSheet = EasyExcel.writerSheet().build();

            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
//            List<tb_test> tb_tests = addObj();
//            excelWriter.fill(new FillWrapper("list", tb_tests), fillConfig, writeSheet);
//            excelWriter.fill(addObj(), writeSheet);
            excelWriter.fill(addObj(),fillConfig,writeSheet);
            Map<String, String> fileData = new HashMap<>();
            fileData.put("zhuti", "测试人员信息");
            fileData.put("date", simple.format(new Date()));
            fileData.put("location", "新百广场");
            fileData.put("master", "百度");
            fileData.put("total", "2");
            fileData.put("people", "审核");
            fileData.put("end", simple.format(new Date()));
            excelWriter.fill(fileData, writeSheet);
//            excelWriter.finish();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
//        result.setCTd("v","");
    }

//    @ApiOperation(value = "测试")
//    @GetMapping("test.json")
//    public Result<Object> test(HttpServletRequest request) throws Exception {
//        return null;
//    }

}
