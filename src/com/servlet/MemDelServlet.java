package com.servlet;

import com.dao.MemberDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MemDelServlet")
public class MemDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        String memberId = request.getParameter("memberId");
        MemberDAO dao = new MemberDAO();
        boolean flag = dao.removeMember(memberId);
        if (flag) {
            writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('删除成功！');");
            writer.write("</script>");
        } else {
            writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('删除失败！');");
            writer.write("</script>");
        }
        writer.flush();
        writer.close();
        request.getRequestDispatcher("index.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
