package co.yedam.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class AddBoardControl implements Command {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String boardTitle = req.getParameter("title");
		String boardWriter = req.getParameter("writer");
		String boardContent = req.getParameter("content");

		BoardService svc = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setTitle(boardTitle);
		vo.setWriter(boardWriter);
		vo.setContent(boardContent);

		try {
			if (svc.addBoard(vo)) {
				resp.sendRedirect("boardList.do");
			} 
			else {
				resp.sendRedirect("boardForm.do");
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
