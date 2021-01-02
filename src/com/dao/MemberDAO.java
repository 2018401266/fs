package com.dao;

import com.bean.Member;
import com.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    //保存家庭成员
    public boolean saveMember(Member member) {

        String sql = "insert into member(memberId,accountId,memberName,memberRelation,memberPhone)values(?,?,?,?,?)";
        return DBUtils.save(sql, member.getMemberId(),member.getAccountId(), member.getMemberName(),
                member.getMemberRelation(), member.getMemberPhone());

    }
    //修改家庭成员信息
    public boolean updateMember(Member member){
        String sql = "update member set memberName=?, memberRelation=?, memberPhone=? where memberId=?";
        return DBUtils.update(sql,member.getMemberName(),member.getMemberRelation(),
                member.getMemberPhone(),member.getMemberId());
    }
    //一个账号对应一个家庭成员
    public Integer selectMemberIdCount(String memberId) {
        String sql = "select count(*) from member where memberId=?";
        return DBUtils.getCount(sql,memberId);
    }
    //分页查询
    public List<Member> getMemberListByPage(String sql) {
        List<Member> memberList = DBUtils.getList(Member.class,sql);
        return memberList;
    }
    //查询总行数
    public Integer getMemberCount(Object...param) {
        String sql = "select count(*) from member where accountId=?";
        Integer count = DBUtils.getCount(sql,param);
        return count;
    }
    //删除用户
    public boolean removeMember(Object...param){
        String sql ="delete from member where memberId=?";
        boolean flag = DBUtils.delete(sql,param);
        return flag;
    }
}

