package com.example.Portfolio.utils;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.ProjectDTO;

public class ProjectUtil {

    public static boolean checkProductDTO(ProjectDTO projectDTO) {
        return StringUtil.isNullOrEmpty(projectDTO.getTitle()) || StringUtil.isNullOrEmpty(projectDTO.getDescription()) ||
                StringUtil.isNullOrEmpty(projectDTO.getImage()) || projectDTO.getTechnologies() == null || projectDTO.getTechnologies().isEmpty() ||
                projectDTO.isPersonal() == null || StringUtil.isNullOrEmpty(projectDTO.getSite());
    }
}
