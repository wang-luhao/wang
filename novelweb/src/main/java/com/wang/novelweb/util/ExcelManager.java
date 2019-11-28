package com.wang.novelweb.util;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelManager {

	// EXCEL 导出
	public static void generateExcel(HttpServletResponse response, String fileName, String sheetName, String title,
                                     String[] headers, int[] headersLen, List<List<Object>> listCon) {
		System.out.println("EXCEL 导出  generateExcel");
		try {
			// 创建工作薄
			XSSFWorkbook workbook = new XSSFWorkbook();
			// 创建表单
			XSSFSheet sheet = genSheet(workbook, sheetName);
			// 创建表单样式
			XSSFCellStyle titleStyle = genTitleStyle(workbook);// 创建标题样式
			XSSFCellStyle headerStyle = genHeaderStyle(workbook);
			XSSFCellStyle contextStyle = genContextStyle(workbook);// 创建文本样式
			// 创建Excel
			genExcel(sheet, titleStyle, headerStyle, contextStyle, title, headers, headersLen, listCon);
			// excel输出
			fileName += ".xlsx";
			// 响应到客户端浏览器
			setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			workbook.write(os);
			os.flush();
			os.close();

			/*
			 * //将生成的Excel文件保存到本地 
			 * FileOutputStream out = new FileOutputStream(new File("D://files/"+fileName)); //将工作薄写入文件输出流中
			 * workbook.write(out); 
			 * //文本文件输出流，释放资源 
			 * out.close();
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 发送响应流方法
	private static void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void genExcel(XSSFSheet sheet, XSSFCellStyle titleStyle, XSSFCellStyle headerStyle, XSSFCellStyle contextStyle,
								 String title, String[] headers, int[] headersLen, List<List<Object>> listCon) {
		System.out.println("EXCEL 导出  genExcel");
		try {
			int len = headers.length;
			// 根据headers列名长度，指定列名宽度 Excel总共len列
			for (int i = 0; i < len; i++) {
				int width = headersLen[i];
				sheet.setColumnWidth(i, 4000 * width);
			}
			int rows = 0;
			// 创建第一行,index从0开始
			XSSFRow row = sheet.createRow(rows);
			XSSFCell cell;
			// 创建title
			if (!StringUtils.isEmpty(title)) {
				// 空 则没有标题
				// 设置标题位置
				sheet.addMergedRegion(new CellRangeAddress(0, // first row
						0, // last row
						0, // first column
						len // last column
				));
				cell = row.createCell(0);// 创建一列
				cell.setCellValue(title);// 标题
				cell.setCellStyle(titleStyle);// 设置标题样式
				rows++;
			}
			// 创建header
			row = sheet.createRow(rows);// 创建第rows+1行
			for (int i = 0; i < len; i++) {
				cell = row.createCell(i);// 创建第i行第一列
				cell.setCellValue(headers[i]);// 第i行第一列内容
				cell.setCellStyle(headerStyle);// 设置样式
			}
			rows++;
			// listCon数据填充到Excel
			for (List<Object> objects : listCon) {
				row = sheet.createRow(rows);// 创建第rows+1行
				for (int j = 0; j < objects.size(); j++) {
					cell = row.createCell(j);// 创建第rows+1行第j+1列
					cell.setCellValue(String.valueOf(objects.get(j)));// 第rows+1行第j+1列的值
					cell.setCellStyle(contextStyle);// 设置样式
				}
				rows++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 设置表单，并生成表单
	private static XSSFSheet genSheet(XSSFWorkbook workbook, String sheetName) {
		// 生成表单
		XSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置表单文本居中
		sheet.setHorizontallyCenter(true);
		sheet.setFitToPage(false);
		// 打印时在底部右边显示文本页信息
		Footer footer = sheet.getFooter();
		footer.setRight("Page " + HeaderFooter.numPages() + " Of " + HeaderFooter.page());
		// 打印时在头部右边显示Excel创建日期信息
		Header header = sheet.getHeader();
		header.setRight("Create Date " + HeaderFooter.date() + " " + HeaderFooter.time());
		// 设置打印方式
		XSSFPrintSetup ps = sheet.getPrintSetup();
		ps.setLandscape(true); // true：横向打印，false：竖向打印 ，因为列数较多，推荐在打印时横向打印
		ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); // 打印尺寸大小设置为A4纸大小
		return sheet;
	}

	// 创建文本样式
	private static XSSFCellStyle genContextStyle(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);// 文本水平居中显示
		style.setVerticalAlignment(VerticalAlignment.CENTER);// 文本竖直居中显示
		style.setWrapText(true);// 文本自动换行
		// 生成Excel表单，需要给文本添加边框样式和颜色
		/*
		 * CellStyle.BORDER_DOUBLE 双边线 CellStyle.BORDER_THIN 细边线
		 * CellStyle.BORDER_MEDIUM 中等边线 CellStyle.BORDER_DASHED 虚线边线
		 * CellStyle.BORDER_HAIR 小圆点虚线边线 CellStyle.BORDER_THICK 粗边线
		 */
		style.setBorderBottom(BorderStyle.THIN);// 设置文本边框
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		// set border color
		style.setTopBorderColor(new XSSFColor(Color.BLACK));// 设置文本边框颜色
		style.setBottomBorderColor(new XSSFColor(Color.BLACK));
		style.setLeftBorderColor(new XSSFColor(Color.BLACK));
		style.setRightBorderColor(new XSSFColor(Color.BLACK));
		return style;
	}
	
	// 生成header样式
	private static XSSFCellStyle genHeaderStyle(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setWrapText(true);
		// 居中，设置边框
		style.setBorderBottom(BorderStyle.THIN);// 设置文本边框
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		// set border color
		style.setTopBorderColor(new XSSFColor(Color.BLACK));// 设置文本边框颜色
		style.setBottomBorderColor(new XSSFColor(Color.BLACK));
		style.setLeftBorderColor(new XSSFColor(Color.BLACK));
		style.setRightBorderColor(new XSSFColor(Color.BLACK));
		// 设置标题文字样式
		XSSFFont headerFont = workbook.createFont();
		headerFont.setBold(true);// 加粗
		headerFont.setFontHeight((short) 11);// 文字尺寸
		headerFont.setFontHeightInPoints((short) 11);
		style.setFont(headerFont);
		return style;
	}

	// 生成title样式
	private static XSSFCellStyle genTitleStyle(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setWrapText(true);
		// 居中，没有边框，所以这里没有设置边框，设置标题文字样式
		XSSFFont titleFont = workbook.createFont();
		titleFont.setBold(true);// 加粗
		titleFont.setFontHeight((short) 15);// 文字尺寸
		titleFont.setFontHeightInPoints((short) 15);
		style.setFont(titleFont);
		return style;
	}

}