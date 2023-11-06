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

public class ModifyBoardControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		BoardService svc = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		
		// TODO Auto-generated method stub
		if(req.getMethod().equals("POST")) {
			String saveDir = req.getServletContext().getRealPath("images");
			int size = 5 * 1024 * 1024;
			try {
				// encType을 multipart로 보내면 읽을때도 밑과같이 읽어야함.(즁요)
				MultipartRequest mr = new MultipartRequest(req, 
														saveDir, // 저장경로 
														size, // 업로드크기
														"UTF-8",
														new DefaultFileRenamePolicy()); // 리네임정책
				String bno = mr.getParameter("bno");
				int boardNo = Integer.parseInt(bno);
				
				if(svc.getBoard(boardNo) != null) {
					String title = mr.getParameter("title");
					String writer = mr.getParameter("writer"); // 굳이필요없음.
					String content = mr.getParameter("content");
					String img = mr.getFilesystemName("img");
					
					vo.setBoardNo(boardNo);
					vo.setTitle(title);
					vo.setWriter(writer);
					vo.setContent(content);
					vo.setImage(img);
					
					if(svc.editBoard(vo)) {
						resp.sendRedirect("boardList.do");
					}
					else {
						resp.sendRedirect("modifyForm.do");
					}
				}
				else {
					//에러페이지 redirect
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
