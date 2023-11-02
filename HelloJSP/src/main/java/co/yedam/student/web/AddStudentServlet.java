package co.yedam.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;
import co.yedam.student.serviceImpl.StudentServiceImpl;

/**
 * Servlet implementation class RemStudentServlet
 */
@WebServlet("/addStudent.do")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
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

    	String stuID = req.getParameter("sId");
    	String stuName = req.getParameter("sName");
    	String stuPass = req.getParameter("sPass");
    	String stuDept = req.getParameter("sDept");
    	String stuBirth = req.getParameter("sBirth");
    	
    	StudentVO vo = new StudentVO();
    	vo.setStudentId(stuID);
    	vo.setStudentName(stuName);
    	vo.setStudentPassword(stuPass);
    	vo.setStudentDept(stuDept);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			vo.setStudentBirthday(sdf.parse(stuBirth));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	resp.setContentType("application/json; charset=UTF-8");
    	StudentService svc = new StudentServiceImpl();
    	
    	boolean ret = svc.addStudent(vo);
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
