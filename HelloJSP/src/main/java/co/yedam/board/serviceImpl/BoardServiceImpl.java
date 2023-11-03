package co.yedam.board.serviceImpl;

import java.util.List;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;

public class BoardServiceImpl implements BoardService {
	BoardDAO dao = new BoardDAO();
	@Override
	public List<BoardVO> getBoardList() {
		return dao.selectAll();
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		dao.updateViewCnt(boardNo);
		return dao.select(boardNo);
	}

	@Override
	public boolean addBoard(BoardVO vo) {
		return dao.insert(vo) > 0 ? true : false;
	}

	@Override
	public boolean editBoard(BoardVO vo) {
		return dao.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean removeBoard(int boardNo) {
		return dao.delete(boardNo) > 0 ? true : false;
	}

	@Override
	public boolean updateViewCnt(int boardNo) {
		// TODO Auto-generated method stub
		return dao.updateViewCnt(boardNo) > 0 ? true : false;
	}
}
