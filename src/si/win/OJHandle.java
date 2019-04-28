package si.win;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class OJHandle extends JFrame {

	DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###");
	private JPanel contentPane;
	private JTextField rtnValue;
	private JTextField ratio1;
	private JTextField itemVal;
	private JTextField ratio2;
	private JTextField txtHttpsopenkakaocomospomrt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OJHandle frame = new OJHandle();
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
	public OJHandle() {
		setTitle("악제계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		contentPane.add(panel, BorderLayout.NORTH);
		
		rtnValue = new JTextField();
		rtnValue.setEditable(false);
		rtnValue.setHorizontalAlignment(SwingConstants.CENTER);
		rtnValue.setFont(new Font("Arial Unicode MS", Font.PLAIN, 35));
		panel.add(rtnValue);
		rtnValue.setColumns(13);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 7, 401, 45);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("비     율");
		label.setBounds(5, 10, 80, 24);
		panel_2.add(label);
		label.setFont(new Font("HY그래픽", Font.PLAIN, 20));
		
		JLabel label_2 = new JLabel(":");
		label_2.setFont(new Font("HY그래픽", Font.PLAIN, 20));
		label_2.setBounds(245, 10, 14, 24);
		panel_2.add(label_2);
		
		ratio1 = new JTextField();
		ratio1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int kCode = e.getKeyCode();
//				System.out.println("keyCode : "+kCode);
				String numberText = getPureNumber(ratio1.getText());
				ratio1.setText(setNumberFomat(numberText));
				if(kCode == 10)
				{
					//엔터키 이벤트 실행
					calculation();
				} else if((kCode >= 48 && kCode <= 57) || (kCode >= 96 && kCode <= 105)) {
					//천단위구분
					ratio1.setText(setNumberFomat(numberText));
				}
			}
		});
		ratio1.setText("1,000,000");
		ratio1.setBounds(90, 7, 146, 30);
		panel_2.add(ratio1);
		ratio1.setFont(new Font("굴림", Font.PLAIN, 20));
		ratio1.setColumns(10);
		
		ratio2 = new JTextField();
		ratio2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int kCode = e.getKeyCode();
//				System.out.println("keyCode : "+kCode);
				String numberText = getPureNumber(ratio2.getText());
				ratio2.setText(setNumberFomat(numberText));
				if(kCode == 10)
				{
					//엔터키 이벤트 실행
					calculation();
					
				} else if((kCode >= 48 && kCode <= 57) || (kCode >= 96 && kCode <= 105)) {
					//천단위구분
					ratio2.setText(setNumberFomat(numberText));
				}
			}
		});
		ratio2.setText("500");
		ratio2.setBounds(263, 7, 125, 30);
		panel_2.add(ratio2);
		ratio2.setFont(new Font("굴림", Font.PLAIN, 20));
		ratio2.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 57, 401, 45);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_1 = new JLabel("상품금액");
		label_1.setBounds(5, 10, 80, 24);
		panel_3.add(label_1);
		label_1.setFont(new Font("HY그래픽", Font.PLAIN, 20));
		
		itemVal = new JTextField();
		itemVal.setBounds(90, 7, 298, 30);
		itemVal.setHorizontalAlignment(SwingConstants.RIGHT);
		itemVal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int kCode = e.getKeyCode();
//				System.out.println("keyCode : "+kCode);
				String numberText = getPureNumber(itemVal.getText());
				itemVal.setText(setNumberFomat(numberText));
				if(numberText.length() > 10)
				{
					showDialog();
				}
				
				if(kCode == 10)
				{
					//엔터키 이벤트 실행
					calculation();
				} else if((kCode >= 48 && kCode <= 57) || (kCode >= 96 && kCode <= 105)) {
					//천단위구분
					itemVal.setText(setNumberFomat(numberText));
				}
			}

		});
		itemVal.setFont(new Font("굴림", Font.PLAIN, 20));
		itemVal.setColumns(19);
		panel_3.add(itemVal);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 107, 401, 37);
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setHgap(100);
		panel_1.add(panel_4);
		
		JButton button = new JButton("계산하기");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculation();
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("계산하기 버튼 클릭.");
			}
		});
		panel_4.add(button);
		button.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 153, 401, 21);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel label_3 = new JLabel("오픈톡주소");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("굴림", Font.PLAIN, 8));
		label_3.setBounds(5, 3, 46, 16);
		panel_5.add(label_3);
		
		txtHttpsopenkakaocomospomrt = new JTextField();
		txtHttpsopenkakaocomospomrt.setText("https://open.kakao.com/o/spOMrT7");
		txtHttpsopenkakaocomospomrt.setBounds(60, 3, 274, 16);
		panel_5.add(txtHttpsopenkakaocomospomrt);
		txtHttpsopenkakaocomospomrt.setFont(new Font("굴림", Font.PLAIN, 8));
		txtHttpsopenkakaocomospomrt.setColumns(10);
		
		JButton btnNewButton = new JButton("복사");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("기능 미구현.");
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 8));
		btnNewButton.setBounds(338, 1, 50, 20);
		panel_5.add(btnNewButton);
	}
	
	//계산하기
	public void calculation()
	{
		int tVal = Integer.parseInt(getPureNumber(itemVal.getText()));
		int bVal = Integer.parseInt(getPureNumber(ratio2.getText()));
		int fVal = Integer.parseInt(getPureNumber(ratio1.getText()));
		
		long rtnVal = (tVal/fVal)*bVal;
		System.out.println("(거래소금액 )"+tVal+" * (단가)"+bVal+" / (비율)"+fVal+" = (입금액 )"+rtnVal);
		
		rtnValue.setText(setNumberFomat(String.valueOf(rtnVal)));
	}
	
	//천단위구분기호
	public String setNumberFomat(String s)
	{
		if(!s.isEmpty())
		{
			s = s.replaceAll("[^0-9]", "");
			long r = Long.parseLong(s);
			s = String.valueOf(formatter.format(r));
		}
		return s;
	}
	
	// 숫자인지 확인
	public boolean isNumber(String str)
	{
		return str.matches("^[a-zA-Z0-9]*$");
	}
	
	/*
	 * 숫자를제외한 모든 문자 삭제
	 * return : String
	 * */
	public String getPureNumber(String text) {
		return text.replaceAll("[^0-9]", "");
	}
	
	public void showDialog()
	{
		JOptionPane.showMessageDialog(null, "화면에 출력할 메세지", "제목", JOptionPane.WARNING_MESSAGE);
	}
}
