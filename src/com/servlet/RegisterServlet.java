package com.servlet;

import com.bean.Account;
import com.dao.AccountDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取前台提交的Phone和密码
        String accountId = request.getParameter("accountId");
        String accountPwd = request.getParameter("accountPwd");
        // 实例化实体对象

        //注册，插入数据库中，对数据进行封装，自己封装成一个对象
        Account account = new Account(accountId, accountPwd,0,0);
        //保存对象到数据库 accountDAO
        AccountDAO accountDAO = new AccountDAO();

        //判断是否有相同的id
        Integer count = accountDAO.selectAccountIdCount(accountId);
        if (count > 0) {
            //数据库中存在该用户，通过response对象给客户端一个错误提示
            PrintWriter writer = response.getWriter();
            ((PrintWriter) writer).write("<script>");
            writer.write("alert('申请注册的id已经被占用！');");
            writer.write("window.location.href='register.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        } else {

            boolean flag = accountDAO.saveAccount(account);
            if (flag) {
                //      注册成功跳转到登陆界面 重定向
                response.sendRedirect("/Management/login.jsp");
            } else {
                //        失败返回注册界面 请求转发
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
                dispatcher.forward(request, response);
            }

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
