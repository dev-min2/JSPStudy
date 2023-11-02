package co.yedam.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class GetStudentServlet
 */
@WebServlet("/getStudent.do")
public class GetStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
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
    	Map<String,Object> jsonMap = new HashMap<>();
    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
    	StudentService svc = new StudentServiceImpl();
    	StudentVO ret = svc.getStudent(stuID);
    	
    	if(ret != null) {
    		jsonMap.put("retCode", "OK");
    		jsonMap.put("student", ret);
    	}
    	else { 
    		jsonMap.put("retCode", "FAIL");
    	}
    	 
    	String jsonData = gson.toJson(jsonMap);
    	
    	PrintWriter out = resp.getWriter();
    	out.println(jsonData);
    	out.flush();
    }
}
