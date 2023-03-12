package com.web.HanLPModule.util;

import com.aspose.words.*;

import java.io.File;

public class AsposeUtils {

    private static final String ROOTPATH = "D:\\soft\\HanLP\\测试用例\\old";
    private static final String NEWPATH = "D:\\soft\\HanLP\\测试用例\\new";

    public static void main(String[] args) throws Exception {
        replaceRangeText();
//        docxTest();
//        tableOpt();
//        getTableValue();
    }


    public static void replaceRangeText() throws Exception {
        FindReplaceOptions finds = new FindReplaceOptions();
        String arg1 = "${arg1}";
        String arg2 = "${arg2}";
        String arg3 = "${arg3}";
        Document doc = new Document(ROOTPATH + File.separator + "高速公路招标文件.docx");
        Range range = doc.getRange();
        range.replace("关键词1",arg1,finds);
        range.replace("关键词2",arg2,finds);
        range.replace("关键词3",arg3,finds);
        doc.save(NEWPATH + File.separator + "高速公路招标文件-模板.docx");
    }



    /**
     * 获取段落编号
     * @throws Exception
     */
    public static void docxTest() throws Exception {
        String file = ROOTPATH + File.separator + "高速公路招标文件.docx";
//        String file = NEWPATH + File.separator + "CreateSimpleTable_out.doc";
        Document doc = new Document(file);
        SectionCollection sections = doc.getSections();
        for(Section section:sections){
            ParagraphCollection paragraphs = section.getBody().getParagraphs();
            for(Paragraph paragraph:paragraphs){
                paragraph.getParagraphFormat().setLineSpacing(18);//设置1.5倍行距
                System.out.println(paragraph.getText());
                //是否有分隔符
//                boolean separator = paragraph.getBreakIsStyleSeparator();
//                System.out.println(separator);
//                System.out.println("--------------------");
                //获取制表符
//                TabStop[] tabStops = paragraph.getEffectiveTabStops();
//                List<TabStop> collect = Stream.of(tabStops).collect(Collectors.toList());
//                System.out.println(collect);
//                System.out.println(collect.get(0));
//                System.out.println("--------------------");
                //样式
//                FrameFormat frame = paragraph.getFrameFormat();
//                System.out.println(frame);
//                System.out.println("--------------------");
                //格式列表
//                ListFormat formats = paragraph.getListFormat();
//                List list = formats.getList();
//                if(list != null){
//                    ListLevelCollection listLevels = list.getListLevels();
//                    for(ListLevel level:listLevels){
//                        System.out.println(level.toString());
////                        System.out.println(run.getText());
//                     }
////                    Style style = list.getStyle();
////                    System.out.println(style);
//                }
//                ListLevel level = formats.getListLevel();
//                System.out.println(level);
//                System.out.println("--------------------");
//                //标签列表
//                ListLabel labels = paragraph.getListLabel();
//                System.out.println(labels);
//                System.out.println("--------------------");
//                //节点类型
//                int nodes = paragraph.getNodeType();
//                System.out.println(nodes);
//                System.out.println("--------------------");
//                //run
//                RunCollection runs = paragraph.getRuns();
//                for(Run run:runs){
//                    System.out.println(run.getNodeType());
//                    System.out.println(run.getText());
//                }
//                System.out.println("--------------------");
//                //范围
//                Range range = paragraph.getRange();
//                String text = range.getText();
//                FieldCollection fields = range.getFields();
//                for(Field field:fields){
//                    System.out.println(field.getDisplayResult());
//                    System.out.println(field.getEnd());
//                    System.out.println(field.getFieldCode());
//                    System.out.println(field.getResult());
//                }
//                System.out.println("--------------------");
//                FormFieldCollection formFields = range.getFormFields();
//                for(FormField formField:formFields){
//                    System.out.println(formField.getName());
//                    System.out.println(formField.getResult());
//                    System.out.println(formField.getEntryMacro());
//                    System.out.println(formField.getExitMacro());
//                    System.out.println(formField.getHelpText());
//                }
//                System.out.println("--------------------");
            }
        }
        String dataDir = NEWPATH + File.separator + "linspace.doc";
        // 将文档保存到磁盘。
        doc.save(dataDir);
    }

    /**
     * 创建表格
     */
    public static void tableOpt() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        // 我们称这种方法开始构建表.
        builder.startTable();
        builder.insertCell();
        builder.write("Row 1, Cell 1 Content.");
        //构建第二个单元格
        builder.insertCell();
        builder.write("Row 1, Cell 2 Content.");
        //调用以下方法结束行并开始新行。
        builder.endRow();

        //构建第二行的第一个单元格。
        builder.insertCell();
        builder.write("Row 2, Cell 1 Content");

        //构建第二个单元格。
        builder.insertCell();
        builder.write("Row 2, Cell 2 Content.");
        builder.endRow();

        //表示我们已经完成了构建表的信号。
        builder.endTable();

        String dataDir = NEWPATH + File.separator + "CreateSimpleTable_out.doc";
        // 将文档保存到磁盘。
        doc.save(dataDir);
    }

    /**
     * 获取表格中单元格的内容
     *（1）NodeType.Table，5，节点类型为表格。
     *（2）NodeType.Row ，6，节点类型为行。
     *（3）NodeType.Cell ，7，节点类型为单元格。
     */
    public static void getTableValue() throws Exception {
        int tableIndex=0;//表格索引，第一个表格
        int rowIndex=0;//行索引，第一行
        int colIndext=0;//列索引，第一列
        String file = NEWPATH + File.separator + "CreateSimpleTable_out.doc";
        Document doc = new Document(file);
        Table table = (Table) doc.getChild(NodeType.TABLE, tableIndex, true);
        RowCollection rows = table.getRows();
        for(Row row:rows){
            CellCollection cells = row.getCells();
            for(Cell cell:cells){
                ParagraphCollection paragraphs = cell.getParagraphs();
                for(Paragraph paragraph:paragraphs){
                    RunCollection runs = paragraph.getRuns();
                    for(Run run:runs){
                        run.getFont().setName("宋体");
                    }
//                    System.out.println(paragraph.getText());
                }
//                String cellContent = cell.getText().replace("\r","").replace("\n","");
//                System.out.println(cellContent);
            }
        }
        String dataDir = NEWPATH + File.separator + "CreateSimpleTable_out1.doc";
        // 将文档保存到磁盘。
        doc.save(dataDir);
    }

}


