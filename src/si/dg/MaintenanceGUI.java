package si.dg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MaintenanceGUI extends JFrame {

	Map<String, String> config;
	Properties properties;
	JPanel contentPane;
	JProgressBar progressBar;
	JPanel logPanel;
	JPanel contentPanel;
	JTextArea logTextArea;
	JButton btn01;
	JButton btn02;
	JScrollPane scrollPane;
	private JButton btn03;
	private JButton btn04;
	private JButton btn05;
	private JButton btn06;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			MaintenanceGUI frame = new MaintenanceGUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public MaintenanceGUI() {
		setTitle("대구시 유지보수를 조금 더 쉽게하자!!");
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		showLogPanel();
		showContentPanel();
		getProperites();
		showProgressbar();
		
	}

	public void showContentPanel()
	{
		
		TitledBorder ContTabLabel = new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "작업영역");
		contentPanel = new JPanel();
		contentPanel.setBorder(ContTabLabel);
		contentPane.add(contentPanel, BorderLayout.NORTH);
		
		btn01 = new JButton("기본폴더열기");
		btn01.setFont(new Font("굴림", Font.PLAIN, 15));
		btn02 = new JButton("백업폴더생성");
		btn02.setFont(new Font("굴림", Font.PLAIN, 15));
		contentPanel.setLayout(new GridLayout(10, 1, 0, 5));
		
		contentPanel.add(btn01);
		contentPanel.add(btn02);
		
		btn03 = new JButton("백업폴더생성03");
		btn03.setFont(new Font("굴림", Font.PLAIN, 15));
		contentPanel.add(btn03);
		
		btn04 = new JButton("백업폴더생성04");
		btn04.setFont(new Font("굴림", Font.PLAIN, 15));
		contentPanel.add(btn04);
		
		btn05 = new JButton("백업폴더생성05");
		btn05.setFont(new Font("굴림", Font.PLAIN, 15));
		contentPanel.add(btn05);
		
		btn06 = new JButton("백업폴더생성06");
		btn06.setFont(new Font("굴림", Font.PLAIN, 15));
		contentPanel.add(btn06);
		
		/*
		 * 
		 * 버튼 이벤트 설정
		 * 
		 * */
		btn01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				targetOpenFolder(properties.getProperty("dgfilepath.dataupload"));
			}
		});
		
	}
	
	public void showLogPanel()
	{
		TitledBorder logTabLabel = new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "로그");
		
		logPanel = new JPanel();
		logPanel.setBorder(logTabLabel);
		logPanel.setLayout(null);
		
		logTextArea = new JTextArea();
		logTextArea.setBounds(0, 0, 410, 88);
		logTextArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
		logTextArea.setColumns(65);
		logTextArea.setRows(7);
		logTextArea.setTabSize(0);
		
		scrollPane = new JScrollPane(logTextArea);
		scrollPane.setBounds(5, 17, 414, 78);

		logPanel.add(scrollPane);
		contentPane.add(logPanel, BorderLayout.CENTER);
	}
	
	public void showProgressbar()
	{
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(78);
		progressBar.setFont(new Font("굴림", Font.PLAIN, 9));
		progressBar.setBounds(10, 80, 390, 10);
		contentPane.add(progressBar, BorderLayout.SOUTH);
	}
	
	
	/*
	 * 특정 폴더 열기
	 * */
	public void targetOpenFolder(String path)
	{
		addLog("폴더 열기 : "+ path);
		try {
			Runtime run = Runtime.getRuntime ();
			run.exec ( "C:\\windows\\explorer.exe "+ path );
		} catch (IOException ie){
			ie.printStackTrace();
			System.out.println("---------------------------------------------------------");
			ie.getMessage();
		} catch (Exception e) {
			e.printStackTrace();;
		}
	}
	
	/*
	 * 로그창에 로그쌓기
	 * */
	public void addLog(String msg)
	{
		try {
			logTextArea.append(msg+"\n");
			logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 알림 창 띄우기
	 * */
	public void showDialog(String title, String msg)
	{
		title = (title == null || title.isEmpty()) ? "경고" : title;
		msg = (msg == null || msg.isEmpty()) ? "관리자에게 문의하세요" : msg;
		
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
	}
	
	/*
	 * 프로퍼티파일 읽어오기
	 * */
	public Map<String, String> getProperites()
	{
		try {
			properties = new Properties();
			config = new HashMap<String, String>();
			String resourcePah = "C:\\java\\workspace\\MManager\\resource\\dgConfig.properties";
			
	        properties.load(new FileInputStream(resourcePah));
		
	        String msg = "프로퍼티 파일 읽기 성공. (경로 : "+ resourcePah+" )\n\n **** 프로젝트 시작 ****\n\n";
	        addLog(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}

}
