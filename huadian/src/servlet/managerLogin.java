package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Manager;
import model.ManagerControl;

public class managerLogin extends HttpServlet {

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
		Integer errno;//返回状态
		String errmsg;//返回状态信息
		Integer remember=0;//返回remember状态
		response.setContentType("application/json;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Manager manager=new Manager();
		ManagerControl managerControl=new ManagerControl();
		manager.setManagerName(request.getParameter("managerName"));
		manager.setManagerPassword(request.getParameter("managerPassword"));
		String jsString="{\"errno\":";
		StringBuffer jsonBuffer=new StringBuffer(jsString);
		if(manager.getManagerName()!=null){
			if(manager.getManagerPassword()!=null){
				Manager manager1=managerControl.queryManager(manager);
				if(manager1!=null){
					if(security.MD5.MD5(manager.getManagerPassword()).equals(manager1.getManagerPassword())){
						errno=0;//登陆成功
						errmsg="登陆成功";
						HttpSession session=request.getSession(true);
						if(request.getParameter("remember")!=null){
							if(request.getParameter("remember").equals("on")){
								remember=1;
							}else{
								remember=0;	
								}
						}
						
						session.setAttribute("manager", manager.getManagerName());
					}
					else{
						errno=1;//密码错误
						errmsg="密码错误";
					}
				}
				else {
					errno=2;//用户名不存在
					errmsg="用户名不存在";
				}
			}else{
				errno=3;//密码为空
				errmsg="密码为空";
			}
		}else {
			errno=4;//用户名为空
			errmsg="用户名为空";
		}

		jsonBuffer.append(errno);
		jsonBuffer.append(",\"errmsg\":");
		jsonBuffer.append("\""+errmsg+"\"");
		jsonBuffer.append(",\"remember\":");
		jsonBuffer.append("\""+remember+"\"");
		jsonBuffer.append("}");
		out.println(jsonBuffer.toString());
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
