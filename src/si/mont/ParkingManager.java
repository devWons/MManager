package si.mont;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParkingManager {
	private static final String CONNECT_URL_PARKINGMANAGER_INTRO = "http://ygsquare1.iptime.org/index.php/login";
	private static final String CONNECT_URL_PARKINGMANAGER_MAIN = "http://ygsquare1.iptime.org/main";
	private static final String CONNECT_URL_PARKINGMANAGER_LOGIN = "http://ygsquare1.iptime.org/login/doLogin/";

	public static String getCurrentData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	public static void discountCar(ArrayList<String> carNumList, Map<String, String> map)
	{
		String ciSession = map.get("ci_session");
		if(carNumList.size() == 1)
		{
//			
//			Response disCar = Jsoup.connect("http://ygsquare1.iptime.org/index.php/main/ajax_DisIns/")
//					.data("inCarNumber", carNumList.get(0))
//					.data("TKNo", "0011019041115324651901")
//					.data("InTime", "2019-04-11 15:32:46")
//					.data("Reserve4", "")
//					.data("TimeType", "")
//					.data("chkedVal", "47:3시간할인")
//					.data("is_ajax", "1")
//					.cookies("")
//					.header("content-type", "application/json;charset=UTF-8")
//					.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//					.header("accept-encoding", "gzip, deflate, br")
//					.header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
//					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
//					.method(Connection.Method.POST)
//					.requestBody("JSON")
//					.ignoreContentType(true)
//					.timeout(5000)
//					.execute();
//			
//			Document disDoc = disCar.parse();
//			System.out.println("----------------disDoc------------------");
//			System.out.println(disDoc);
//			System.out.println("----------------//disDoc------------------");
		}
		
	}

	public static void main(String[] args) throws Exception {
		try {
			Response loginForm = Jsoup.connect("http://ygsquare1.iptime.org/index.php/login")
					.method(Connection.Method.GET)
					.execute();

			System.out.println("최초 쿠키값 : "+ loginForm.cookies());
			
			Response loginPage = Jsoup.connect("http://ygsquare1.iptime.org/login/doLogin/")
					.data("login_id", "yk306")
					.data("login_pw", "1")
					.data("login_ip", InetAddress.getLocalHost().getHostAddress())
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
			
			System.out.println("로그인한 후 쿠키값 : "+ loginPage.cookies());
			
			Map<String, String> cookies = loginPage.cookies();
			
			Connection.Response evaluationPage = Jsoup.connect("http://ygsquare1.iptime.org/index.php/main/ajax_CarList/")
					.cookies(cookies)
					.data("is_ajax", "1")
//					.data("carNumber", "53")
					.method(Connection.Method.POST)
					.timeout(5000)
					.execute();

			Document doc = evaluationPage.parse();
			
//			System.out.println("----------------doc------------------");
//			System.out.println(doc);
//			System.out.println("----------------//doc------------------");
			
			ArrayList<String> carNumList = new ArrayList<String>();
			
			for(Element elFont : doc.select("font"))
			{
//				System.out.println(elFont.text());
				carNumList.add(elFont.text());
			}
			System.out.println("----------------doc------------------");
			System.out.println(carNumList);
			
//			 <a onclick="fnCarInfo('26모5466', '2019-04-26 19:54:34', '0 시간 14 분 ', '0011019042619543490101');"><font color="white">26모5466</font></a> 
//			  <a onclick="fnCarInfo('20마2089', '2019-04-26 19:55:40', '0 시간 13 분 ', '0011019042619554113501');"><font color="white">20마2089</font></a> 
//			  <a onclick="fnCarInfo('38가2225', '2019-04-26 20:07:17', '0 시간 1 분 ', '0011019042620071733801');"><font color="white">38가2225</font></a> 			
//			discountCar(carNumList, loginForm.cookies());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
