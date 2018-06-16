package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import service.UserService;

import model.Err;
import model.User;

public class UpdateUsers extends HttpServlet {

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
		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession(true);
		PrintWriter out = response.getWriter();
		UserService userService=new UserService();
		User user=new User();
		System.out.println(request.getParameter("id")+" "+request.getParameter("name")+" "+request.getParameter("password"));
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setNameString(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		Err err=new Err();
		if(session.getAttribute("manager")!=null){
			err=userService.updateUser(user);
		}
		else{
			err.setErrno(6);
			err.setErrmsg("会话失效，请重新登陆");
		}
		System.out.println(JSONObject.fromObject(err));
		out.println(JSONObject.fromObject(err));
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
		HttpSession session=request.getSession(true);
		PrintWriter out = response.getWriter();
		UserService userService=new UserService();
		User user=new User();
		System.out.println(request.getParameter("id"));
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setNameString(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		Err err=new Err();
		if(session.getAttribute("manager")!=null){
			err=userService.updateUser(user);
		}
		else{
			err.setErrno(6);
			err.setErrmsg("会话失效，请重新登陆");
		}
		System.out.println(JSONObject.fromObject(err));
		out.println(JSONObject.fromObject(err));
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
