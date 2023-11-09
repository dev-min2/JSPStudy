package co.yedam.admin.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.admin.service.MemberVO;
import co.yedam.common.Command;

public class MemberListControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String path = "admin/memberList.tiles";
		
		List<MemberVO> vos = new ArrayList<MemberVO>();
		MemberVO vo = new MemberVO();
		vo.setMid("M001");
		
		req.setAttribute("list", vos);
		
		try {
			req.getRequestDispatcher(path).forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
