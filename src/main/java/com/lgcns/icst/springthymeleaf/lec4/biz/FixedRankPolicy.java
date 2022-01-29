package com.lgcns.icst.springthymeleaf.lec4.biz;

import com.lgcns.icst.springthymeleaf.lec4.constant.MemberRank;
import com.lgcns.icst.springthymeleaf.lec4.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
@Qualifier("fixed-rank")
public class FixedRankPolicy implements RankPolicy {

    @Override
    public MemberRank getRank(Connection connection, MemberEntity member) throws Exception {
        Integer point = member.getPoint();
        if (point > 20) {
            return MemberRank.GOLD;
        } else if (point > 10) {
            return MemberRank.SILVER;
        } else {
            return MemberRank.BRONZE;
        }
    }
}
