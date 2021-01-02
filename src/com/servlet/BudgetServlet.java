package com.servlet;

import com.bean.Account;
import com.bean.Budget;
import com.bean.PageBean;
import com.dao.BudgetDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/BudgetServlet")
public class BudgetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String accountId = (String) request.getSession().getAttribute("SESSION_ACCOUNTID");
        String method = request.getParameter("method");
        if ("add".equals(method)) {
            //封装对象
            Budget budget = requestDataObj(request);
            BudgetDAO budgetDAO = new BudgetDAO();
            if (budgetDAO.getMember(budget.getMemberId(), accountId)) {
                Account account = (Account) request.getSession().getAttribute("SESSION_ACCOUNT");
                if (budget.getState().equals("收入")) {
                    account.setTotalAsset(account.getTotalAsset() + budget.getValue());
                } else if (budget.getState().equals("支出")) {
                    account.setTotalAsset(account.getTotalAsset() - budget.getValue());
                }
                request.getSession().setAttribute("SESSION_ACCOUNT", account);
                //将对象保存到数据库
                Integer budgetId = budgetDAO.saveBudget(budget);
                //将当前登录用户的简历ID，保存到Session中
                request.getSession().setAttribute("SESSION_BUDGETID", budgetId);

                //跳转到简历信息页面，展示刚添加的简历信息
                budget.setBudgetId(budgetId);
                //简历放入请求作用域，展示该对象信息
                request.setAttribute("budget", budget);
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('记账成功！');");
                writer.write("window.location.href='index.jsp'");
                writer.write("</script>");
                //请求转发到简历展示界面
                // request.getRequestDispatcher("index.jsp").forward(request,response);
            } else {
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('记账人不是该家庭成员！');");
                writer.write("window.location.href='index.jsp'");
                writer.write("</script>");
            }

        }else {
            request.setCharacterEncoding("UTF-8");

            //1.每页多少行 pageSize

            String pageSizeStr = request.getParameter("pageSize");
            Integer pageSize = null;
            if (pageSizeStr != null && pageSizeStr.length() > 0) {
                pageSize = Integer.valueOf(pageSizeStr);
            } else {
                pageSize = 10;
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
            BudgetDAO budgetDAO = new BudgetDAO();
            totalRows = budgetDAO.getBudgetCount(accountId);


            //limit startRow,pageSize;
            //5.起始行 startRow
            Integer startRow = (currentPage - 1) * pageSize;

            StringBuffer sqlRow = new StringBuffer(
                    "select * from budget where memberId in " +
                            "(select memberId from member where accountId=" + accountId + ")"
            );
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

            List<Budget> budgetList = budgetDAO.getBudgetListByPage(sqlRow.toString());

            PageBean pageBean = new PageBean(currentPage, pageSize, totalRows, budgetList);

            request.setAttribute("bpageBean", pageBean);

            request.getRequestDispatcher("showBudget.jsp").forward(request, response);
        }

    }
    


private Budget requestDataObj(HttpServletRequest request) {
        String budgetId = request.getParameter("budgetId");
        String memberId = request.getParameter("memberId");
        String type = request.getParameter("type");
        String valueStr = request.getParameter("budgetValue");
        String budgetDateStr = request.getParameter("budgetDate");
        String state = request.getParameter("state");
        Date day = null;
        double value = Double.parseDouble(valueStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            day = sdf.parse(budgetDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Budget budget = new Budget(null, memberId, type, value, day, state);
        return budget;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
