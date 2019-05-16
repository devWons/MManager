package si.mont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParkUNIT {

	public static void main(String[] args) throws IOException {
		try {
			
			Response loginForm = Jsoup.connect("http://ygsquare1.iptime.org/index.php/login")
					.method(Connection.Method.GET)
					.execute();
			
			Connection.Response evaluationPage = Jsoup.connect("http://ygsquare1.iptime.org/index.php/main/ajax_CarList/")
					.cookies(loginForm.cookies())
					.data("is_ajax", "1")
//					.data("carNumber", "14우1024")
					.data("carNumber", "")
					.method(Connection.Method.POST)
					.timeout(5000)
					.execute();

			Document doc = evaluationPage.parse();
			
			ArrayList<String> carNumList = new ArrayList<String>();
			
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(dt);
			System.out.println("today : "+ today);
			
			String str = doc.select("a").outerHtml();
			
			BufferedReader reader = new BufferedReader(new StringReader(str));
			reader.readLine();

			String read = null;
			String lineInfo = null;
			String tmpToday = null;
			
			System.out.println("----------한줄씩읽기-----------------");
			while((read = reader.readLine()) != null){
				tmpToday = read.substring(read.indexOf("', '"), read.indexOf("', '")+14);
				tmpToday = tmpToday.replaceAll("', '", "");
				
				if(today.matches(tmpToday))
				{
					lineInfo = read.substring(read.indexOf(today), read.indexOf(today)+10);
					carNumList.add(read.substring(read.lastIndexOf("</font>")-7, read.lastIndexOf("</font>")));
					System.out.println(read);
				}
                lineInfo = null;
            }
			
			System.out.println("----------//한줄씩읽기-----------------");
			
			for(String s : carNumList)
			{
				System.out.println(s);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
