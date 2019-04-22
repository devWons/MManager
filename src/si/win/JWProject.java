package si.win;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JWProject extends JFrame {

	private JPanel contentPane;
	private JTextField carNum;
	private JTextField phoneNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JWProject frame = new JWProject();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JWProject() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JTextArea logArea = new JTextArea();
		logArea.setEditable(false);
		logArea.setRows(4);
		logArea.setColumns(50);
		logArea.setTabSize(10);
		panel.add(logArea);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(10);
		panel_1.add(panel_2);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setLineWrap(true);
		textArea_1.setRows(5);
		textArea_1.setTabSize(0);
		panel_2.add(textArea_1);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel carNumber = new JLabel("차량번호");
		carNumber.setHorizontalAlignment(SwingConstants.LEFT);
		carNumber.setVerticalAlignment(SwingConstants.TOP);
		panel_4.add(carNumber);
		
		carNum = new JTextField();
		panel_4.add(carNum);
		carNum.setHorizontalAlignment(SwingConstants.LEFT);
		carNum.setColumns(10);
		
		JButton btnCarNumSearch = new JButton("SEARCH");
		panel_4.add(btnCarNumSearch);
		btnCarNumSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JLabel label = new JLabel("전화번호");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(label);
		
		phoneNum = new JTextField();
		phoneNum.setHorizontalAlignment(SwingConstants.LEFT);
		phoneNum.setColumns(10);
		panel_5.add(phoneNum);
		
		JButton btnPhoneNumSearch = new JButton("SEARCH");
		btnPhoneNumSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(btnPhoneNumSearch);
	}

}
