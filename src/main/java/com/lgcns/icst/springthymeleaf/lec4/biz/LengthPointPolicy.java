package com.lgcns.icst.springthymeleaf.lec4.biz;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("length-point")
@Service
public class LengthPointPolicy implements PointPolicy {

    private static LengthPointPolicy instance;

    public static LengthPointPolicy getInstance() {
        if (instance == null) {
            instance = new LengthPointPolicy();
        }
        return instance;
    }

    @Override
    public int getPoint(String content) {
        if (content.length() > 20) {
            return 5;
        } else if (content.length() > 10) {
            return 3;
        } else {
            return 0;
        }
    }
}