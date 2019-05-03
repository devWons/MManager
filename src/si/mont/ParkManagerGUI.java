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

public class ParkManagerGUI extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> model;
	
	private String[] colHeader = {"입차시간","차량번호"};
	private JTable table;
	

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(btnPanel, BorderLayout.NORTH);
		
		JButton button = new JButton("새로고침");
		btnPanel.add(button);
		
		JLabel lblNewLabel = new JLabel("");
		btnPanel.add(lblNewLabel);
		
		ArrayList<String> carListArr = getInputCarList();
		
		model = new DefaultListModel<String>();
		for(String car : carListArr)
		{
			model.addElement(car);
		}
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"입차시간", "차량번호", "주차시간"
			}
		));
		contentPane.add(table, BorderLayout.SOUTH);
		
	}

	/*
	 * 입차된 차량 리스트를 가져온다.
	 * */
	private ArrayList<String> getInputCarList() {
		ArrayList<String> listArr = new ArrayList<String>();
		listArr.add("39너7444");
		listArr.add("28도7174");
		listArr.add("65누3085");
		listArr.add("30도0715");
		listArr.add("52노7579");
		listArr.add("27나9139");
		listArr.add("20마2140");
		listArr.add("52저6877");
		listArr.add("26러9756");
		listArr.add("06어5236");
		listArr.add("33구0917");
		listArr.add("39너7444");
		listArr.add("28도7174");
		listArr.add("65누3085");
		listArr.add("30도0715");
		listArr.add("52노7579");
		listArr.add("27나9139");
		return listArr;
	}

}
