package com.web.HanLPModule.util;

import com.aspose.pdf.*;
import com.aspose.pdf.devices.JpegDevice;
import com.aspose.pdf.devices.PngDevice;

import com.aspose.pdf.devices.Resolution;
import com.aspose.pdf.internal.html.drawing.UnitType;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AsposePdfUtil {

    public static void main(String[] args) {
//        String inPath = "D:\\soft\\files\\in\\【田海娇】后疫情时代企业员工加班现状-20221203.pdf";
        String inPath = "D:\\soft\\files\\in\\Office安装教程.pdf";
        String outPath = "D:\\soft\\files\\out\\img3";
        try {
//            pdf2Doc(inPath,outPath);
//            pdfToDoc(inPath,outPath);
//            addWatermark(inPath,outPath,"测试水印");
            List<BufferedImage> bufferedImages = pdfToImage(inPath, outPath);
            BufferedImage bufferedImage = mergeImage(false, bufferedImages);
            File outputfile = new File("D:\\soft\\files\\out\\image.jpg");
            ImageIO.write(bufferedImage, "jpg", outputfile);
            System.out.println("ok");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * aspose方法
     * @param inPath
     * @param outPath
     * @throws IOException
     */
    public static void pdf2Doc(String inPath,String outPath) throws IOException {
        long start = System.currentTimeMillis();
        File pdfFile = new File(outPath);
        Document document = new Document(inPath);
        FileOutputStream outputStream = new FileOutputStream(pdfFile);
        document.save(outputStream, SaveFormat.DocX);
        outputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("PDF转化WORD共耗时：" + (end - start) + "ms"); // 转化用时
    }

    /**
     * 调用acrobat
     * @param in
     * @param out
     */
    public static void pdfToDoc(String in,String out) {
        File inPath = new File(in);
        File outPath = new File(out);
        //pdfActiveX PDDoc对象 主要建立PDF对象
        ActiveXComponent app = new ActiveXComponent("AcroExch.PDDoc");
        //PDF控制对象
        Dispatch pdfObject = app.getObject();
        long start = System.currentTimeMillis();
        //打开PDF文件，建立PDF操作的开始
        Dispatch.call(pdfObject, "Open", new Variant(inPath.getAbsolutePath()));
        Variant jsObj = Dispatch.call(pdfObject, "GetJSObject");
        Dispatch.call(jsObj.getDispatch(), "SaveAs", outPath.getPath(), "com.adobe.acrobat.docx");
        app.invoke("Close");
        long end = System.currentTimeMillis();
        System.out.println("PDF转化WORD共耗时：" + (end-start)/1000);
//        System.out.println("Wao-haha");
    }

    /**
     * 合并任数量的图片成一张图片
     *
     * @param isHorizontal true代表水平合并，fasle代表垂直合并
     * @param imgs         待合并的图片数组
     * @return
     * @throws IOException
     */
    public static BufferedImage mergeImage(boolean isHorizontal, List<BufferedImage> imgs) throws IOException {
        // 生成新图片
        BufferedImage destImage = null;
        // 计算新图片的长和高
        int allw = 0, allh = 0, allwMax = 0, allhMax = 0;
        // 获取总长、总宽、最长、最宽
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            allw += img.getWidth();

            if (imgs.size() != i + 1) {
                allh += img.getHeight() + 5;
            } else {
                allh += img.getHeight();
            }


            if (img.getWidth() > allwMax) {
                allwMax = img.getWidth();
            }
            if (img.getHeight() > allhMax) {
                allhMax = img.getHeight();
            }
        }
        // 创建新图片
        if (isHorizontal) {
            destImage = new BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB);
        } else {
            destImage = new BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g2 = (Graphics2D) destImage.getGraphics();
        g2.setBackground(Color.LIGHT_GRAY);
        g2.clearRect(0, 0, allw, allh);
        g2.setPaint(Color.RED);

        // 合并所有子图片到新图片
        int wx = 0, wy = 0;
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            int w1 = img.getWidth();
            int h1 = img.getHeight();
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[w1 * h1];
            // 逐行扫描图像中各个像素的RGB到数组中
            ImageArrayOne = img.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1);
            if (isHorizontal) {
                // 水平方向合并
                // 设置上半部分或左半部分的RGB
                destImage.setRGB(wx, 0, w1, h1, ImageArrayOne, 0, w1);
            } else {
                // 垂直方向合并
                // 设置上半部分或左半部分的RGB
                destImage.setRGB(0, wy, w1, h1, ImageArrayOne, 0, w1);
            }
            wx += w1;
            wy += h1 + 5;
        }
        return destImage;
    }


    /**
     * @param sourceFile 需要重新重命名的图片路径
     * @param targetPath 重命名的图片路径保存地址
     */
    public static void fileCopyRightWay(String sourceFile, String targetPath) {
        try {
            //读取源地址文件的字节流
            FileInputStream in = new FileInputStream(sourceFile);
            FileOutputStream out = new FileOutputStream(targetPath);
            byte[] bs = new byte[1026];
            int count = 0;
            while ((count = in.read(bs, 0, bs.length)) != -1) {
                //把读取到的字节流写入到目的地址的文件里面
                out.write(bs, 0, count);
            }
            //刷新下输出流
            out.flush();
            // 关闭输入流和输出流
            out.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加水印
     *
     * @param filepath [文件路径]
     * @param data     [水印文字内容]
     */
    public static void addWatermark(String filepath,String outpath, String data) {
        long start = System.currentTimeMillis();
        Document pdfDocument = new Document(filepath);
        TextStamp textStamp = new TextStamp(data);
        textStamp.setBackground(true);
        textStamp.setXIndent(100);
        textStamp.setYIndent(100);
        textStamp.setRotateAngle(50);

        textStamp.getTextState().setFont(FontRepository.findFont("Arial"));
        textStamp.getTextState().setFontSize(34.0F);
        textStamp.getTextState().setFontStyle(FontStyles.Italic);
//        textStamp.getTextState().setForegroundColor(Color.getLightGray());

        TextStamp textStamp1 = new TextStamp(data);
        textStamp1.setBackground(true);
        textStamp1.setXIndent(300);//设置位置
        textStamp1.setYIndent(300);
        textStamp1.setRotateAngle(50);//设置旋转角度
        textStamp1.getTextState().setFont(FontRepository.findFont("Arial"));
        textStamp1.getTextState().setFontSize(34.0F);//设置字体大小
        textStamp1.getTextState().setFontStyle(FontStyles.Italic);
//        textStamp1.getTextState().setForegroundColor(Color.LIGHT_GRAY);//设置字体颜色

        TextStamp textStamp2 = new TextStamp(data);
        textStamp2.setBackground(true);
        textStamp2.setXIndent(500);
        textStamp2.setYIndent(500);
        textStamp2.setRotateAngle(50);
        textStamp2.getTextState().setFont(FontRepository.findFont("Arial"));
        textStamp2.getTextState().setFontSize(34.0F);
        textStamp2.getTextState().setFontStyle(FontStyles.Italic);
//        textStamp2.getTextState().setForegroundColor(Color.getLightGray());


        PageCollection pages = pdfDocument.getPages();
        int size = pages.size();
        for (int i = 1; i <= size; i++) {
            pages.get_Item(i).addStamp(textStamp);
            pages.get_Item(i).addStamp(textStamp1);
            pages.get_Item(i).addStamp(textStamp2);
        }
        pdfDocument.save(outpath);
        long end = System.currentTimeMillis();
        System.out.println("添加水印共耗时：" + (end-start)/1000);
    }


    public static void addWatermarkWYH(String filepath, String data) {
        Document pdfDocument = new Document(filepath);
        TextStamp textStamp = new TextStamp(data);
        //背景(好像没用)
        textStamp.setBackground(true);
        //x坐标  可以通过xy坐标来定义水印的具体位置
        textStamp.setXIndent(100);
        //y坐标
        textStamp.setYIndent(10);
        //通过以下属性控制水印的样式 字体 大小 颜色等
        //旋转角度
        textStamp.setRotateAngle(0);
        //字体类型
        textStamp.getTextState().setFont(FontRepository.findFont("Arial"));
        //字体大小
        textStamp.getTextState().setFontSize(20.0F);
        //字体样式
        textStamp.getTextState().setFontStyle(FontStyles.Italic);
        //字体颜色
        textStamp.getTextState().setForegroundColor(com.aspose.pdf.Color.fromRgb(Color.yellow));

        TextStamp textStamp1 = new TextStamp(data);
        textStamp1.setBackground(true);
        textStamp1.setXIndent(200);//设置位置
        textStamp1.setYIndent(10);
        textStamp1.setRotateAngle(0);//设置旋转角度
        textStamp1.getTextState().setFont(FontRepository.findFont("Arial"));
        textStamp1.getTextState().setFontSize(30.0F);//设置字体大小
        textStamp1.getTextState().setFontStyle(FontStyles.Italic);
        textStamp1.getTextState().setForegroundColor(com.aspose.pdf.Color.fromRgb(Color.green));


        TextStamp textStamp2 = new TextStamp(data);
        textStamp2.setBackground(true);
        textStamp2.setXIndent(300);
        textStamp2.setYIndent(10);
        textStamp2.setRotateAngle(0);
        textStamp2.getTextState().setFont(FontRepository.findFont("Arial"));
        textStamp2.getTextState().setFontSize(40.0F);
        textStamp2.getTextState().setFontStyle(FontStyles.Italic);
        textStamp2.getTextState().setForegroundColor(com.aspose.pdf.Color.fromRgb(Color.red));
        PageCollection pages = pdfDocument.getPages();
        System.out.println(pages);
        int size = pages.size();
        for (int i = 1; i <= size; i++) {
            //如果只想要一个水印 只添加一个即可
            pages.get_Item(i).addStamp(textStamp);
            pages.get_Item(i).addStamp(textStamp1);
            pages.get_Item(i).addStamp(textStamp2);
        }
        pdfDocument.save(filepath);
    }

    /**
     * pfd转图片
     * @param pdf 源文件全路径
     * @param outPath 转后的文件夹路径
     */
    public static List<BufferedImage> pdfToImage(String pdf, String outPath){
        List<BufferedImage> list = new ArrayList<>();
        try {
            long start = System.currentTimeMillis();
//            log.info("convert pdf2jpg begin");
            Document pdfDocument = new Document(pdf);
            //图片宽度：800
            //图片高度：100
            // 分辨率 960
            //Quality [0-100] 最大100
            //例： new JpegDevice(800, 1000, resolution, 90);
            Resolution resolution = new Resolution(960);
            JpegDevice jpegDevice = new JpegDevice(resolution);
//            JpegDevice jpegDevice = new JpegDevice(new Resolution());
            for (int index=1;index<=pdfDocument.getPages().size();index++) {
                // 输出路径
                File file = new File(outPath + File.separator +index+".jpg");
                FileOutputStream fileOs = new FileOutputStream(file);
                jpegDevice.process(pdfDocument.getPages().get_Item(index), fileOs);
                fileOs.close();
                BufferedImage image = ImageIO.read(file);
                list.add(image);
            }
            long end = System.currentTimeMillis();
//            log.info("convert pdf2jpg completed, elapsed ：" + ((now - old) / 1000.0) + "秒");
            System.out.println("PDF转化image共耗时：" + (end - start) + "ms"); // 转化用时
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("convert pdf2jpg error:"+e);
        }
        return list;
    }
}
