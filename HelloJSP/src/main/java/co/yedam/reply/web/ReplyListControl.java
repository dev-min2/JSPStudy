package co.yedam.reply.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.common.Command;
import co.yedam.common.PageDTO;
import co.yedam.reply.service.ReplyService;
import co.yedam.reply.service.ReplyVO;
import co.yedam.reply.serviceImpl.ReplyServiceImpl;

public class ReplyListControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String bno = req.getParameter("bno");
		String page = req.getParameter("page");
		page = (page == null ? "1" : page);
		
		int boardNo = Integer.parseInt(bno);
		int pageNo = Integer.parseInt(page);
		
		ReplyService svc = new ReplyServiceImpl();
		int totalReplyCnt = svc.getReplyCnt(boardNo);
		PageDTO dto = new PageDTO(boardNo, totalReplyCnt, pageNo, 5);
		
		List<ReplyVO> vos = svc.replyList(boardNo, pageNo);
		
		System.out.println(dto);
		
		Map<String, Object> map = new HashMap<>();
		map.put("vos", vos);
		map.put("dto", dto);
		
		// json형태로 데이터전달.
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(map);
		resp.setContentType("application/json; charset=utf-8");
		
		try {
			resp.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
