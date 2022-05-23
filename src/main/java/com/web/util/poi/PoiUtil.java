//package com.web.util.poi;
//
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.util.Date;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.multipart.MultipartFile;
//import com.web.util.date.DateUtil;
//
///**
// * 字符串工具类
// * @author
// * @version v1.0
// */
//public class PoiUtil {
//
//	/**
//     * 根据文件后缀 new 不一样的Workbook
//     * @param
//     * @return
//     */
//	public static Workbook getNewWorkbook(String fileType,MultipartFile ps) {
//		try {
//			if (".xls".equals(fileType)){
//				return new HSSFWorkbook(ps.getInputStream()) ;
//	        } else if (".xlsx".equals(fileType)) {
//	        	return new XSSFWorkbook(ps.getInputStream()) ;
//	        }
//        }
//        catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null ;
//    }
//
//
//	/**
//     * 根据Cell类型设置数据
//     * @param cell
//     * @return
//     */
////	public static String getCellFormatValue(Cell cell) {
////        String cellvalue = "";
////        if (cell != null) {
////            // 判断当前Cell的Type
////            switch (cell.getCellType()) {
////            // 如果当前Cell的Type为NUMERIC
////				case NUMERIC:
////            	Object inputVal ;
////            	long longVal = Math.round(cell.getNumericCellValue()) ;
////            	Double doubleVal = cell.getNumericCellValue() ;
////            	if (Double.parseDouble(longVal+".0") == doubleVal){
////            		inputVal = longVal ;
////            	}else{
////            		inputVal = doubleVal ;
////            	}
////            	DecimalFormat df = new DecimalFormat("#.##") ;
////            	cellvalue =  String.valueOf(df.format(inputVal)) ;
////            	break ;
////				case BOOLEAN:
////            	boolean value = cell.getBooleanCellValue();
////            	if(value) {
////            		cellvalue = "1";
////            	}else {
////            		cellvalue = "0";
////            	}
////            	break;
////				case FORMULA: {
////                // 判断当前的cell是否为Date
////                if (HSSFDateUtil.isCellDateFormatted(cell)) {
////                    // 如果是Date类型则，转化为Data格式
////
////                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
////                    //cellvalue = cell.getDateCellValue().toLocaleString();
////
////                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
////                    Date date = cell.getDateCellValue();
////                    cellvalue = DateUtil.formatDate(date, DateUtil.HYPHEN_DATE) ;
////                }
////                // 如果是纯数字
////                else {
////                    // 取得当前Cell的数值
////                    cellvalue = String.valueOf(cell.getNumericCellValue());
////                }
////                break;
////            }
////            // 如果当前Cell的Type为STRING
////				case STRING:
////                // 取得当前的Cell字符串
////                cellvalue = cell.getRichStringCellValue().getString();
////                break;
////            // 默认的Cell值
////            default:
////                cellvalue = "";
////            }
////        } else {
////            cellvalue = "";
////        }
////        return cellvalue;
////    }
//	public static String getCellFormatValue(Cell cell) {
//		String cellvalue = "";
//		if (cell != null) {
//			// 判断当前Cell的Type
//			switch (cell.getCellType()) {
//				// 如果当前Cell的Type为NUMERIC
//				case HSSFCell.CELL_TYPE_NUMERIC:
//					Object inputVal ;
//					long longVal = Math.round(cell.getNumericCellValue()) ;
//					Double doubleVal = cell.getNumericCellValue() ;
//					if (Double.parseDouble(longVal+".0") == doubleVal){
//						inputVal = longVal ;
//					}else{
//						inputVal = doubleVal ;
//					}
//					DecimalFormat df = new DecimalFormat("#.##") ;
//					cellvalue =  String.valueOf(df.format(inputVal)) ;
//					break ;
//				case HSSFCell.CELL_TYPE_BOOLEAN:
//					boolean value = cell.getBooleanCellValue();
//					if(value) {
//						cellvalue = "1";
//					}else {
//						cellvalue = "0";
//					}
//					break;
//				case HSSFCell.CELL_TYPE_FORMULA: {
//					// 判断当前的cell是否为Date
//					if (HSSFDateUtil.isCellDateFormatted(cell)) {
//						// 如果是Date类型则，转化为Data格式
//
//						//方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
//						//cellvalue = cell.getDateCellValue().toLocaleString();
//
//						//方法2：这样子的data格式是不带带时分秒的：2011-10-12
//						Date date = cell.getDateCellValue();
//						cellvalue = DateUtil.formatDate(date, DateUtil.HYPHEN_DATE) ;
//					}
//					// 如果是纯数字
//					else {
//						// 取得当前Cell的数值
//						cellvalue = String.valueOf(cell.getNumericCellValue());
//					}
//					break;
//				}
//				// 如果当前Cell的Type为STRIN
//				case HSSFCell.CELL_TYPE_STRING:
//					// 取得当前的Cell字符串
//					cellvalue = cell.getRichStringCellValue().getString();
//					break;
//				// 默认的Cell值
//				default:
//					cellvalue = "";
//			}
//		} else {
//			cellvalue = "";
//		}
//		return cellvalue;
//	}
//}
