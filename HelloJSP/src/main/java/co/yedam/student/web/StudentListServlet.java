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
 * Servlet implementation class StudentListServlet
 */

@WebServlet("/studentList.do")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StudentListServlet() {
        super();
        System.out.println("생성자 call");
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stubz
    	super.init(config);
    	
    	System.out.println("init call!");
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	req.setCharacterEncoding("UTF-8");
    	//ㅇㅈ
    	Enumeration<String> enumer = req.getHeaderNames();
    	while(enumer.hasMoreElements()) {
    		String header = enumer.nextElement();
    		System.out.print(header + ": ");
    		System.out.println(req.getHeader(header));
    	}
    	String name = req.getParameter("name");
    	String age = req.getParameter("age");
    	
    	System.out.println("Service call!");
    	resp.setContentType("application/json; charset=UTF-8");
    	
    	StudentService svc = new StudentServiceImpl();
    	List<StudentVO> list = svc.listStudent();
    	
    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    	String jsonStr = gson.toJson(list);
    	
    	PrintWriter out = resp.getWriter();
    	out.print(jsonStr);
    	out.flush();
    }
    
    @Override
    public void destroy() {
    	// TODO Auto-generated method stub
    	//
    	//
    	//
    	System.out.println("destroy call!");
    }
}
