package co.yedam.reply.mapper;

import java.util.List;

import co.yedam.reply.service.ReplyVO;

// Mapper인터페이스의 메소드명은 Mapper.xml의 id와 매핑되어야함
public interface ReplyMapper {
	public List<ReplyVO> selectReplyAll(int boardNo); // 목록
	public ReplyVO selectReply(int replyNo);
	public int insertReply(ReplyVO vo);
	public int updateReply(ReplyVO vo);
	public int deleteReply(int replyNo);
}
