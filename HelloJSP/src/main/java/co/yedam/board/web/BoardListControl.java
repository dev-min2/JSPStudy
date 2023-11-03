package co.yedam.board.web;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class BoardListControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		BoardService svc = new BoardServiceImpl();
		List<BoardVO> boardList = svc.getBoardList();
		
		req.setAttribute("boardList", boardList);
		
		// 페이지요청(boardList.do) -> 요청을 넘김(서블릿 request와 response가 그대로 전달이됨. 즉 객체 살아있음)
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/board/boardList.jsp");
		try {
			// 요청을 넘김
			rd.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
