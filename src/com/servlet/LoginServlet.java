package com.servlet;

import com.bean.Account;
import com.dao.AccountDAO;
import com.utils.CookieEncryptTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String accountId = request.getParameter("accountId");
        String accountPwd = request.getParameter("accountPwd");

        AccountDAO dao = new AccountDAO();
        Account account = dao.getAccountByIdAndPwd(accountId,accountPwd);
//判断account = null，返回登录页面，不为空，就进入主页面

        if(account!=null){
            //将登陆用户保存到session
            request.getSession().setAttribute("SESSION_ACCOUNT",account);
            request.getSession().setAttribute("SESSION_ACCOUNTID",accountId);
            response.sendRedirect("index.jsp");

        }else{
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('用户名或密码错误！');");
            writer.write("window.location.href='login.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
