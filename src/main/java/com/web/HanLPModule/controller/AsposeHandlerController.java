package com.web.HanLPModule.controller;

import com.aspose.words.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/aspose")
public class AsposeHandlerController {

    private static final String ROOTPATH = "D:\\soft\\HanLP\\测试用例\\new";

    @GetMapping("/words/test1")
    public void docxTest() throws Exception {
        String file = ROOTPATH + File.separator + "高速公路招标文件.docx";
        Document doc = new Document(file);
        SectionCollection sections = doc.getSections();
        for(Section section:sections){
            ParagraphCollection paragraphs = section.getBody().getParagraphs();
            for(Paragraph paragraph:paragraphs){
                //是否有分隔符
                boolean separator = paragraph.getBreakIsStyleSeparator();
                System.out.println(separator);
                //获取制表符
                TabStop[] tabStops = paragraph.getEffectiveTabStops();
                System.out.println(tabStops);
                //样式
                FrameFormat frame = paragraph.getFrameFormat();
                System.out.println(frame);
                //格式列表
                ListFormat formats = paragraph.getListFormat();
                System.out.println(formats);
                //标签列表
                ListLabel labels = paragraph.getListLabel();
                System.out.println(labels);
                //节点类型
                int nodes = paragraph.getNodeType();
                System.out.println(nodes);
                //run
                RunCollection runs = paragraph.getRuns();
                System.out.println(runs);
                //范围
                Range range = paragraph.getRange();
                System.out.println(range);
            }
        }
    }

}
