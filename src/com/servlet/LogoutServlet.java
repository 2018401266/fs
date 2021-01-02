package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 获得用户会话，使其失效
        request.getSession().invalidate();
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open ('"+request.getContextPath()+"/login.jsp','_top')");
        out.println("</script>");
        out.println("</html>");


    }

}
