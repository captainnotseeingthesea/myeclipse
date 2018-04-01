package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.GoodsService;
import service.GoodsWantService;

import model.Goods;

public class CancelGoods extends HttpServlet {

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

		response.setContentType("text/html,UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession(true);
		GoodsService goodsService=new GoodsService();
		GoodsWantService goodsWantService=new GoodsWantService();
		if(session==null|session.getAttribute("loginUsername")==null){
			response.sendRedirect("/huadian/html/index.jsp?reserve=0");
			return;
		}
		else{
			if(request.getParameter("mark")!=null){
				if(Integer.parseInt(request.getParameter("mark"))==1){
					Integer goodsId=Integer.parseInt(request.getParameter("goodsId"));
					Goods goods=goodsService.queryGoodsById(goodsId);
					goods.setStatus(4);
					goods.setCancelDate(new Timestamp(System.currentTimeMillis()));
					goodsService.updateGoods(goods);
					response.sendRedirect("/huadian/html/user.jsp?cancel=1");
					return;
				}
				else if (Integer.parseInt(request.getParameter("mark"))==2) {
					Integer goodsId=Integer.parseInt(request.getParameter("goodsId"));
					Goods goods=goodsService.queryGoodsById(goodsId);
					goods.setStatus(1);
					goodsService.updateGoods(goods);
					response.sendRedirect("/huadian/html/user.jsp?cancel=2");
					return;
				}
				else if (Integer.parseInt(request.getParameter("mark"))==3) {
					Integer goodsId=Integer.parseInt(request.getParameter("goodsId"));
					Goods goods=goodsService.queryGoodsById(goodsId);
					goods.setBuyDate(new Timestamp(System.currentTimeMillis()));
					goods.setStatus(3);
					goodsService.updateGoods(goods);
					response.sendRedirect("/huadian/html/user.jsp?cancel=3");
					return;
				}
				else if (Integer.parseInt(request.getParameter("mark"))==4) {
					Integer goodsId=Integer.parseInt(request.getParameter("goodsId"));
					Goods goods=goodsWantService.queryGoodsById(goodsId);
					goods.setBuyDate(new Timestamp(System.currentTimeMillis()));
					goods.setStatus(4);
					goodsWantService.updateGoods(goods);
					response.sendRedirect("/huadian/html/user.jsp?cancel=4");
					return;
				}
				else if (Integer.parseInt(request.getParameter("mark"))==5) {
					Integer goodsId=Integer.parseInt(request.getParameter("goodsId"));
					Goods goods=goodsWantService.queryGoodsById(goodsId);
					goods.setBuyDate(new Timestamp(System.currentTimeMillis()));
					goods.setStatus(1);
					goodsWantService.updateGoods(goods);
					response.sendRedirect("/huadian/html/user.jsp?cancel=5");
					return;
				}
			}
		}
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
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
