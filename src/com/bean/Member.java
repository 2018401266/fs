package com.bean;

public class Member {
    private String memberId;
    private String accountId;//家庭成员所在家庭编号
    private String memberName;
    private String memberRelation;
    private String memberPhone;

    public Member() {
    }

    public Member(String memberId, String accountId, String memberName, String memberRelation, String memberPhone) {
        this.memberId = memberId;
        this.accountId = accountId;
        this.memberName = memberName;
        this.memberRelation = memberRelation;
        this.memberPhone = memberPhone;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberRelation() {
        return memberRelation;
    }

    public void setMemberRelation(String memberRelation) {
        this.memberRelation = memberRelation;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }


    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", accountId=" + accountId +
                ", memberName='" + memberName + '\'' +
                ", memberRelation='" + memberRelation + '\'' +
                ", memberPhone='" + memberPhone +
                '}';
    }


}
