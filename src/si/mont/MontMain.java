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

public class MontMain {

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

			HttpPost httpPost = new HttpPost("http://ygsquare1.iptime.org/index.php/login");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("login_id", "yk306"));
			nvps.add(new BasicNameValuePair("login_pw", "1"));
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
