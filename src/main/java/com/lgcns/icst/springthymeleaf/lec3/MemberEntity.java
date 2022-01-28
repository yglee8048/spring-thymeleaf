package com.lgcns.icst.springthymeleaf.lec3;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public class MemberEntity {

    @NotEmpty(message = "아이디는 필수 값입니다.")
    @Max(value = 8, message = "아이디는 최대 8글자입니다.")
    private String memberId;
    private String memberPw;
    private String memberName;
    private Integer point;

    public MemberEntity(String memberId, String memberPw, String memberName, Integer point) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.point = point;
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
}
