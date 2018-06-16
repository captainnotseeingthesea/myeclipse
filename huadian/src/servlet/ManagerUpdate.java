package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ManagerService;

import model.Err;

public class ManagerUpdate extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Manager manager=new Manager();
		ManagerService managerService=new ManagerService();
		HttpSession session=request.getSession(true);
		String old_password=request.getParameter("old_password");
		String new_password=request.getParameter("new_password");
		String renew_password=request.getParameter("renew_password");
		Err err=new Err();
		if(session.getAttribute("manager")!=null){
			String managerName=session.getAttribute("manager").toString();
			err=managerService.managerUpdate(old_password, new_password, renew_password, managerName);
		}
		else{
			err.setErrno(6);
			err.setErrmsg("会话失效，请重新登陆");
		}
		//System.out.println(old_password+" "+new_password+" "+renew_password );
		
		String jsString="{\"errno\":";
		StringBuffer jsonBuffer=new StringBuffer(jsString);
		jsonBuffer.append(err.getErrno());
		jsonBuffer.append(",\"errmsg\":");
		jsonBuffer.append("\""+err.getErrmsg()+"\"}");
		out.println(jsonBuffer);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
