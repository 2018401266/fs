package com.servlet;

import com.bean.Account;
import com.bean.Member;
import com.bean.PageBean;
import com.dao.MemberDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/MemberServlet")
public class MemberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getParameter("method");
        PrintWriter writer = null;
        if ("add".equals(method)) {
            //封装对象
            Member member = requestDataObj(request);

            MemberDAO memberDAO = new MemberDAO();
            //将对象保存到数据库
            boolean flag = memberDAO.saveMember(member);
            if (flag) {
                writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('添加成功！');");
                writer.write("window.location.href='index.jsp'");
                writer.write("</script>");
            } else {
                writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('添加失败！');");
                writer.write("window.location.href='index.jsp#ap'");
                writer.write("</script>");
            }
            writer.flush();
            writer.close();

        }else if(method.equals("show")){
            //1.每页多少行 pageSize

            String pageSizeStr = request.getParameter("pageSize");
            Integer pageSize = null;
            if (pageSizeStr != null && pageSizeStr.length() > 0) {
                pageSize = Integer.valueOf(pageSizeStr);
            } else {
                pageSize = 20;
            }
            //2.当前第几页 currentPage
            String currentPageStr = request.getParameter("currentPage");
            Integer currentPage = null;
            if (currentPageStr != null && currentPageStr.length() > 0) {
                currentPage = Integer.valueOf(currentPageStr);
            } else {
                currentPage = 1;
            }
            //3.一共有多上行数据 totalRows
            Integer totalRows = 0;
            MemberDAO memberDAO = new MemberDAO();

            String accountId = (String)request.getSession().getAttribute("SESSION_ACCOUNTID");
            totalRows = memberDAO.getMemberCount(accountId);


            //limit startRow,pageSize;
            //5.起始行 startRow
            Integer startRow = (currentPage - 1) * pageSize;

            StringBuffer sqlRow = new StringBuffer(
                    "select memberId,accountId,memberName,memberRelation,memberPhone " +
                            "from member where member.accountId="+accountId
            );
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
            System.out.println(sqlRow.toString());
            //查询所有信息

            List<Member> memberList = memberDAO.getMemberListByPage(sqlRow.toString());

            PageBean pageBean = new PageBean(currentPage, pageSize, totalRows, memberList);

            request.setAttribute("pageBean", pageBean);

            request.getRequestDispatcher("member.jsp").forward(request, response);
        }else if("update".equals(method)) {
            //封装对象
            Member member = requestDataObj(request);
            MemberDAO memberDAO = new MemberDAO();
            //将对象保存到数据库
            boolean flag = memberDAO.updateMember(member);
            if (flag) {
                writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('修改成功！');");
                writer.write("window.location.href='index.jsp#'");
                writer.write("</script>");
            } else {
                writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('修改失败！');");
                writer.write("window.location.href='index.jsp#'");
                writer.write("</script>");
            }
            writer.flush();
            writer.close();

        }


    }
    //获取页面数据，封装成一个Member对象
    private Member requestDataObj(HttpServletRequest request) {
        String memberId = request.getParameter("memberId");
        String memberName = request.getParameter("memberName");
        String memberRelation = request.getParameter("memberRelation");
        String memberPhone = request.getParameter("memberPhone");

        //从session取出登录账户的id
        Account account = (Account) request.getSession().getAttribute("SESSION_ACCOUNT");
        Member member = new Member(memberId, null, memberName, memberRelation, memberPhone);
        member.setAccountId(account.getAccountId());
        return member;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
