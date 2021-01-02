package com.dao;

import com.bean.Budget;
import com.bean.Charts;
import com.bean.Member;
import com.utils.DBUtils;

import java.util.List;
import java.util.Map;

public class ConsusDAO {//统计
    public List<Budget> getMonthlyIncome(String date,String accountId) {
        String sql="select * from budget where memberId in " +
                "(select memberId from member where accountId = "+accountId+ ") " +
                "and budgetDate LIKE '"+date+"%' and budget.state='收入'";
        List<Budget> monthIncomeList = DBUtils.getList(Budget.class,sql);
        return monthIncomeList;
    }
    public Integer getMonthlyIncomeCount(String date,String accountId) {
        String sql="selece count(*) from budget where memberId in " +
                "(select memberId from member where accountId = "+accountId+ ") " +
                "and budgetDate LIKE '"+date+"%' and budget.state='收入'";
        Integer count = DBUtils.getCount(sql);
        return count;
    }
    public List<Charts> getList(String sql,String date, String accountId){
        List<Charts> chartsList = DBUtils.getList(Charts.class,sql);
        return chartsList;

    }
}
