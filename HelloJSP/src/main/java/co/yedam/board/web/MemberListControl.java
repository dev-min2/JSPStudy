package co.yedam.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.MemberVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class MemberListControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		if(session.getAttribute("login") == null)
			return;
		if(session.getAttribute("ADMIN") == null)
			return;
		
		BoardService svc = new BoardServiceImpl();
		List<MemberVO> vos = svc.getMemberList();
		
		req.setAttribute("list", vos);
		try {
			req.getRequestDispatcher("WEB-INF/board/memberList.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
