package si.mont;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParkUNIT {

	public static void main(String[] args) throws IOException {
		try {
			
			Response loginForm = Jsoup.connect("http://ygsquare1.iptime.org/index.php/login")
					.method(Connection.Method.GET)
					.execute();
			
			System.out.println("최초 쿠키값 : "+ loginForm.cookies());
			
			Connection.Response evaluationPage = Jsoup.connect("http://ygsquare1.iptime.org/index.php/main/ajax_CarList/")
					.cookies(loginForm.cookies())
					.data("is_ajax", "1")
					.data("carNumber", "14우1024")
					.method(Connection.Method.POST)
					.timeout(5000)
					.execute();

			Document doc = evaluationPage.parse();
			
			System.out.println("----------------doc------------------");
			System.out.println(doc);
			System.out.println("----------------//doc------------------");
			
			ArrayList<String> carNumList = new ArrayList<String>();
			
			for(Element elFont : doc.select("font"))
			{
//				System.out.println(elFont.text());
				carNumList.add(elFont.text());
			}
			
			
			Response disCar = Jsoup.connect("http://ygsquare1.iptime.org/index.php/main/ajax_DisIns/")
					.data("inCarNumber", "14우1024")
					.data("TKNo", "0011019041316392568301")
					.data("InTime", "2019-04-1316:39:25")
					.data("Reserve4", "")
					.data("TimeType", "")
					.data("chkedVal", "47:3시간할인")
					.data("is_ajax", "1")
					.cookies(loginForm.cookies())
					.header("content-type", "application/json;charset=UTF-8")
					.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("accept-encoding", "gzip, deflate, br")
					.header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
					.method(Connection.Method.POST)
					.requestBody("JSON")
					.ignoreContentType(true)
					.timeout(5000)
					.execute();
			
			Document disDoc = disCar.parse();
			System.out.println("----------------disDoc------------------");
			System.out.println(disDoc);
			System.out.println("----------------//disDoc------------------");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
