package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserControl;

public class Register extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Register() {
		super();
	}

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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer tempBuffer=new StringBuffer("{\"temp\":");
		Integer temp=0;
		UserControl usControl = new UserControl();
		User user = new User();
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		if (username.length() > 10 || username.length() < 1) {
			System.out.print("用户名格式不正确！");
			temp=1;
		} else {
			try {
				if (usControl.query(username).size() == 0) {
					if (password1.length() > 0 || password2.length() > 0) {
						if (password1.equals(password2)) {
							user.setNameString(username);
							user.setPassword(password1);
							try {
								usControl.addUser(user);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.print("注册成功！");
						} else {
							temp=2;
							System.out.println("两次密码不匹配");
						}
					} else {
						temp=3;
						System.out.print("密码不可为空！");
					}
				}
				else{
					temp=4;
					System.out.print("用户名已经存在");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(username + " " + password1 + " " + password2);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		tempBuffer.append(temp);
		tempBuffer.append("}");
		System.out.println(tempBuffer.toString());
		out.println(tempBuffer.toString());
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
