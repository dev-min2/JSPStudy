package co.yedam.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.MemberVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class LoginControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		String id = req.getParameter("id");
		String password = req.getParameter("password");

		BoardService svc = new BoardServiceImpl();

		try {
			MemberVO loginMember = svc.loginCheck(id, password);
			if(loginMember != null) {
				HttpSession session = req.getSession(); // 호출시 session이 만들어짐.
				session.setAttribute("login", id);
				if(loginMember.getResponsibility().toUpperCase().equals("ADMIN")) {
					session.setAttribute("ADMIN", "admin");
				}
				
				resp.sendRedirect("boardList.do");
			} else {
				resp.sendRedirect("main.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
