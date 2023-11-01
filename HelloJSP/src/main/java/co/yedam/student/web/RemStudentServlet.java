package co.yedam.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;
import co.yedam.student.serviceImpl.StudentServiceImpl;

/**
 * Servlet implementation class RemStudentServlet
 */
@WebServlet("/delStudent.do")
public class RemStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stubz
    	super.init(config);
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	req.setCharacterEncoding("UTF-8");

    	String stuID = req.getParameter("studentId");
    	if(stuID == null || stuID.isEmpty()) {
    		return;
    	}
    	
    	resp.setContentType("application/json; charset=UTF-8");
    	StudentService svc = new StudentServiceImpl();
    	boolean ret = svc.removeStudent(stuID);
    	
    	PrintWriter out = resp.getWriter();
    	// {"retCode":"OK"}
    	String jsonResult = "{\"retCode\":";
    	if(ret)
    		jsonResult += "\"OK\"}";
    	else
    		jsonResult += "\"FAIL\"}";
    	
    	out.println(jsonResult);
    	out.flush();
    }

}
