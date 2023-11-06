package co.yedam.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;

public class LoginFormControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		if(req.getSession().getAttribute("login") != null) {
			//resp.sendRedirect("");
		}
		
		String path = "WEB-INF/main/loginForm.jsp";
		try {
			req.getRequestDispatcher(path).forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
