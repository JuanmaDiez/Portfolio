package com.example.Portfolio.utils;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.AdminDTO;

public class AdminUtils {
    public static boolean checkEmail(String email) {
        return StringUtil.isNullOrEmpty(email) ||
                !email.contains("@");
    }
}
