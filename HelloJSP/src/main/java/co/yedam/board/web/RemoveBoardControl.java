package co.yedam.board.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class RemoveBoardControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		BoardService svc = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		
		// TODO Auto-generated method stub
		if(req.getMethod().equals("POST")) {
			try {
				String bno = req.getParameter("bno");
				int boardNo = Integer.parseInt(bno);
				
				if(svc.removeBoard(boardNo)) {
					resp.sendRedirect("boardList.do");
				}
				else {
					resp.sendRedirect("removeForm.do");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
