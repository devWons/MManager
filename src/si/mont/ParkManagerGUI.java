package si.mont;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParkManagerGUI extends JFrame {

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
		progressBar.setValue(67);
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
				getInputCarList();
			}
		});
		btnPanel.add(btnRefresh);
	}
	
	

	/*
	 * 입차된 차량 리스트를 가져온다.
	 * */
	private void getInputCarList() {
		ArrayList<String> listArr = new ArrayList<String>();
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
			
			for(Element elFont : doc.select("font"))
			{
				carList.append("\n"+elFont.text());
				carList.setCaretPosition(carList.getDocument().getLength());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
