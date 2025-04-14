package com.example.Portfolio.utils;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.ProjectDTO;

public class ProjectUtils {

    public static boolean checkProductDTO(ProjectDTO projectDTO) {
        return StringUtil.isNullOrEmpty(projectDTO.getTitleEsp()) || StringUtil.isNullOrEmpty(projectDTO.getTitleEn()) ||
                StringUtil.isNullOrEmpty(projectDTO.getDescriptionEsp()) || StringUtil.isNullOrEmpty(projectDTO.getDescriptionEn()) ||
                StringUtil.isNullOrEmpty(projectDTO.getImage()) || projectDTO.getTechnologiesIds() == null || projectDTO.getTechnologiesIds().isEmpty() ||
                projectDTO.isPersonal() == null || StringUtil.isNullOrEmpty(projectDTO.getSite());
    }
}
