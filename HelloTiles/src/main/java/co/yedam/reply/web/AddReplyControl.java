package co.yedam.reply.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.common.Command;
import co.yedam.reply.service.ReplyService;
import co.yedam.reply.service.ReplyVO;
import co.yedam.reply.serviceImpl.ReplyServiceImpl;

public class AddReplyControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String bno = req.getParameter("bno");
		String replyer = req.getParameter("replyer");
		String reply = req.getParameter("reply");
		
		if(replyer == null)
			return;
		
		ReplyService svc = new ReplyServiceImpl();
		ReplyVO vo = new ReplyVO();
		vo.setBoardNo(Integer.parseInt(bno));
		vo.setReply(reply);
		vo.setReplyer(replyer);
		vo.setReplyDate(new Date());
		
		Map<String,Object> map = new HashMap<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if(svc.addReply(vo)) {
			map.put("retCode", "OK");
			map.put("vo", vo);
		}
		else {
			map.put("retCode","FAIL");
		}
		resp.setContentType("application/json; charset=utf-8");
		try {
			resp.getWriter().print(gson.toJson(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
