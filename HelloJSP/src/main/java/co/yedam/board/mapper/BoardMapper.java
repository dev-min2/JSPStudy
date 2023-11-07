package co.yedam.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.yedam.board.service.BoardVO;
import co.yedam.board.service.MemberVO;

// Mapper인터페이스의 메소드명은 Mapper.xml의 id와 매핑되어야함
public interface BoardMapper {
	public List<BoardVO> selectBoardAll(); 
	public BoardVO select(int boardNo);
	public int updateViewCnt(int boardNo);
	public int insert(BoardVO vo);
	public int update(BoardVO vo);
	public int delete(int boardNo);
	public List<MemberVO> selectMemberAll();
	public MemberVO selectMember(@Param("id") String id, @Param("pass") String pass);
}
