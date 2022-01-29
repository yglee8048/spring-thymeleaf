package com.lgcns.icst.springthymeleaf.lec4.dto;

import com.lgcns.icst.springthymeleaf.lec4.constant.MemberRank;
import com.lgcns.icst.springthymeleaf.lec4.entity.MemberEntity;

public class MemberDTO {

    private String memberId;
    private String memberPw;
    private String memberName;
    private Integer point;
    private MemberRank rank;

    public MemberDTO() {
    }

    public MemberDTO(MemberEntity memberEntity) {
        this.memberId = memberEntity.getMemberId();
        this.memberPw = memberEntity.getMemberPw();
        this.memberName = memberEntity.getMemberName();
        this.point = memberEntity.getPoint();
    }

    public MemberEntity toEntity() {
        return new MemberEntity(memberId, memberPw, memberName, point);
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

    public String getRank() {
        return rank.name();
    }

    public void setRank(String rank) {
        this.rank = MemberRank.valueOf(rank);
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
