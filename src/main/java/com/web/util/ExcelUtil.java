package com.web.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 路径：com.example.demo.utils
 * 类名：
 * 功能：导入导出
 * 备注：
 * 创建人：typ
 * 创建时间：2018/10/19 11:21
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Slf4j
public class ExcelUtil {

//    /**
//     * 方法名：exportExcel
//     * 功能：导出Excel
//     * 描述：
//     * 创建人：typ
//     * 创建时间：2018/10/19 16:00
//     * 修改人：
//     * 修改描述：
//     * 修改时间：
//     */
//    public static void exportExcel(HttpServletResponse response, List<auth_user> list) {
//        log.info("导出解析开始，fileName:{}","test");
//        try {
//            //实例化HSSFWorkbook
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            //创建一个Excel表单，参数为sheet的名字
//            HSSFSheet sheet = workbook.createSheet("sheet");
//            //设置表头
//            String[] titles = new String[]{"姓名","审核标记","删除标记"};
//            setTitle(workbook, sheet, titles);
//            //设置单元格并赋值
//            setData(sheet, list);
//            //设置浏览器下载
//            setBrowser(response, workbook, data.getFileName());
//            log.info("导出解析成功!");
//        } catch (Exception e) {
//            log.info("导出解析失败!");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 方法名：setTitle
//     * 功能：设置表头
//     * 描述：
//     * 创建人：typ
//     * 创建时间：2018/10/19 10:20
//     * 修改人：
//     * 修改描述：
//     * 修改时间：
//     */
//    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
//        try {
//            HSSFRow row = sheet.createRow(0);
//            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
//            for (int i = 0; i <= str.length; i++) {
//                sheet.setColumnWidth(i, 15 * 256);
//            }
//            //设置为居中加粗,格式化时间格式
//            HSSFCellStyle style = workbook.createCellStyle();
//            HSSFFont font = workbook.createFont();
//            font.setBold(true);
//            style.setFont(font);
//            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
//            //创建表头名称
//            HSSFCell cell;
//            for (int j = 0; j < str.length; j++) {
//                cell = row.createCell(j);
//                cell.setCellValue(str[j]);
//                cell.setCellStyle(style);
//            }
//        } catch (Exception e) {
//            log.info("导出时设置表头失败！");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 方法名：setData
//     * 功能：表格赋值
//     * 描述：
//     * 创建人：typ
//     * 创建时间：2018/10/19 16:11
//     * 修改人：
//     * 修改描述：
//     * 修改时间：
//     */
//    private static void setData(HSSFSheet sheet, List<auth_user> list) {
//        try{
//            int rowNum = 1;
//            for (int i = 0; i < list.size(); i++) {
//                HSSFRow row = sheet.createRow(rowNum);
//                for (int j = 0; j < list.get(i).length; j++) {
//                    row.createCell(j).setCellValue(data.get(i)[j]);
//                }
//                rowNum++;
//            }
//            log.info("表格赋值成功！");
//        }catch (Exception e){
//            log.info("表格赋值失败！");
//            e.printStackTrace();
//        }
//    }

//    /**
//     * 方法名：importExcel
//     * 功能：导入
//     * 描述：
//     * 创建人：typ
//     * 创建时间：2018/10/19 11:45
//     * 修改人：
//     * 修改描述：
//     * 修改时间：
//     */
//    public static List<Map<String,Object>> importExcel(MultipartFile file, String[] params) {
////        log.info("导入解析开始，fileName:{}",fileName);
//        try {
//            List<Map<String,Object>> list = new ArrayList<>();
//            List<auth_user> users = new ArrayList<>();
//            InputStream inputStream = file.getInputStream();
//            Workbook workbook = WorkbookFactory.create(inputStream);
//            Sheet sheet = workbook.getSheetAt(0);
//            //获取sheet的行数
//            int rows = sheet.getPhysicalNumberOfRows();
//            if(sheet.getRow(0).getPhysicalNumberOfCells() != params.length){
//                throw new RuntimeException("error");
//            }
//
//            for (int i = 0; i < rows; i++) {
//                //过滤表头行
//                if (i == 0) {
//                    continue;
//                }
//                //获取当前行的数据
//                Row row = sheet.getRow(i);
//                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
//                int index = 0;
//
//                Map<String,Object> map = new LinkedHashMap<>();
//
//                for (Cell cell : row) {
//                    if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
////                        objects[index] = (int) cell.getNumericCellValue();
//                        map.put(params[i],(int) cell.getNumericCellValue());
//                    }
//                    if (cell.getCellType() == (HSSFCell.CELL_TYPE_STRING)) {
////                        objects[index] = cell.getStringCellValue();
//                        map.put(params[i],cell.getStringCellValue());
//                    }
//                    if (cell.getCellType() == (HSSFCell.CELL_TYPE_BOOLEAN)) {
////                        objects[index] = cell.getBooleanCellValue();
//                        map.put(params[i],cell.getBooleanCellValue());
//                    }
//                    if (cell.getCellType() == (HSSFCell.CELL_TYPE_ERROR)) {
////                        objects[index] = cell.getErrorCellValue();
//                        map.put(params[i],cell.getErrorCellValue());
//                    }
//                    index++;
//                }
//                list.add(map);
//            }
//
//            for(Map<String,Object> single:list){
//                auth_user user = JSONObject.parseObject(JSONObject.toJSONString(single), auth_user.class);
//                users.add(user);
//            }
//
//            System.out.println(users.toString());
//            log.info("导入文件解析成功！");
//            return list;
//        }catch (Exception e){
//            log.info("导入文件解析失败！");
//            e.printStackTrace();
//        }
//        return null;
//    }

    //测试导入
//    public static void main(String[] args) {
//        try {
//            String fileName = "f:/test.xlsx";
//            List<Object[]> list = importExcel(fileName);
//            for (int i = 0; i < list.size(); i++) {
//                User user = new User();
//                user.setId((Integer) list.get(i)[0]);
//                user.setUsername((String) list.get(i)[1]);
//                user.setPassword((String) list.get(i)[2]);
//                user.setEnable((Integer) list.get(i)[3]);
//                System.out.println(user.toString());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
