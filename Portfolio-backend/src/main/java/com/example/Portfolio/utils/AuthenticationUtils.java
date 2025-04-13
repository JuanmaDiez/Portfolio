package com.example.Portfolio.utils;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.AuthenticationDTO;

public class AuthenticationUtils {
    public static boolean checkAuthenticationDTO(AuthenticationDTO authenticationDTO) {
        return StringUtil.isNullOrEmpty(authenticationDTO.getUsernameOrEmail()) ||
        StringUtil.isNullOrEmpty(authenticationDTO.getPassword());
    }

    public static boolean checkAuthenticationHeader(String authHeader) {
        if (StringUtil.isNullOrEmpty(authHeader) || !authHeader.startsWith(ConstantUtils.BEARER))
            return true;

        try {
            String token = authHeader.split(" ")[1];
        } catch (IndexOutOfBoundsException e) {
            return true;
        }

        return false;
    }
}
