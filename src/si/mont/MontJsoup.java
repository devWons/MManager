package si.mont;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MontJsoup {

	private static final Logger log = LoggerFactory.getLogger(MontJsoup.class);
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://ygsquare1.iptime.org/index.php/login")
					.data("login_id", "yk306")
					.data("login_pw", "1")
					.userAgent("Mozilla")
					.cookie("auth", "token").timeout(3000).post();
			
			
			String el = doc.html();
			
			log.debug(el);
			
		} catch (Exception e) {
			log.debug("e : "+ e.getMessage());
		}

	}

}
