package com.dao;

import com.bean.Account;
import com.bean.Budget;
import com.bean.Member;
import com.utils.DBUtils;

import java.util.List;

public class BudgetDAO {
    //保存收支记录
    public Integer saveBudget(Budget budget) {

        String sql = "insert into budget(memberId,type,value,budgetDate,state)"+
                "VALUES(?,?,?,?,?);";
        return DBUtils.updateForPrimary(sql,budget.getMemberId(),budget.getType(),budget.getValue(),budget.getBudgetDate(),
                budget.getState());
    }
    //查询当前用户是否属于该家庭
    public boolean getMember(String memberId,String accountId){
        String sql="select count(*) from member where memberId=? and accountId=?";
        Integer count = DBUtils.getCount(sql,memberId,accountId);
        if(count>0) return true;
        else
            return false;
    }
    //查询总行数
    public Integer getBudgetCount(Object ...param) {
        String sql = "select count(*) from budget where memberId in(select memberId from member where accountId=?)";
        Integer count = DBUtils.getCount(sql,param);
        return count;
    }

    //查询得到数据集
    public List<Budget> getBudgetListByPage(String sql) {
        List<Budget> budgetList = DBUtils.getList(Budget.class,sql);
        return budgetList;
    }

}
