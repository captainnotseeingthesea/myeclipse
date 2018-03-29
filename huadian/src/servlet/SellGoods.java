package servlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Goods;
import model.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ThumbnaillatorTest.ThumbTest;

import service.GoodsService;
public class SellGoods extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // �ϴ��ļ��洢Ŀ¼
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // �ϴ�����
    private static final int MEMORY_THRESHOLD   = 1024 * 1024;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 
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

		response.setContentType("text/html;charset=utf-8");
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
//		out.flush();
//		out.close();
		RequestDispatcher dispatcher=request.getRequestDispatcher("../html/index.jsp");
//		response.sendRedirect("../html/index.jsp");
		dispatcher.forward(request, response);
;	}

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
		HttpSession session=request.getSession(true);
		String username=session.getAttribute("loginUsername").toString();
		Goods goods=new Goods();//����һ����Ʒ���� 
		//���ϴ����ļ����б��
		Integer folderCount=1;
		Integer fileCount=1;
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// ����Ƿ�Ϊ��ý���ϴ�
		if (!ServletFileUpload.isMultipartContent(request)) {
		    // ���������ֹͣ
		    PrintWriter writer = response.getWriter();
		    writer.println("Error: ��������� enctype=multipart/form-data");
		    writer.flush();
		    return;
		}
 
        // �����ϴ�����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // ������ʱ�洢Ŀ¼
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // ��������ļ��ϴ�ֵ
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // �����������ֵ (�����ļ��ͱ�����)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // ���Ĵ���
        upload.setHeaderEncoding("UTF-8"); 

        // ������ʱ·�����洢�ϴ����ļ�
        // ���·����Ե�ǰӦ�õ�Ŀ¼
        String uploadPath1 = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        // ���Ŀ¼�������򴴽�
        System.out.println(uploadPath1);
        File uploadDir1 = new File(uploadPath1);
        if (!uploadDir1.exists()) {
            uploadDir1.mkdir();
        }
        String uploadPath = uploadPath1+File.separator+username;
        String uploadPathString="";
        // ���Ŀ¼�������򴴽�
        System.out.println(uploadPath);
        File uploadDir = new File(uploadPath);
        try {
        @SuppressWarnings("unchecked")
        List<FileItem> formItems = upload.parseRequest(request);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        	
        	File d = new File(uploadPath);
        	File list[] = d.listFiles();
        	for(int i = 0; i < list.length; i++){
        	    if(!list[i].isFile()){
        	        folderCount++;
        	    }    
        	}
        	String goodsNameString="";
        	for(FileItem item:formItems){
        		if(item.isFormField()){
        			if (item.getFieldName().equals("goodsName")) {
						goodsNameString=item.getString("UTF-8");
					}
        		}
        	}
        	uploadPathString=uploadPath+File.separator+folderCount+"-"+goodsNameString;
            goods.setPicture(username+File.separator+folderCount+"-"+goodsNameString);
        	File uploadDir2 = new File(uploadPathString);
        	if (!uploadDir2.exists()) {
                uploadDir2.mkdir();
            }
            // ���������������ȡ�ļ�����
            if (formItems != null && formItems.size() > 0) {
                // ����������
                for (FileItem item : formItems) {
                    // �����ڱ��е��ֶ�
                    if (!item.isFormField()) {                       
//                    	String fileName = new File(item.getName()).getName();
                    	String fileName=(fileCount++).toString()+".jpg";
                    	String filePath = uploadPathString + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // �ڿ���̨����ļ����ϴ�·��
                        System.out.println(filePath);
                        // �����ļ���Ӳ��
                        item.write(storeFile);
                        request.setAttribute("message",
                            "�ļ��ϴ��ɹ�!");
                    }
                    
                    else{
                    	if(item.getFieldName().equals("goodsName")){
                    		goods.setGoodsName(item.getString("UTF-8"));
                    	}
                    	else if (item.getFieldName().equals("goodsClass")) {
							goods.setClassId(Integer.parseInt(item.getString()));
						}
                    	else if (item.getFieldName().equals("price")) {
							goods.setPrice(Double.valueOf(item.getString()));
						}
                    	else if (item.getFieldName().equals("sellerContact")) {
							goods.setSellerContact(item.getString("UTF-8"));
						}
                    	else {
							goods.setDescription(item.getString("UTF-8"));
						}
                    }
                }
                ThumbTest thumbTest=new ThumbTest();
                System.out.println(uploadPathString);
                thumbTest.test(uploadPathString);
                User user=new User();
                user.setNameString(username);
                GoodsService goodsService=new GoodsService();
                goodsService.addGoods(goods, user);
                response.sendRedirect("/huadian/html/user.jsp");
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "������Ϣ: " + ex.getMessage());
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

}
