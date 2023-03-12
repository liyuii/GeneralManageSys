package com.web.HanLPModule.util;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AsposeWordUtil {

    public static void main(String[] args) {
        String inPath = "D:\\soft\\files\\in\\【田海娇】后疫情时代企业员工加班现状-20221203.docx";
        String outPath = "D:\\soft\\files\\out\\【田海娇】后疫情时代企业员工加班现状-20221203.pdf";
        try {
            wordToPdf(inPath,outPath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void wordToPdf(String inPath,String outPath) throws Exception {
        long start = System.currentTimeMillis();
        File file = new File(outPath);
        FileOutputStream outputStream = new FileOutputStream(file);
        Document doc = new Document(inPath);
        doc.save(outputStream, SaveFormat.PDF);
        long end = System.currentTimeMillis();
        System.out.println("Word转换成功，共耗时：" + (end - start) + "ms"); // 转化用时
    }

    public static void wordToPdf(FileInputStream input, String outPath) throws Exception {
        long start = System.currentTimeMillis();
        File file = new File(outPath);
        FileOutputStream outputStream = new FileOutputStream(file);
        Document doc = new Document(input);
        doc.save(outputStream, SaveFormat.PDF);
        long end = System.currentTimeMillis();
        System.out.println("Word转换成功，共耗时：" + (end - start) + "ms"); // 转化用时
    }

}
