package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsUtil {
	public static void writeAdd(String filepath,List<List<String>>strs) throws IOException{
		String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
		FileInputStream fis;
		try {
			fis=new FileInputStream(filepath);
		} catch (FileNotFoundException e1) {
			throw new IllegalArgumentException("文件不存在");
		}
		Workbook wb = null;
		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook(fis);
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook(fis);
		} else {
			fis.close();
			throw new IllegalArgumentException("您的文档格式不正确!");
		}
		Sheet sheet1 = (Sheet) wb.getSheetAt(0);
//		Sheet sheet1 = (Sheet) wb.getSheet("sheet1");
		if(sheet1==null){
			try {
				wb.close();
			} catch (IOException e) {
			}
			throw new IllegalArgumentException("不存在sheet");
		}
		int startRow=sheet1.getLastRowNum()+1;
		for (int i = 0; i < strs.size(); i++) {
			Row row = (Row) sheet1.createRow(i+startRow);
			List<String> l=strs.get(i);
			for (int j = 0; j < l.size(); j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(l.get(j));
			}
		}
		try {
			OutputStream stream;
			stream = new FileOutputStream(filepath);
			wb.write(stream);
			stream.close();
			wb.close();
		} catch (Exception e) {
			new RuntimeException(e);
		}
	}
	public static void write(String filepath,List<String> titles,List<List<String>>strs){
		try {
			FileUtil.createFile(filepath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
		Workbook wb = null;
		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook();
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook();
		} else {
			throw new IllegalArgumentException("您的文档格式不正确!");
		}
		Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
		if(titles!=null){
			Row row=sheet1.createRow(0);
			for (int i = 0; i < titles.size(); i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(titles.get(i));
			}
		}
		int startRow=0;
		if(titles!=null) startRow=1;
		else startRow=0;
		if(strs!=null){
			for (int i = 0; i < strs.size(); i++) {
				Row row = (Row) sheet1.createRow(i+startRow);
				List<String> l=strs.get(i);
				for (int j = 0; j < l.size(); j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(l.get(j));
				}
			}
		}
		try {
			OutputStream stream;
			stream = new FileOutputStream(filepath);
			wb.write(stream);
			stream.close();
			wb.close();
		} catch (Exception e) {
			new RuntimeException(e);
		}
	}
	public static void write(String filepath,String[] titles,String[][]strs){
		String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
		Workbook wb = null;
		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook();
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook();
		} else {
			throw new IllegalArgumentException("您的文档格式不正确!");
		}
		Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
		if(titles!=null){
			Row row=sheet1.createRow(0);
			for (int i = 0; i < titles.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(titles[i]);
			}
		}
		int startRow=0;
		if(titles!=null) startRow=1;
		else startRow=0;
		for (int i = 0; i < strs.length; i++) {
			Row row = (Row) sheet1.createRow(i+startRow);
			for (int j = 0; j < strs[i].length; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(strs[i][j]);
			}
		}
		try {
			OutputStream stream;
			stream = new FileOutputStream(filepath);
			wb.write(stream);
			stream.close();
			wb.close();
		} catch (Exception e) {
			new RuntimeException(e);
		}
	}
	public static boolean writeTest(String outPath) throws Exception {
		String fileType = outPath.substring(outPath.lastIndexOf(".") + 1, outPath.length());
		System.out.println(fileType);
		// 创建工作文档对象
		Workbook wb = null;
		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook();
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook();
		} else {
			System.out.println("您的文档格式不正确！");
			return false;
		}
		// 创建sheet对象
		Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
		// 循环写入行数据
		for (int i = 0; i < 5; i++) {
			Row row = (Row) sheet1.createRow(i);
			// 循环写入列数据
			for (int j = 0; j < 8; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue("测试" + j);
			}
		}
		// 创建文件流
		OutputStream stream = new FileOutputStream(outPath);
		// 写入数据
		wb.write(stream);
		// 关闭文件流
		stream.close();
		wb.close();
		return true;
	}
	public static void readTest(String filePath) throws IOException {
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
		InputStream stream = new FileInputStream(filePath);
		Workbook wb = null;
		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook(stream);
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook(stream);
		} else {
			System.out.println("您输入的excel格式不正确");
		}
		Sheet sheet1 = wb.getSheetAt(0);
		for (Row row : sheet1) {
			for (Cell cell : row) {
				System.out.print(cell.getStringCellValue() + "  ");
			}
			System.out.println();
		}
	}

	

	public static void main(String[] args) {
		try {
			XlsUtil.read1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	public static void read1() throws Exception {
		String filePath="E:/定稿红毯.xlsx";
		File f=new File(filePath);
		InputStream  is=new FileInputStream(f);
		Workbook wb=new XSSFWorkbook(is);
		Sheet sheet=wb.getSheetAt(0);
//		Iterator iterator = sheet.iterator();
//		iterator.hasNext();
//		for (; iterator.hasNext();) {
//			Row row = (Row) iterator.next();
//			for (int i = 1; i < row.getFirstCellNum(); i++) {
//				System.out.print(row.getCell(i).getStringCellValue()+"  ");
//			}
//			System.out.println();
//		} 
		StringBuffer sb=new StringBuffer();
		for (int i = 1; i <= 69; i++) {
			sb.append("<li class=\"item-content row\"><div class=\"item-inner\">");
			Row row=sheet.getRow(i);
			sb.append("<div class=\"col-20\">"+String.valueOf(i)+"</div>");
			sb.append("<div class=\"col-20\">"+row.getCell(1).getStringCellValue()+"</div>");
			sb.append("<div class=\"col-20\">"+row.getCell(2).getStringCellValue()+"</div>");
			sb.append("<div class=\"col-20\">"+row.getCell(3).getStringCellValue()+"</div>");
			sb.append("</div></li>\n");
//			sb.append("<div class=\"row\">");
//			Row row=sheet.getRow(i);
//			sb.append("<div class=\"col-33\">"+row.getCell(1).getStringCellValue()+"</div>");
//			sb.append("<div class=\"col-33\">"+row.getCell(2).getStringCellValue()+"</div>");
//			sb.append("<div class=\"col-33\">"+row.getCell(3).getStringCellValue()+"</div>");
//			sb.append("</div>\n"); 
		}
		System.out.println(sb);
		wb.close(); 
	}
	public static void seat() throws Exception {

		String filePath="E:/座位表.xlsx";
		File f=new File(filePath);
		InputStream  is=new FileInputStream(f);
		Workbook wb=new XSSFWorkbook(is);
		Sheet sheet=wb.getSheetAt(0);
		StringBuffer sb=new StringBuffer();
		for (int i = 1; i <= 69; i++) {
			sb.append("<li class=\"item-content \"><div class=\"item-inner\">");
			Row row=sheet.getRow(i);
			sb.append("<div class=\"col-33\">"+row.getCell(1).getStringCellValue()+"</div>");
			sb.append("<div class=\"col-33\">"+row.getCell(2).getStringCellValue()+"</div>");
			sb.append("<div class=\"col-33\">"+row.getCell(3).getStringCellValue()+"</div>");
			sb.append("</div></li>\n");
		}
		System.out.println(sb);
		wb.close(); 
	
	}
}
