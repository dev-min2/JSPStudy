package co.yedam.common;

import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.reply.mapper.ReplyMapper;
import co.yedam.reply.service.ReplyVO;

public class MainExe {
	public static void main(String[] args) throws ParseException {
		SqlSession session = //
					DataSourceMybatis.getInstance().openSession(true);
		
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		
		List<ReplyVO> vos = mapper.selectReplyAll(7);
		
		vos.forEach((obj) -> {
			System.out.println(obj);
		});
		
		
		ReplyVO vo = new ReplyVO();
		vo.setReplyNo(11);
		vo.setBoardNo(7);
		vo.setReply("집이너무너무가고싶어요");
		vo.setReplyer("M001");
//		
//		if(mapper.insertReply(vo) > 0) {
//			System.out.println("삽입성공");
//		}
		
		System.out.println(mapper.selectReply(3));
		
	}
}
