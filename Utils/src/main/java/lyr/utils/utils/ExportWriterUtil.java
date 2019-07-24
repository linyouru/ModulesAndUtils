package lyr.utils.utils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExportWriterUtil {

	/**
	 * 普通导出
	 * @param head 每个map包含两个entry 第一个entry key "name"，value "新列名"
	 * 	           第二个entry key "id"，value "content集合里的列名"
	 * @param content 表格数据
	 * @param os 输出流 写出文件格式xlsx
	 * @Author LinYouRu
	 * @Date 11:01 2019/7/24
	 * @return void
	 **/
	public static void exportExcel(List<Map<String, Object>> head, List<Map<String, Object>> content, OutputStream os) {

		@SuppressWarnings("resource")
        SXSSFWorkbook workbook = new SXSSFWorkbook(10000);// 创建一个Excel文件

		SXSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet

		CellStyle style = workbook.createCellStyle();
		style.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
		writeSheet(sheet, style, head, content);
		try {
			workbook.write(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/***
	 * 写入一个sheet
	 * @param sheet
	 * @param style
	 * @param head
	 * @param content
	 */
	private static void writeSheet(SXSSFSheet sheet, CellStyle style, List<Map<String, Object>> head,
								  List<Map<String, Object>> content) {
		SXSSFRow titleRow = sheet.createRow(0);

		titleRow.setRowStyle(style);
		if (content.size() > 0) {
			if (head == null) {
				Map<String, Object> map = content.get(0); // 遍历 jsonarray

				Set<String> entrySet = map.keySet();
				int i = 0;
				for (String key : entrySet) {
					titleRow.createCell(i++).setCellValue(key + "");
				}
				for (i = 0; i < content.size(); i++) {
					SXSSFRow row = sheet.createRow(i + 1);
					int j = 0;

					for (String key : entrySet) {
						row.createCell(j++).setCellValue(content.get(i).get(key) + "");
					}
				}
			} else {
				// 传入表头
				int i = 0;
				for (Map<String, Object> map : head) {
					titleRow.createCell(i++).setCellValue(map.get("name") + "");
				}
				for (i = 0; i < content.size(); i++) {
					SXSSFRow row = sheet.createRow(i + 1);
					int j = 0;
					Map<String, Object> one = content.get(i);
					for (Map<String, Object> map : head) {

						row.createCell(j++).setCellValue(one.get(map.get("id") + "") + "");
					}
				}
			}
		}

	}

	/************ 导出CSV ******************/
	/**
	 * @param
	 * @param list
	 * @param
	 */
	public void exportCSV(List<Map<String, Object>> head,List<Map<String, Object>> list, String outPutPath) {
		// BufferedWriter bw = new BufferedWriter(
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {

			csvFile = new File(outPutPath);
			if (!csvFile.exists()) {
				csvFile.createNewFile();
			}
			System.out.println("csvFile：" + csvFile);
			// UTF-8使正确读取分隔符","
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"),
					1024);
			// 写入头部
			if (list.size() > 0) {
				
				//增加有表头
				if(head!=null)
				{
					//传入表头
					for (Map<String, Object> map : head) {
						csvFileOutputStream.write(map.get("name") + ",");
					}
					csvFileOutputStream.newLine();
					// 导入数据
					for (int i = 0; i < list.size(); i++) {
						for (Map<String, Object> map : head) {
							csvFileOutputStream.write(list.get(i).get(map.get("id")) + ",");
						}
						csvFileOutputStream.newLine();
					}
				}
				else{
					// 增加表头
					Map<String, Object> map = list.get(0); // 遍历 jsonarray

					Set<String> entrySet = map.keySet();
					for (String key : entrySet) {
						csvFileOutputStream.write(key + ",");
					}
					csvFileOutputStream.newLine();
					// 导入数据
					for (int i = 0; i < list.size(); i++) {
						for (String key : entrySet) {
							csvFileOutputStream.write(list.get(i).get(key) + ",");
						}
						csvFileOutputStream.newLine();
					}
				}
				csvFileOutputStream.flush();
			}
		} catch (Exception e) {
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
