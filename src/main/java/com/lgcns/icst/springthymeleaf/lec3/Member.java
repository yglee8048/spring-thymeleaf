package com.lgcns.icst.springthymeleaf.lec3;

public class Member {

    private String memberId;
    private String memberPw;
    private String memberName;
    private Integer point;

    public Member(String memberId, String memberPw, String memberName, Integer point) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.point = point;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public String getMemberName() {
        return memberName;
    }

    public Integer getPoint() {
        return point;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
