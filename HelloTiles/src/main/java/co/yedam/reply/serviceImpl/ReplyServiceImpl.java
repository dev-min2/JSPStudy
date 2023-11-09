package co.yedam.reply.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import co.yedam.common.DataSourceMybatis;
import co.yedam.reply.mapper.ReplyMapper;
import co.yedam.reply.service.ReplyService;
import co.yedam.reply.service.ReplyVO;

public class ReplyServiceImpl implements ReplyService {
	
	private SqlSession session = DataSourceMybatis.getInstance().openSession(true);
	private ReplyMapper mapper = session.getMapper(ReplyMapper.class);
	
	public void close() {
		session.close();
	}
	
	@Override
	public List<ReplyVO> replyList(int boardNo, int page) {
		// TODO Auto-generated method stub
		return mapper.selectReplyAll(boardNo, page);
	}

	@Override
	public ReplyVO getReply(int replyNo) {
		// TODO Auto-generated method stub
		return mapper.selectReply(replyNo);
	}

	@Override
	public boolean addReply(ReplyVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertReply(vo) > 0 ? true : false;
	}

	@Override
	public boolean editReply(ReplyVO vo) {
		// TODO Auto-generated method stub
		return mapper.updateReply(vo) > 0 ? true : false;
	}

	@Override
	public boolean deleteReply(int replyNo) {
		// TODO Auto-generated method stub
		return mapper.deleteReply(replyNo) > 0 ? true : false;
	}

	@Override
	public int getReplyCnt(int boardNo) {
		// TODO Auto-generated method stub
		return mapper.getTotalCnt(boardNo);
	}
}
