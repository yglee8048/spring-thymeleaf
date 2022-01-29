package com.lgcns.icst.springthymeleaf.lec4.biz;

import com.lgcns.icst.springthymeleaf.lec4.constant.MemberRank;
import com.lgcns.icst.springthymeleaf.lec4.entity.MemberEntity;

import java.sql.Connection;

public interface RankPolicy {

    MemberRank getRank(Connection connection, MemberEntity member) throws Exception;
}
