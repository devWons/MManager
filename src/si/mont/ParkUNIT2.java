package si.mont;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParkUNIT2 {

	public static void main(String[] args) throws IOException {
		String getCarInfo = "<a onclick=\"fnCarInfo('35소9688', '2018-04-25 15:45:51', '9265 시간 21 분 ', '0011018042515455182201');\"><font color=\"white\">35소9688</font></a>";
		
		int com1 = getCarInfo.indexOf("2018-04-25");
		String str = getCarInfo.substring(getCarInfo.indexOf("2018-04-25"), getCarInfo.indexOf("2018-04-25")+10);
		
		String str2 = getCarInfo.substring(getCarInfo.lastIndexOf("</font>")-7, getCarInfo.lastIndexOf("</font>"));

		String str3 = getCarInfo.substring(getCarInfo.indexOf("', '"), getCarInfo.indexOf("', '")+14);
		str3 = str.replaceAll("', '", "");
		
		String tmp = getCarInfo.substring(getCarInfo.indexOf("(")+1, getCarInfo.lastIndexOf(")"));
		String[] tmpArr = tmp.split(",");

		for(String s : tmpArr)
		{
			s = s.trim();
			s = s.replaceAll("'", "");
			System.out.println(s);
		}
		
		/*
		String getCarInfo = "('14우1024', '2019-04-13 16:39:25', '2 시간 5 분 ', '0011019041316392568301')";
		
		getCarInfo = getCarInfo.replaceAll("[(]", "");
		getCarInfo = getCarInfo.replaceAll("[)]", "");
		
		getCarInfo = getCarInfo.replaceAll(" ", "");
		getCarInfo = getCarInfo.replaceAll("\\p{Z}", "");


		String[] tmp = getCarInfo.split(",");
		
		for(String str : tmp)
		{
			System.out.println(str);
		}
		*/
	}

}
