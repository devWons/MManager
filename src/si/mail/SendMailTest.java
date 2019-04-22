package si.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTest {

	public static void main(String[] args) {

		/*PORT = 25:non-ssl, 465:ssl, 587:tls */
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.naver.com");
		props.put("mail.smtp.port", "25");
		props.put("defaultEncoding", "utf-8");
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.ssl.enable", "true"); //25번 port는 non-ssl이므로 주석처리함.

		final String userId = "skswodnjs102";
		final String userPw = "dnstks2fl!";
		
		try {
			String sender = "skswodnjs102@naver.com"; //보내는사람 메일주소
			String subject = "메일 테스트"; //메일 제목
			String body = "ㅁ ㅏ 메일 받아라 !!"; //메일 본문
			
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				String un=userId;
				String pw=userPw;
			
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(un, pw);
				}
			});
			session.setDebug(false); //Debug 모드 설정.

			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(sender)); 
			
			// 받는 사람 이메일주소 세팅
			InternetAddress[] toAddr = new InternetAddress[2];
			toAddr[0] = new InternetAddress ("skswodnjs102@naver.com");
			toAddr[1] = new InternetAddress ("jwkim@tell-us.co.kr");

			mimeMessage.setRecipients(Message.RecipientType.TO, toAddr ); //수신자 셋팅

//			"C:\\Users\\ilji\\Desktop\\database.properties"
			
			

			
			

			mimeMessage.setSubject(subject); //제목 세팅
			mimeMessage.setText(body); //본문 세팅
			
			//메일 발송
			Transport.send(mimeMessage);
			
			System.out.println("메일 발송 성공!!");
		} catch (Exception e) {
			System.out.println("메일보내기 오류 : "+ e.getMessage());
		}
	}

}