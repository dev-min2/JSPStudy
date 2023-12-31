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

public class AddBoardControl implements Command {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		BoardService svc = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		// TODO Auto-generated method stub
		if(req.getMethod().equals("get")) {
			String boardTitle = req.getParameter("title");
			String boardWriter = req.getParameter("writer");
			String boardContent = req.getParameter("content");

			vo.setTitle(boardTitle);
			vo.setWriter(boardWriter);
			vo.setContent(boardContent);

			try {
				if (svc.addBoard(vo)) {
					resp.sendRedirect("boardList.do");
				} else {
					resp.sendRedirect("boardForm.do");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(req.getMethod().equals("POST")) {
			// 파일 업로드
			
			String saveDir = req.getServletContext().getRealPath("images");
			int size = 5 * 1024 * 1024;
			try {
				MultipartRequest mr = new MultipartRequest(req, 
														saveDir, // 저장경로 
														size, // 업로드크기
														"UTF-8",
														new DefaultFileRenamePolicy()); // 리네임정책
				
				String title = mr.getParameter("title");
				String writer = mr.getParameter("writer");
				String content = mr.getParameter("content");
				String img = mr.getFilesystemName("img");
				
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setContent(content);
				vo.setImage(img);
				
				if(svc.addBoard(vo)) {
					resp.sendRedirect("boardList.do");
				}
				else {
					resp.sendRedirect("boardForm.do");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}