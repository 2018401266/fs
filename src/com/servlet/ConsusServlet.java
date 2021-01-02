package com.servlet;

import com.bean.Budget;
import com.bean.Charts;
import com.bean.PageBean;
import com.dao.ConsusDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ConsusServlet")
public class ConsusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String type = request.getParameter("type");
        String accountId = (String) request.getSession().getAttribute("SESSION_ACCOUNTID");
        String mon = request.getParameter("month");
        String year = request.getParameter("year");
        ConsusDAO consusDAO = new ConsusDAO();
        if(year==null){
            List<Charts> monthlyList = null;
            String sql5="select state as type,sum(budget.value) as chartsValue from budget where memberId in " +
                    "(select memberId from member where accountId = "+accountId+ ") " +
                    "and budgetDate LIKE '"+mon+"%' group by state";
            monthlyList = consusDAO.getList(sql5,mon,accountId);
            request.setAttribute("monthList",monthlyList);

            List<Charts> monthlyIncomeList = null;
            String sql="select type,sum(budget.value) as chartsValue from budget where memberId in " +
                    "(select memberId from member where accountId = "+accountId+ ") " +
                    "and budgetDate LIKE '"+mon+"%' and budget.state='收入' group by type";
            monthlyIncomeList = consusDAO.getList(sql,mon,accountId);
            request.setAttribute("monthIncomeList",monthlyIncomeList);

            List<Charts> monthlyExpenseList = null;
            String sql2="select type,sum(budget.value) as chartsValue from budget where memberId in " +
                    "(select memberId from member where accountId = "+accountId+ ") " +
                    "and budgetDate LIKE '"+mon+"%' and budget.state='支出' group by type";
            monthlyExpenseList = consusDAO.getList(sql2,mon,accountId);
            request.setAttribute("monthExpenseList",monthlyExpenseList);

            request.getRequestDispatcher("monthpie.jsp").forward(request,response);

        }
        //if("month".equals(type)){//查询月统计


        //}
        if(mon==null){
            List<Charts> yearlyList = null;
            String sql5="select state as type,sum(budget.value) as chartsValue from budget where memberId in " +
                    "(select memberId from member where accountId = "+accountId+ ") " +
                    "and budgetDate LIKE '"+year+"%' group by state";
            yearlyList = consusDAO.getList(sql5,mon,accountId);
            request.setAttribute("monthList",yearlyList);

            List<Charts> yearlyIncomeList = null;
            String sql3="select type,sum(budget.value) as chartsValue from budget where memberId in " +
                    "(select memberId from member where accountId = "+accountId+ ") " +
                    "and budgetDate LIKE '"+year+"%' and budget.state='收入' group by type";
            yearlyIncomeList = consusDAO.getList(sql3,mon,accountId);
            request.setAttribute("monthIncomeList",yearlyIncomeList);

            List<Charts> yearlyExpenseList = null;
            String sql4="select type,sum(budget.value) as chartsValue from budget where memberId in " +
                    "(select memberId from member where accountId = "+accountId+ ") " +
                    "and budgetDate LIKE '"+year+"%' and budget.state='支出' group by type";
            yearlyExpenseList = consusDAO.getList(sql4,mon,accountId);
            request.setAttribute("monthExpenseList",yearlyExpenseList);

            request.getRequestDispatcher("year.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
