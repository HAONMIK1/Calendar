import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinCalendar extends JFrame implements ActionListener {
	CalendartDAO dao = new CalendartDAO();
	Container contentPane;
	JPanel p1;
	JLabel title;
	JLabel id;
	JLabel pw;
	JLabel name;
	JLabel birth;
	JTextField txtid;
	JTextField txtpw;
	JTextField txtname;
	JTextField txtbirth;
	JButton join;

	public JoinCalendar(String string) {
		super(string);
		addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						dispose();
					}
					public void windowActivated(WindowEvent e) {
						System.out.println("windowActivated");
					}
				}
				);
		compose();

		setSize(300,400);
		setVisible(true);
	}

	private void compose() {
		
		Font font =new Font("궁서", Font.BOLD, 20);
		Font font1 =new Font("궁서", Font.BOLD, 40);
		title = new JLabel("회원가입");
		title.setFont(font1);
		title.setBounds(80, 30, 200, 60);
		contentPane= getContentPane();
		contentPane.setLayout(null);
		p1= new JPanel();
		p1.setLayout(null);
		p1.setBounds(0,0,300,400);
		p1.setBackground(Color.yellow);
		contentPane.add(p1);
	
		p1.add(title);
		name = new JLabel("이름");
		birth = new JLabel("생년월일");
		id = new JLabel("아이디");
		pw = new JLabel("비밀번호");
		
		id.setFont(font);
		pw.setFont(font);
		name.setFont(font);
		birth.setFont(font);
		
		id.setBounds(30, 120, 100, 30);
		pw.setBounds(30, 160, 100, 30);
		name.setBounds(30, 200, 100, 30);
		birth.setBounds(30, 240, 180, 30);

		txtid= new JTextField();
		txtpw= new JTextField();
		txtname= new JTextField();
		txtbirth= new JTextField();
		
		txtid.setBounds(120, 120, 100, 30);
		txtpw.setBounds(120, 160, 100, 30);
		txtname.setBounds(120, 200, 100, 30);
		txtbirth.setBounds(120, 240, 100, 30);
		
		p1.add(txtid);
		p1.add(txtpw);
		p1.add(txtname);
		p1.add(txtbirth);

		p1.add(name);
		p1.add(birth);
		p1.add(id);
		p1.add(pw);
		
		join = new JButton("확인");
		join.setBounds(120, 300, 100, 30);
		join.addActionListener(this);
		p1.add(join);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		dao.JoinCal(txtid.getText(),txtpw.getText(),txtname.getText(),txtbirth.getText());
		dao.CreateT(txtid.getText());
		txtid.setText("");
		txtpw.setText("");
		txtname.setText("");
		txtbirth.setText("");
	
		
	}

}
