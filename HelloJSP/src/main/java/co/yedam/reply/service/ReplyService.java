package co.yedam.reply.service;

import java.util.List;

public interface ReplyService {
	public List<ReplyVO> replyList(int boardNo, int page);
	public ReplyVO getReply(int replyNo);
	public boolean addReply(ReplyVO vo);
	public boolean editReply(ReplyVO vo);
	public boolean deleteReply(int replyNo);
	
	public int getReplyCnt(int boardNo);
}
