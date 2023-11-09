package co.yedam.board.service;

import java.util.List;

import co.yedam.admin.service.MemberVO;

public interface BoardService {
	// 목록, 단건조회, 등록, 수정, 삭제:
	public List<BoardVO> getBoardList();
	public BoardVO getBoard(int boardNo);
	public boolean addBoard(BoardVO vo);
	public boolean editBoard(BoardVO vo);
	public boolean removeBoard(int boardNo);
	public boolean updateViewCnt(int boardNo);
	
	//로그인처리
	public MemberVO loginCheck(String id, String password);
	public List<MemberVO> getMemberList();
}