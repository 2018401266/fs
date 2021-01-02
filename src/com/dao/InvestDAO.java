package com.dao;

import com.bean.Invest;
import com.utils.DBUtils;

import java.util.List;

public class InvestDAO {
    //保存投资记录
    public boolean saveInvest(Invest invest) {

        String sql = "insert into invest(accountId,productId,investValue)"+
                "VALUES(?,?,?);";
        return DBUtils.save(sql,invest.getAccountId(),invest.getProductId(),invest.getInvestValue());
    }

    //分页查询
    public List<Invest> getInvestListByPage(String sql) {
        List<Invest> InvestList = DBUtils.getList(Invest.class,sql);
        return InvestList;
    }
    //查询总行数
    public Integer getInvestCount(Object...param) {
        String sql = "select count(*) from invest where accountId=?";
        Integer count = DBUtils.getCount(sql,param);
        return count;
    }
    //删除投资记录
    public boolean removeInvest(Invest invest){
        String sql ="delete from invest where productId=?";
        boolean flag = DBUtils.delete(sql,invest.getProductId());
        return flag;
    }
}
