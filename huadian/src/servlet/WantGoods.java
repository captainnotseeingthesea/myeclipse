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
import service.GoodsWantService;
public class WantGoods extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // 上传配置
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
		Goods goods=new Goods();//创建一个商品对象 
		//对上传的文件进行编号
		Integer folderCount=1;
		Integer fileCount=1;
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
		    // 如果不是则停止
		    PrintWriter writer = response.getWriter();
		    writer.println("Error: 表单必须包含 enctype=multipart/form-data");
		    writer.flush();
		    return;
		}
 
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath1 = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        // 如果目录不存在则创建
        System.out.println(uploadPath1);
        File uploadDir1 = new File(uploadPath1);
        if (!uploadDir1.exists()) {
            uploadDir1.mkdir();
        }
        String uploadPath = uploadPath1+File.separator+username;
        String uploadPathString="";
        // 如果目录不存在则创建
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
            // 解析请求的内容提取文件数据
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {                       
//                    	String fileName = new File(item.getName()).getName();
                    	String fileName=(fileCount++).toString()+".jpg";
                    	String filePath = uploadPathString + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                            "文件上传成功!");
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
                GoodsWantService goodsWantService=new GoodsWantService();
                goodsWantService.addGoods(goods, user);
                response.sendRedirect("/huadian/html/user.jsp");
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
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
