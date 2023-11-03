package co.yedam.common;

import java.text.ParseException;
import java.util.List;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;

public class MainExe {
	public static void main(String[] args) throws ParseException {
		BoardService svc = new BoardServiceImpl();
		
		BoardVO vo = new BoardVO();
		vo.setTitle("민교");
		vo.setContent("안녕 난 민교야");
		vo.setWriter("민교");
		vo.setImage("이미지얌~");
		
		//svc.addBoard(vo);
		
		List<BoardVO> vos = svc.getBoardList();
		
		vos.forEach(obj -> {
			System.out.println(obj);
		});
		
		vo = svc.getBoard(4);
		vo.setTitle("민교킹");
		vo.setContent("집가고싶어");
		svc.editBoard(vo);
		
		svc.removeBoard(5);
		
	}
}
