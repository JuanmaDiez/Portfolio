package com.example.Portfolio.utils;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.TechnologyDTO;

public class TechnologyUtils {
    public static boolean checkCreateTechnologyDTO(TechnologyDTO technologyDTO) {
        return StringUtil.isNullOrEmpty(technologyDTO.getName()) ||
                StringUtil.isNullOrEmpty(technologyDTO.getIcon());
    }

    public static boolean checkEditTechnologyDTO(TechnologyDTO technologyDTO) {
        return StringUtil.isNullOrEmpty(technologyDTO.getName()) &&
                StringUtil.isNullOrEmpty(technologyDTO.getIcon());
    }
}
