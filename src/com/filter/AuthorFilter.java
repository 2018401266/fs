package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthorFilter")
public class AuthorFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取根目录所对应的绝对路径
        String currentURL = request.getRequestURI();
        //截取到当前文件名用于比较
        String targetURL = currentURL.substring(currentURL.indexOf("/",1),currentURL.length());
        //如果session不为空就返回该session，如果为空就返回null
        HttpSession session = request.getSession(false);
        if(!"/login.jsp".equals(targetURL)){
            //判断当前页面是否是重顶次昂后的登陆页面页面，如果是就不做session的判断，防止死循环
            if(session==null||session.getAttribute("SESSION_ACCOUNT")==null){
                //如果session为空表示用户没有登陆就重定向到login.jsp页面
                //System.out.println("request.getContextPath()=" + request.getContextPath());
                response.sendRedirect(request.getContextPath()+"/login.jsp");
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {


    }

}
