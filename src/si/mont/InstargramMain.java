package si.mont;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;

public class InstargramMain {
	public static final String CLIENT_ID = "e5f6da8c60054e368309bcb7453ade86";
	public static final String REDIRECT_URI = "http://skswodnjs02.cafe24.com/mblk/mblkMain.php";
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet("http://ygsquare1.iptime.org/index.php/login");
			CloseableHttpResponse getResponse = httpClient.execute(httpGet);
			try {
//				System.out.println(getResponse.getStatusLine());
				HttpEntity responseEntity = getResponse.getEntity();
				EntityUtils.consume(responseEntity);
			} finally {
				getResponse.close();
			}
//			String url = "https://api.instagram.com/oauth/authorize/?client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI+"&response_type=token";
//			System.out.println("로그인url: "+ url);
//			
//			Response loginForm = Jsoup.connect(url)
//					.method(Connection.Method.GET)
//					.execute();
//			
//			System.out.println("로그인한 후 쿠키값 : "+ loginForm.cookies());

			HttpPost httpPost = new HttpPost("https://api.instagram.com/oauth/authorize/");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("client_id", CLIENT_ID));
			nvps.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));
			nvps.add(new BasicNameValuePair("response_type", "token"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse postResponse = httpClient.execute(httpPost);
			try {
//				System.out.println(postResponse.getStatusLine());
				HttpEntity responseEntity = postResponse.getEntity();
				EntityUtils.consume(responseEntity);
				
				System.out.println(postResponse);
			} finally {
				postResponse.close();
			}
		} finally {
			httpClient.close();
		}

	}

}
