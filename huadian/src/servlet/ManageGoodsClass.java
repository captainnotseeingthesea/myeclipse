package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GoodsClassService;

import model.GoodsClass;
import model.PageBean;
import model.Table;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ManageGoodsClass extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		GoodsClassService goodsClassService=new GoodsClassService();
		int pageNum=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("limit"));
		int count=goodsClassService.queryCount();
		PageBean<GoodsClass> pageBean=new PageBean<GoodsClass>(pageNum, pageSize,count);
		Table<GoodsClass> table=goodsClassService.queryGoodsClass(pageBean);
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("code", 0);
	    result.put("msg", "");
	    result.put("count",count);
	    JSONArray array=JSONArray.fromObject(table.getList());
	    result.put("data", array);
	    out.println(JSONObject.fromObject(result));
	    System.out.println(JSONObject.fromObject(result));
	    //System.out.println(JSONObject.fromObject(result));
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
