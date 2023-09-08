import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class MemoCalendar extends JFrame implements ActionListener{
	private String[] columnNames= {"제목","내용"};
	private Object[][] rowData;
	private JTable table;
	private JScrollPane scrollPane;
	JLabel lbl;
	private JTextField todo= new JTextField();
	private JButton[] btn = new JButton[4];
	int month,day,year;
	ArrayList<CalendarBean> lists ;
	Container contentPane;
	CalendartDAO dao;
	String ID;
	JTextField txtTitle = new JTextField(); 
	JTextField txtMemo = new JTextField(); 
	CalendarBean acb= new CalendarBean();
	
	 int number=0 ;// 숨겨진 메모 고유번호
	
	public MemoCalendar(int year,int month, int day, String ID) {
	
		this.ID = ID;
		System.out.println(year+"년"+month+"월 "+day+"일");
		this.month=month;
		this.day = day;
		this.year=year;
		compose();
		setSize(400,500);
		setVisible(true);
	}
	private void compose() {
		dao = new CalendartDAO();
		lists = dao.getAllCal(ID,year,month,day);
		rowData = new Object[lists.size()][columnNames.length];
	
		fillData();
		
		table =	new JTable(rowData,columnNames);//내용,위쪽 목차
		scrollPane= new JScrollPane(table);
		contentPane= getContentPane();
		
		contentPane.setLayout(null);
		scrollPane.setBounds(0,50,400,300);
		contentPane.add(scrollPane);
		lbl = new JLabel(month+"월"+day+"일");
		lbl.setBounds(5, 0, 100, 50);
		contentPane.add(lbl);
		
		JLabel lbTitle = new JLabel("제목");
		JLabel lbMemo = new JLabel("내용");
		lbTitle.setBounds(10, 350, 30, 30);
		lbMemo.setBounds(10, 380, 30, 30);
		
		contentPane.add(lbTitle);
		contentPane.add(lbMemo);
		
		
	
		txtTitle.setBounds(40, 355, 300, 20);
		txtMemo.setBounds(40, 385, 300, 20);
		contentPane.add(txtTitle);
		contentPane.add(txtMemo);
		
		String [] btnTitile= {"등록","수정","삭제"};
		for(int i=0;i<btnTitile.length;i++) {
			btn[i] = new JButton(btnTitile[i]);
			btn[i].addActionListener(this);
		}
		btn[0].setBounds(40, 410, 60, 40);
		btn[1].setBounds(120, 410, 60, 40);
		btn[2].setBounds(200, 410, 60, 40);
		contentPane.add(btn[0]);
		contentPane.add(btn[1]);
		contentPane.add(btn[2]);
		
		table.addMouseListener(new MouseHandler());
		
	}
	private void fillData() {
		Object[] arr = lists.toArray();
		int j=0;
		for(int i=0;i<arr.length;i++) {
			CalendarBean pb =(CalendarBean) arr[i];
			rowData[i][j++] = pb.getTitle();
			rowData[i][j++] = pb.getMemo();
			j=0;
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj==btn[0]) {
				System.out.println("등록");
				dao.InsertMemo(ID,txtTitle.getText(),txtMemo.getText(),year,month,day);
				getAllCal();
				clearTextField();
			}
			else if(obj==btn[1]) {
				CalendarBean cb = new CalendarBean();
			
				System.out.println("수정");
				dao.UpdateMemo(ID,txtTitle.getText(),txtMemo.getText(),year,month,day,number);
				getAllCal();
				clearTextField();
			}
			else if(obj==btn[2]) {
				System.out.println("삭제");
				dao.DeleteData(ID,number);
				getAllCal();
				clearTextField();
			}
	}

	private void getAllCal() {
		dao= new CalendartDAO();
		lists = dao.getAllCal(ID,year,month,day);
		rowData = new Object[lists.size()][columnNames.length];
		fillData();

		table =	new JTable(rowData,columnNames);//내용,위쪽 목차
		scrollPane.setViewportView(table);//새로고침

		table.addMouseListener(new MouseHandler());
		
	}
	class MouseHandler extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseEntered");
			int row = table.getSelectedRow();
			System.out.println("row:"+ row);
			txtTitle.setText(String.valueOf(table.getValueAt(row,0)));
			txtMemo.setText(String.valueOf(table.getValueAt(row,1)));
			number =dao.Numfind(ID,txtTitle.getText(),txtMemo.getText());
			
	
		}
	}
	public void clearTextField() {
		txtMemo.setText("");
		txtTitle.setText("");
	


	}
































}
