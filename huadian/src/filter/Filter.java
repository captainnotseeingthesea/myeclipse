package filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filter
 */
public class Filter implements javax.servlet.Filter {
				/*��Ҫ�ų���ҳ��*/
	private String excludedPages;       
	private String[] excludedPageArray; 
    /**
     * Default constructor. 
     */
    public Filter() {
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		excludedPages = fConfig.getInitParameter("excludedPages");     
		if (excludedPages.length()>0) {     
		excludedPageArray = excludedPages.split(",");     
		}         
		} 
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		boolean isExcludedPage = false; 
		String a=((HttpServletRequest)request).getRequestURI();
		if(!a.contains(".jsp")){
            //���������css����js�ļ���ֱ�ӷ���
               chain.doFilter(request, response);
               return;
        }else{
			for (String page : excludedPageArray) {//�ж��Ƿ��ڹ���url֮��     
				if(((HttpServletRequest) request).getServletPath().equals(page)){     
					isExcludedPage = true;     
				break;     
				}     
			}  
			
			if(isExcludedPage){//�ٹ���url֮��
				chain.doFilter(request, response);  
				return;
			}else{
				HttpSession session=((HttpServletRequest)request).getSession();
				if(session==null|session.getAttribute("loginUsername")==null){
					((HttpServletResponse)response).sendRedirect("/huadian/html/index.jsp?reserve=0");
				}
				else{
					chain.doFilter(request, response);
					return;
				}
			}
		
	}
	}

}
