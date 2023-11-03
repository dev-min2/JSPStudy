package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.web.AddBoardControl;
import co.yedam.board.web.BoardFormControl;
import co.yedam.board.web.BoardListControl;
import co.yedam.board.web.GetBoardControl;

public class FrontController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Command> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		map.put("/boardList.do", new BoardListControl());
		map.put("/getBoard.do", new GetBoardControl());
		map.put("/boardForm.do", new BoardFormControl()); // 화면만 보여주기 
		map.put("/addBoard.do", new AddBoardControl());
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
		if(cmd != null) {
			cmd.execute(req, resp);
		}
		else {
			// 에러
			System.out.println("없음");
		}
	}
}
