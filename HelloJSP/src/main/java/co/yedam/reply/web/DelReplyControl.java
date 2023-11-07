package co.yedam.reply.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.reply.service.ReplyService;
import co.yedam.reply.serviceImpl.ReplyServiceImpl;

public class DelReplyControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String replyNo = req.getParameter("replyNo");
		
		ReplyService svc = new ReplyServiceImpl();
		String resultJson = "{\"retCode\":";
		resp.setContentType("application/json;charset=utf-8");
		if(svc.deleteReply(Integer.parseInt(replyNo))) {
			resultJson += "\"OK\"}";
		}
		else {
			resultJson += "\"FAIL\"}";
		}
		
		System.out.println(resultJson);
		
		try {
			resp.getWriter().print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
