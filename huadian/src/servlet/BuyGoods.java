package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Goods;
import model.User;

import service.GoodsService;
import service.UserService;

public class BuyGoods extends HttpServlet {

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

		response.setContentType("text/html,UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession(true);
		GoodsService goodsService=new GoodsService();
		UserService userService=new UserService();
		User user=new User();
		if(session.getAttribute("loginUsername")!=null){
			String username=session.getAttribute("loginUsername").toString();
			Integer goodsId=Integer.parseInt(request.getParameter("goodsId"));
			String contact=request.getParameter("contact");
			Goods goods=goodsService.queryGoodsById(goodsId);
			goods.setReserveDate(new Timestamp(System.currentTimeMillis()));
			goods.setBuyerContact(contact);
			user.setNameString(username);
			Integer buyerId=userService.queryUser(user).getId();
			goods.setBuyerId(buyerId);
			goods.setReserveDate(new Timestamp(System.currentTimeMillis()));
			goods.setStatus(2);
			goodsService.updateGoods(goods);
			response.sendRedirect("/huadian/html/index.jsp?reserve=1");
		}
		else{
			response.sendRedirect("/huadian/html/index.jsp?reserve=0");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	public static void main(String[] args) {
		Date date=new Date();
		System.out.println(date);
	}
}
