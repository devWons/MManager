package si.mont;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.swing.JLabel;

public class ParkManagerGUI extends JFrame {
	Date dt = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat nowTime = new SimpleDateFormat("HH:mm:ss");
	String today = sdf.format(dt);

	JPanel contentPane;
	JPanel contentPanel;
	JScrollPane scrollPane;
	JTextArea carList;
	JButton btnRefresh;
	JPanel btnPanel;
	static JProgressBar progressBar;
	JLabel jTotalCnt;
	
	/*****************    환경설정     *****************************************************************************/
	static int progressbarSleep = 15; //프로그레스바 갱신주기. 반드시 초단위임
	static int sleep = 20; //일정한 간격으로 실행될 쓰레드 시간. 반드시 분 단위임
	static int maxPers = sleep*60; // 프로그레스바 100%일때 
	static int currentSleepTime = 0;
	static int cst = 0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int progressbarSleep = 15; //프로그레스바 갱신주기. 반드시 초단위임
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			ParkManagerGUI frame = new ParkManagerGUI();
			frame.setVisible(true);
			
	        // 주기적인 작업을 위한
	        final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);


	        exec.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					try {
						System.err.println("---------    Auto START    ------------------------------------------------------- ");
						Calendar cal = Calendar.getInstance();
						System.out.println("Auto 실행시간 - "+nowTime.format(cal.getTime())) ;
						long start = System.currentTimeMillis(); //시작하는 시점 계산
						
						frame.getInputCarList();
						
						long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
						
						System.err.println("---------    Auto END (실행시간 : " + ( end - start )/1000.0 +"초)    ----------------------------------------- ");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 0, sleep, TimeUnit.MINUTES);
/*
	        exec.scheduleAtFixedRate(new Runnable() {
	        	@Override
	        	public void run() {
	        		try {
	        			Calendar cal2 = Calendar.getInstance() ;
	        			
//	        			currentSleepTime = currentSleepTime/maxPers*100;
	        			cst = cst/maxPers*100;
	        			System.out.println("1 : "+ cst);

//	        			System.out.println(currentSleepTime+"초 - 프로그레스바 마지막 갱신 : "+nowTime.format(cal2.getTime()));
//	        			System.out.println(cst+"초 - 프로그레스바 마지막 갱신 : "+nowTime.format(cal2.getTime()));
	        			progressBar.setValue(cst);
	        			
	        			if(cst >= maxPers)
	        			{
	        				cst = 0;
	        			} else {
	        				cst = cst + progressbarSleep;
	        			}
	        			System.out.println("cst : "+cst);
	        		} catch (Exception e) {
	        			e.printStackTrace();
	        		}
	        	}
	        }, 0, progressbarSleep, TimeUnit.SECONDS);
*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public ParkManagerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		createBtnPanel();
		createContentPanel();
		
	}
	
	private void createContentPanel()
	{
		contentPanel = new JPanel();
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		carList = new JTextArea();
		carList.setFont(new Font("Monospaced", Font.PLAIN, 24));
		carList.setRows(8);
		carList.setColumns(14);
		carList.setEditable(false);
		
		scrollPane = new JScrollPane(carList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		contentPanel.add(scrollPane);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("굴림", Font.PLAIN, 8));
		contentPane.add(progressBar, BorderLayout.SOUTH);
	}

	/*
	 * 버튼 패널 생성
	 * */
	public void createBtnPanel()
	{
		btnPanel = new JPanel();
		jTotalCnt = new JLabel("현재 : 0  건");
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		btnPanel.add(jTotalCnt);
		
		btnRefresh = new JButton("새로고침");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar start = Calendar.getInstance();
				System.err.println("-    "+nowTime.format(start.getTime())+"    새로고침 버튼 클릭!!    -------------------------------------------------");
				getInputCarList();
			}
		});
		
		btnPanel.add(btnRefresh);
		
		contentPane.add(btnPanel, BorderLayout.NORTH);
	}
	
	

	/*
	 * 당일자 입차된 차량 리스트를 가져온다.
	 * */
	private void getInputCarList() {
		try {
			Response loginForm = Jsoup.connect("http://ygsquare1.iptime.org/index.php/login")
					.method(Connection.Method.GET)
					.execute();
	
			Connection.Response evaluationPage = Jsoup.connect("http://ygsquare1.iptime.org/index.php/main/ajax_CarList/")
					.cookies(loginForm.cookies())
					.data("is_ajax", "1")
					.method(Connection.Method.POST)
					.timeout(5000)
					.execute();
	
			Document doc = evaluationPage.parse();
			
			String str = doc.select("a").outerHtml();
			
			String read = null;
			String lineInfo = null;
			String tmpToday = null;
			String[] tmpArr = null;
			int totalCnt = 0; //오늘 출입한 차량 건수 
			
			BufferedReader reader = new BufferedReader(new StringReader(str));
			reader.readLine();
			
			while((read = reader.readLine()) != null){
				read = read.replaceAll("'", ""); //" ' " 기호 Replace
				tmpArr = read.substring(read.indexOf("(")+1, read.lastIndexOf(")")).split(","); //괄호 안에 내용을 콤마(,) 기준으로 Split
				
				tmpToday = tmpArr[1].trim().substring(0, 10);
				
				if(today.matches(tmpToday))
				{
					totalCnt++; //차량수 증가
					carList.append("\n"+tmpArr[0]);
					carList.setCaretPosition(carList.getDocument().getLength());
				}
                lineInfo = null;
            }
		
			jTotalCnt.setText("현재 : "+totalCnt+" 건");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
