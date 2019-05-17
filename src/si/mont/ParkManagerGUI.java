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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParkManagerGUI extends JFrame {
	Date dt = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat nowTime = new SimpleDateFormat("HH");
	String today = sdf.format(dt);

	JPanel contentPane;
	JPanel contentPanel;
	JScrollPane scrollPane;
	JTextArea carList;
	JButton btnRefresh;
	JPanel btnPanel;
	JProgressBar progressBar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			ParkManagerGUI frame = new ParkManagerGUI();
			frame.setVisible(true);
			int progressbarSleep = 15; //15초
			int sleep = 20; //20분
			int currentSleepTime = 0;
			// 시간 출력 포맷
	        final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
	        // 주기적인 작업을 위한
	        final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);


	        exec.scheduleAtFixedRate(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.err.println("-----------------    Auto START!!    ------------------------------------------------------- ");
						Calendar cal = Calendar.getInstance() ;
						System.out.println(fmt.format(cal.getTime())) ;
						long start = System.currentTimeMillis(); //시작하는 시점 계산
						
						frame.getInputCarList();
						
						long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
						System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초");
						
						System.err.println("-----------------    Auto END!!      ------------------------------------------------------- ");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 0, sleep, TimeUnit.MINUTES);

	        exec.scheduleAtFixedRate(new Runnable() {
	        	
	        	@Override
	        	public void run() {
	        		try {
	        			Calendar cal2 = Calendar.getInstance() ;
	        			System.out.println(fmt.format(cal2.getTime())) ;
	        			
	        			int maxPers = 1200;
	        			
	        		} catch (Exception e) {
	        			e.printStackTrace();
	        		}
	        	}
	        }, 0, progressbarSleep, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public ParkManagerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 238, 410);
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
		contentPane.add(contentPanel, BorderLayout.WEST);
		
		carList = new JTextArea();
		carList.setFont(new Font("Monospaced", Font.PLAIN, 24));
		carList.setRows(9);
		carList.setColumns(13);
		carList.setEditable(false);
		
		scrollPane = new JScrollPane(carList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setAlignmentX(CENTER_ALIGNMENT);
		
		contentPanel.add(scrollPane);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setFont(new Font("굴림", Font.PLAIN, 8));
		contentPane.add(progressBar, BorderLayout.SOUTH);
	}

	/*
	 * 버튼 패널 생성
	 * */
	public void createBtnPanel()
	{
		btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(btnPanel, BorderLayout.NORTH);
		
		btnRefresh = new JButton("새로고침");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long start = System.currentTimeMillis();
				System.err.println("------    "+start+"    -------    Manual START!!    ------------------------------------------------------- ");
				getInputCarList();
			}
		});
		btnPanel.add(btnRefresh);
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
			
			BufferedReader reader = new BufferedReader(new StringReader(str));
			reader.readLine();
			
			while((read = reader.readLine()) != null){
				read = read.replaceAll("'", ""); //" ' " 기호 Replace
				tmpArr = read.substring(read.indexOf("(")+1, read.lastIndexOf(")")).split(","); //괄호 안에 내용을 콤마(,) 기준으로 Split
				
				tmpToday = tmpArr[1].trim().substring(0, 10);
				
				if(today.matches(tmpToday))
				{
					carList.append("\n"+tmpArr[0]);
					carList.setCaretPosition(carList.getDocument().getLength());
				}
                lineInfo = null;
            }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
