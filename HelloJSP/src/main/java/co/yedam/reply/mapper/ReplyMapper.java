package co.yedam.reply.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.yedam.common.ChartVO;
import co.yedam.reply.service.ReplyVO;

// Mapper인터페이스의 메소드명은 Mapper.xml의 id와 매핑되어야함
public interface ReplyMapper {
	public List<ReplyVO> selectReplyAll(@Param("boardNo") int boardNo, @Param("page") int page); // 목록
	public ReplyVO selectReply(int replyNo);
	public int insertReply(ReplyVO vo);
	public int updateReply(ReplyVO vo);
	public int deleteReply(int replyNo);
	
	// 댓글갯수
	public int getTotalCnt(int boardNo);
	// 전체 게시판 회원별 댓글 개수
	public List<ChartVO> getReplyCntByMember();
}
