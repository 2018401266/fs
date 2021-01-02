package com.servlet;

import com.bean.Account;
import com.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.AccessControlContext;

@WebServlet(urlPatterns = "/AccountServlet")
public class AccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String method = request.getParameter("method");
        if(method.equals("update")){
            String npd = request.getParameter("newPwd");
            String npd2 = request.getParameter("newPwd2");
            if(npd.equals(npd2)){
                Account account = (Account) request.getSession().getAttribute("SESSION_ACCOUNT");
                account.setAccountPwd(npd);
                AccountDAO dao = new AccountDAO();
                if(dao.updateAccountInfo(account)){
                    //显示余额界面
                    PrintWriter writer = response.getWriter();
                    writer.write("<script>");
                    writer.write("alert('修改成功！');");
                    writer.write("window.location.href='index.jsp'");
                    writer.write("</script>");
                    writer.flush();
                    writer.close();

                }else {
                    //显示修改密码界面
                    PrintWriter writer = response.getWriter();
                    writer.write("<script>");
                    writer.write("alert('修改失败！');");
                    writer.write("window.location.href='index.jsp'");
                    writer.write("</script>");
                    writer.flush();
                    writer.close();

                }

            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
