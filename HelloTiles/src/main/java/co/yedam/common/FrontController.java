package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.admin.web.MemberListControl;
import co.yedam.board.web.BoardListControl;
import co.yedam.board.web.GetBoardControl;
import co.yedam.board.web.LoginControl;
import co.yedam.board.web.LoginFormControl;
import co.yedam.board.web.LogoutControl;
import co.yedam.reply.web.AddReplyControl;
import co.yedam.reply.web.DelReplyControl;
import co.yedam.reply.web.ReplyListControl;

public class FrontController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Command> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		map.put("/boardList.do", new BoardListControl());
		map.put("/memberList.do", new MemberListControl());
		map.put("/getBoard.do", new GetBoardControl());
		map.put("/replyList.do", new ReplyListControl());
		map.put("/delReply.do", new DelReplyControl());
		map.put("/addReply.do", new AddReplyControl());
		// 로그인
		map.put("/loginForm.do", new LoginFormControl());
		map.put("/login.do", new LoginControl());
		// 로그아웃
		map.put("/logout.do", new LogoutControl());
	}
	
	//끼양
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		System.out.println("데프콘");
		
		// helloJSP/??.do를 가져옴.
		String uri = req.getRequestURI();
		// helloJsp를 가져옴.
		String contextPath = req.getContextPath();
		// 실제 요청. ???.do를 찾는당
		String reqURL = uri.substring(contextPath.length());
		
		Command cmd = map.get(reqURL);
		System.out.println(reqURL);
		if(cmd != null) {
			cmd.execute(req, resp);
		}
		else {
			// 에러
			System.out.println("없음");
		}
	}
}
