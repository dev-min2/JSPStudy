package co.yedam.board.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FirstServlet2.do")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//init(최초요청1회시) -> service(요청올떄) -> destroy(서버꺼질떄. gracefulshutdown)
    public FirstServlet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "홍길동";
		int age = 20;
		
		response.setContentType("text/html; charset=utf-8");
		for(int i = 0; i < 5; ++i) {
			response.getWriter().print("<p>" + i + "번째 이름은" + name + ", 나이는" + age + "임</p>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// service 메소드를 오버라이딩한경우 doGet, doPost로 가지않는듯.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("서비스실행");
	}
}
