
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginCalendar extends JFrame implements ActionListener {
	CalendartDAO dao = new CalendartDAO();
	Container contentPane;
	JPanel p1;
	JLabel ID;
	JLabel PW;
	JTextField txtId;
	JTextField txtPw;
	JLabel name;
	JButton login;
	JButton join;

	public LoginCalendar(String string) {
		super(string);

		addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
					public void windowActivated(WindowEvent e) {
						System.out.println("windowActivated");
					}
				}
				);
		compose();

		setSize(800,600);
		setVisible(true);



	}
	private void compose() {
		Font font =new Font("monospaced", Font.BOLD, 30);
		Font font1 =new Font("monospaced", Font.BOLD, 100);
		contentPane= getContentPane();
		contentPane.setLayout(null);
		p1= new JPanel();
		p1.setLayout(null);
		p1.setBounds(0,0,800,600);  
		p1.setBackground(Color.blue);
		contentPane.add(p1);

		ID = new JLabel("ID");
		PW = new JLabel("PW");
		ID.setBounds(250,200,50,50);
		PW.setBounds(250,240,50,50);
		ID.setFont(font);
		PW.setFont(font);
		p1.add(ID);
		p1.add(PW);

		txtId= new JTextField();
		txtPw= new JTextField();
		txtId.setBounds(330, 210, 130, 30);
		txtPw.setBounds(330, 250, 130, 30);
		
		p1.add(txtId);
		p1.add(txtPw);
		
		name = new JLabel("ARK");
		login = new JButton("로그인");
		join = new JButton("회원가입");
		name.setFont(font1);
		name.setBounds(300, 5 , 300, 200);
		
		Font font3 =new Font("monospaced", Font.BOLD, 20);
		login.setFont(font3);
		login.setBackground(Color.BLUE);
		login.setBounds(330, 300, 130, 40);
		
		login.addActionListener(this);
		join.setFont(font3);
		join.setBackground(Color.BLUE);
		join.setBounds(330, 350, 130, 40);
		join.addActionListener(this);
		p1.add(name);
		p1.add(login);
		p1.add(join);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj =e.getSource();
		ResultSet check =null;
		if(obj==login) {
			int i = dao.login(txtId.getText(),txtPw.getText());
			System.out.println(i);
			if (i==0)
				dispose();
		}
		else if(obj==join) {
			JoinCalendar jc = new JoinCalendar("회원 가입");
		}
	}

}
