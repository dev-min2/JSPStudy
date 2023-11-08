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
		
		mapper.selectReplyAll(7, 5).forEach(rep -> {
			System.out.println(rep);
		});
		
	}
}
