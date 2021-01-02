package com.servlet;

import com.bean.*;
import com.bean.Invest;
import com.dao.InvestDAO;
import com.dao.MemberDAO;
import com.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/InvestServlet")
public class InvestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getParameter("method");
        PrintWriter writer = null;
        if ("add".equals(method)) {
            //封装对象
            Invest invest = requestDataObj(request);
            InvestDAO investDAO = new InvestDAO();
            //将对象保存到数据库
            boolean flag = investDAO.saveInvest(invest);
            if (flag) {
                Account account = (Account) request.getSession().getAttribute("SESSION_ACCOUNT");
                account.setTotalInvest(account.getTotalInvest() + invest.getInvestValue());
                account.setTotalAsset(account.getTotalAsset() - invest.getInvestValue());
                request.getSession().setAttribute("SESSION_ACCOUNT", account);
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

        } else if (method.equals("show")) {
            //1.每页多少行 pageSize

            String pageSizeStr = request.getParameter("pageSize");
            Integer pageSize = null;
            if (pageSizeStr != null && pageSizeStr.length() > 0) {
                pageSize = Integer.valueOf(pageSizeStr);
            } else {
                pageSize = 5;
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
            InvestDAO InvestDAO = new InvestDAO();

            String accountId = (String) request.getSession().getAttribute("SESSION_ACCOUNTID");
            totalRows = InvestDAO.getInvestCount(accountId);

            //5.起始行 startRow
            Integer startRow = (currentPage - 1) * pageSize;

            StringBuffer sqlRow = new StringBuffer(
                    "select * from invest where accountId=" + accountId
            );
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
            //查询所有信息

            List<Invest> InvestList = InvestDAO.getInvestListByPage(sqlRow.toString());

            PageBean pageBean = new PageBean(currentPage, pageSize, totalRows, InvestList);

            request.setAttribute("ipageBean", pageBean);

            request.getRequestDispatcher("investList.jsp").forward(request, response);
        }else{
            InvestDAO investDAO = new InvestDAO();
            Invest invest = requestDataObj(request);
            boolean flag = investDAO.removeInvest(invest);
            if (flag) {
                Account account = (Account) request.getSession().getAttribute("SESSION_ACCOUNT");
                account.setTotalInvest(account.getTotalInvest() - invest.getInvestValue());
                account.setTotalAsset(account.getTotalAsset() + invest.getInvestValue());
                request.getSession().setAttribute("SESSION_ACCOUNT", account);
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
    }

    private Invest requestDataObj(HttpServletRequest request) {
        String productId = request.getParameter("productId");
        String investValueStr = request.getParameter("investValue");
        double investValue = Double.parseDouble(investValueStr);

        //从session取出登录账户的id
        String accountId = (String) request.getSession().getAttribute("SESSION_ACCOUNTID");
        Invest invest = new Invest(accountId, productId, investValue);
        return invest;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
