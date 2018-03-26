package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserControl;

public class Login extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("����get����");
		PrintWriter outPrintWriter=response.getWriter();
		response.setContentType("application/json;charset=utf-8");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer i=0;
		UserControl usControl=new UserControl();
		HttpSession session=request.getSession(true);
		System.out.println("����post����");
		PrintWriter outPrintWriter=response.getWriter();
//		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		String nameString=request.getParameter("username");
		String password=request.getParameter("password");
		String jsString="{\"i\":";
		StringBuffer jsonBuffer=new StringBuffer(jsString);
		try {
			if (usControl.query(nameString).size()==1) {
				User user=usControl.query(nameString).get(0);
				if(user.getPassword().equals(password))
				{					
					System.out.println("��¼�ɹ���");
					session.setAttribute("loginUsername",nameString);
					
				}
				else {
					i=1;
					System.out.println("�������");
				}
			}
			else {
				i=2;
				System.out.println("�û��������ڣ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonBuffer.append(i);
		jsonBuffer.append("}");
		outPrintWriter.println(jsonBuffer.toString());
//		outPrintWriter.println("{\"i\":1}");
		outPrintWriter.flush();
		outPrintWriter.close();
		System.out.println(jsonBuffer.toString());
		System.out.println((jsonBuffer.toString()).getClass());
		System.out.print(nameString+" "+password);
		
	}

}
