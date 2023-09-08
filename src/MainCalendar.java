
public class MainCalendar {
	CalendartDAO dao = new CalendartDAO();
	public MainCalendar() {
		init();
	}
	private void init() {
		
		LoginCalendar lc =new LoginCalendar("로그인");
		//JoinCalendar jc = new JoinCalendar("회원가입");
		//NoahCal nc = new NoahCal("캘린더","noa2006");
		//MemoCalendar mc = new MemoCalendar(2023,9, 7,"noa2006");
	}
	public static void main(String[] args) {
		MainCalendar mc =new MainCalendar();
		
	}
}
   