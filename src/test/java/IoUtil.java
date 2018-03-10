

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.junit.Test;




public class IoUtil {
	public static void conversionFile(String readfilepath, String writefilepath) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		long linenum =0;
		try {
			File file = new File(writefilepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			StringBuffer sb = new StringBuffer();
			reader = new BufferedReader(new FileReader(readfilepath));
			//ReversedLinesFileReader object = new ReversedLinesFileReader(file,4096,"UTF-8");
			
			String line = null;
			// 按行读取
			while ((line = reader.readLine()) != null) {
				// 先将日志用,分割
				String[] strings = line.split(";");
				// 分割后取第四个元素得到[27/Dec/2017:16:10:32 +0800]
				String calldatestr = strings[3];
				// 去除第一个[
				String str2 = calldatestr.substring(1, calldatestr.length());
				// 空格分割,取第一个得到27/Dec/2017:16:10:32
				String[] datestr = str2.split(" ");
				String calldatestring = datestr[0];
				// 将27/Dec/2017:16:10:32转换格式
				Date calldate = new Date();
				String newdatestr = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					calldate = sdf.parse(calldatestring);
					// 转换得到
					newdatestr = sdfnew.format(calldate);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 获取第一个数字，并加1

				sb.append(strings[0]).append(";").append(strings[1]).append(";").append(strings[2]).append(";")
						.append("\"").append(newdatestr).append("\"").append(";").append(strings[4]).append("\r\n");
				linenum++;
			}
			// 写入新的文件
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	public static void conversionFileLastLine(String readfilepath, String writefilepath,long lines) throws IOException{
		  
        File writefile = new File(writefilepath);  
        File readfile = new File(readfilepath);
        int counter = 1;  
		StringBuffer sb = new StringBuffer();
        //构造方法 ReversedLinesFileReader(final File file, final int blockSize, final String encoding)   
        ReversedLinesFileReader object = new ReversedLinesFileReader(readfile,4096,"UTF-8");
		BufferedWriter writer = null;
		try {
	        while (counter <= lines) {  
		           String line = object.readLine();//读取下一行  
				// 先将日志用,分割
//		           if (null==line) {
//					line=object.readLine();
//				}
				String[] strings = line.split(";");
				// 分割后取第四个元素得到[27/Dec/2017:16:10:32 +0800]
				String calldatestr = strings[3];
				// 去除第一个[
				String str2 = calldatestr.substring(1, calldatestr.length());
				// 空格分割,取第一个得到27/Dec/2017:16:10:32
				String[] datestr = str2.split(" ");
				String calldatestring = datestr[0];
				// 将27/Dec/2017:16:10:32转换格式
				Date calldate = new Date();
				String newdatestr = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					calldate = sdf.parse(calldatestring);
					// 转换得到
					newdatestr = sdfnew.format(calldate);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 获取第一个数字，并加1

				sb.append(strings[0]).append(";").append(strings[1]).append(";").append(strings[2]).append(";")
						.append("\"").append(newdatestr).append("\"").append(";").append(strings[4]).append("\r\n");
	            counter++;  
	        }  
	        try {
		        writer = new BufferedWriter(new FileWriter(writefile));
				writer.write(sb.toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if (object != null) {
				try {
					object.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        object.close();
	}

	public static void main(String[] args) throws IOException, ParseException {

		//conversionFileLastLine("F:/test.txt", "F:/newtest.txt",10);
		String datestr = "2018-03-01 00:00:00";
		SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =sdfnew.parse(datestr);
		long start1 = System.currentTimeMillis();
		System.out.println("第一种转换开始时间 "+start1 );
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long datelong =calendar.getTimeInMillis();
		long endat1 = System.currentTimeMillis();
		System.out.println("第一种转换结束时间 "+endat1 );
		long time1 = endat1 - start1;
		System.out.println("第一种转换耗时 "+time1 );
		long start2 = System.currentTimeMillis();
		System.out.println("第二种转换开始时间 "+start2 );
		long datelong2 = date.getTime();
		long endat2 = System.currentTimeMillis();
		System.out.println("第二种转换结束时间 "+endat2 );
		long time2 = endat2-start2;
		System.out.println("第二种转换耗时 "+time2 );
	}

	public static int countLines(String filename) throws IOException {    
	    LineNumberReader reader  = new LineNumberReader(new FileReader(filename));    
	int cnt = 0;    
	String lineRead = "";    
	while ((lineRead = reader.readLine()) != null) {}    
	    
	cnt = reader.getLineNumber();     
	reader.close();    
	return cnt;    
	}  
}
