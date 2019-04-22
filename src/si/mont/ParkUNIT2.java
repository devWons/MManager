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
		
	}

}
