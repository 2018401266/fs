package com.dao;

import com.bean.Account;
import com.utils.DBUtils;

public class AccountDAO {
    public boolean updateAccountInfo(Account account){//传入账户对象，通过账户ID更新账户信息
        String sql = "update account set accountPwd=? where accountId=?";
        boolean flag = DBUtils.update(sql,account.getAccountPwd(),account.getAccountId());
        return flag;
    }
    //根据家庭账户和密码查询账户是否存在
    public static Account getAccountByIdAndPwd(String accountId, String accountPwd) {
        String sql = "select accountId, accountPwd, totalAsset, totalInvest from account " +
                "where accountId=? and accountPwd=?";
        return DBUtils.getSingleObj(Account.class,sql,accountId,accountPwd);
    }
    //保存注册对象
    public boolean saveAccount(Account account){

        String sql = "insert into account(accountId,accountPwd,totalAsset,totalInvest)" +
                "values(?,?,?,?)";
        return DBUtils.save(sql,account.getAccountId(),account.getAccountPwd(),account.getTotalAsset(),account.getTotalInvest());

    }

    public Integer selectAccountIdCount(String accountId) {
        String sql = "select count(*) from account where accountId=?";
        return DBUtils.getCount(sql,accountId);
    }
}
