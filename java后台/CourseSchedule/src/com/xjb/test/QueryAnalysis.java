package com.xjb.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.xjb.model.Course;
import com.xjb.service.CourseService;

public class QueryAnalysis {
	private static List<Course> courses=new ArrayList<Course>();
	
    public static void main(String[] args) throws Exception {
    	for(int t=0;t<19;t++)
    	{
    		
    		File file = new File("F:\\做着玩\\实验室上机表\\上机表\\第"+(t+1)+"周上机表.xls");
	       String[][] result = getData(file, 3);
	       int rowLength = result.length;
	       for(int i=0;i<rowLength;i++) {
	           for(int j=0;j<result[i].length;j++) {
	        	  //Course course=new Course();
	        	  
	              System.out.print(result[i][j]+"\t\t");
	           }
	           System.out.println();
	       }
	       CourseService courseService=new CourseService();
	       
	       for (Course course : courses) {
			System.out.println(course);
			courseService.InsertCourse(course);
		}
				
	}
    	  

    }
    /**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * @param file 读取数据的源Excel
     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(File file, int ignoreRows)
           throws FileNotFoundException, IOException {
       List<String[]> result = new ArrayList<String[]>();
       //总行数
       int rowSize = 0;
       BufferedInputStream in = new BufferedInputStream(new FileInputStream(
              file));
       // 打开HSSFWorkbook
       POIFSFileSystem fs = new POIFSFileSystem(in);
       HSSFWorkbook wb = new HSSFWorkbook(fs);
       HSSFCell cell = null;
       //遍历表格
       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
    	   //表格行数
           HSSFSheet st = wb.getSheetAt(sheetIndex);
           // 从指定给位置遍历
           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
        	   //指向此行
              HSSFRow row = st.getRow(rowIndex);
              //如果无数据则跳过
              if (row == null) {
                  continue;
              }
              //记录总行数
              int tempRowSize = row.getLastCellNum() + 1;
              if (tempRowSize > rowSize) {
                  rowSize = tempRowSize;
              }
              //临时变量 存储此行数据
              String[] values = new String[rowSize];
              //初始化数组
              Arrays.fill(values, "");
              //是否有值的标志
              boolean hasValue = false;
              //遍历此行
              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
            	  //初始化数据
                  String value = "";
                  //得到此列指针
                  cell = row.getCell(columnIndex);
                  //判断
                  if (cell != null) {
                     // 注意：一定要设成这个，否则可能会出现乱码
                     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                     //得到此列数据类型
                     switch (cell.getCellType()) {
                     //String
                     case HSSFCell.CELL_TYPE_STRING:
                         value = cell.getStringCellValue();
                         break;
                     //数字
                     case HSSFCell.CELL_TYPE_NUMERIC:
                         if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd")
                                       .format(date);
                            } else {
                                value = "";
                            }
                         } else {
                            value = new DecimalFormat("0").format(cell
                                   .getNumericCellValue());
                         }
                         break;
                     //生成的数据
                     case HSSFCell.CELL_TYPE_FORMULA:
                         // 导入时如果为公式生成的数据则无值
                         if (!cell.getStringCellValue().equals("")) {
                            value = cell.getStringCellValue();
                         } else {
                            value = cell.getNumericCellValue() + "";
                         }
                         break;
                         //空白
                     case HSSFCell.CELL_TYPE_BLANK:
                         break;
                         //错误
                     case HSSFCell.CELL_TYPE_ERROR:
                         value = "";
                         break;
                         //布尔值
                     case HSSFCell.CELL_TYPE_BOOLEAN:
                         value = (cell.getBooleanCellValue() == true ? "Y"
                                : "N");
                         break;
                         //其余
                     default:
                         value = "";
                     }
                  }
                  //
                  if (columnIndex == 0 && value.trim().equals("")) {
                     break;
                  }
                  //去掉字符右边的空格
                  values[columnIndex] = rightTrim(value);
                  //存在有值
                  hasValue = true;
              }
              //有值，放入数组
              if (hasValue) {
                  result.add(values);
              }
           }
       }
       //关闭流
       in.close();
       //定义和表格相同的二维数组
       String[][] returnArray = new String[result.size()][rowSize];
       //
       for (int i = 0; i < returnArray.length; i++) {
           returnArray[i] = (String[]) result.get(i);
       }
       
       courses=ListToCourse(result);
       
       return returnArray;
    }

    private static List<Course> ListToCourse(List<String[]> result) {
		// TODO Auto-generated method stub
    	List<Course> courses=new ArrayList<Course>();
    	for (String[] strings : result) {
    		Course course=new Course();
    		course.setWeekno(strings[0]);
    		course.setWeek(strings[1]);
    		course.setSection(strings[2]);
    		course.setCourseName(strings[3]);
    		course.setClasses(strings[4]);
    		course.setNum(strings[5]);
    		course.setTeacher(strings[6]);
    		course.setAddress(strings[7]);
    		course.setCourseType(strings[8]);
    		courses.add(course);

    	}
    	
		return courses;
	}
	/**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
     public static String rightTrim(String str) {
       if (str == null) {
           return "";
       }
       int length = str.length();
       for (int i = length - 1; i >= 0; i--) {
           if (str.charAt(i) != 0x20) {
              break;
           }
           length--;
       }
       return str.substring(0, length);
    }
}