package com.example.Portfolio.utils;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.AdminDTO;

public class AdminUtil {
    public static boolean checkAdminDTO(AdminDTO adminDTO) {
        return checkEmail(adminDTO.getEmail()) ||
                StringUtil.isNullOrEmpty(adminDTO.getUsername()) ||
                StringUtil.isNullOrEmpty(adminDTO.getPassword());
    }

    public static boolean checkEmail(String email) {
        return StringUtil.isNullOrEmpty(email) ||
                !email.contains("@") ||
                !email.contains(".com");
    }
}
