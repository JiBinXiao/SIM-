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
    		
    		File file = new File("F:\\������\\ʵ�����ϻ���\\�ϻ���\\��"+(t+1)+"���ϻ���.xls");
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
     * ��ȡExcel�����ݣ���һά����洢����һ���и��е�ֵ����ά����洢���Ƕ��ٸ���
     * @param file ��ȡ���ݵ�ԴExcel
     * @param ignoreRows ��ȡ���ݺ��Ե�������������ͷ����Ҫ���� ���Ե�����Ϊ1
     * @return ������Excel�����ݵ�����
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(File file, int ignoreRows)
           throws FileNotFoundException, IOException {
       List<String[]> result = new ArrayList<String[]>();
       //������
       int rowSize = 0;
       BufferedInputStream in = new BufferedInputStream(new FileInputStream(
              file));
       // ��HSSFWorkbook
       POIFSFileSystem fs = new POIFSFileSystem(in);
       HSSFWorkbook wb = new HSSFWorkbook(fs);
       HSSFCell cell = null;
       //�������
       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
    	   //�������
           HSSFSheet st = wb.getSheetAt(sheetIndex);
           // ��ָ����λ�ñ���
           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
        	   //ָ�����
              HSSFRow row = st.getRow(rowIndex);
              //���������������
              if (row == null) {
                  continue;
              }
              //��¼������
              int tempRowSize = row.getLastCellNum() + 1;
              if (tempRowSize > rowSize) {
                  rowSize = tempRowSize;
              }
              //��ʱ���� �洢��������
              String[] values = new String[rowSize];
              //��ʼ������
              Arrays.fill(values, "");
              //�Ƿ���ֵ�ı�־
              boolean hasValue = false;
              //��������
              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
            	  //��ʼ������
                  String value = "";
                  //�õ�����ָ��
                  cell = row.getCell(columnIndex);
                  //�ж�
                  if (cell != null) {
                     // ע�⣺һ��Ҫ��������������ܻ��������
                     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                     //�õ�������������
                     switch (cell.getCellType()) {
                     //String
                     case HSSFCell.CELL_TYPE_STRING:
                         value = cell.getStringCellValue();
                         break;
                     //����
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
                     //���ɵ�����
                     case HSSFCell.CELL_TYPE_FORMULA:
                         // ����ʱ���Ϊ��ʽ���ɵ���������ֵ
                         if (!cell.getStringCellValue().equals("")) {
                            value = cell.getStringCellValue();
                         } else {
                            value = cell.getNumericCellValue() + "";
                         }
                         break;
                         //�հ�
                     case HSSFCell.CELL_TYPE_BLANK:
                         break;
                         //����
                     case HSSFCell.CELL_TYPE_ERROR:
                         value = "";
                         break;
                         //����ֵ
                     case HSSFCell.CELL_TYPE_BOOLEAN:
                         value = (cell.getBooleanCellValue() == true ? "Y"
                                : "N");
                         break;
                         //����
                     default:
                         value = "";
                     }
                  }
                  //
                  if (columnIndex == 0 && value.trim().equals("")) {
                     break;
                  }
                  //ȥ���ַ��ұߵĿո�
                  values[columnIndex] = rightTrim(value);
                  //������ֵ
                  hasValue = true;
              }
              //��ֵ����������
              if (hasValue) {
                  result.add(values);
              }
           }
       }
       //�ر���
       in.close();
       //����ͱ����ͬ�Ķ�ά����
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
     * ȥ���ַ����ұߵĿո�
     * @param str Ҫ������ַ���
     * @return �������ַ���
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