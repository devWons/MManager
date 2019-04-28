package si.mont;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JTable;

public class ParkManagerGUI extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	
	private String[] colHeader = {"입차시간","차량번호"};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
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
		
		JPanel listPanel = new JPanel();
		contentPane.add(listPanel, BorderLayout.CENTER);
		
		
		model = new DefaultTableModel(colHeader, 0);
		
	}

}
