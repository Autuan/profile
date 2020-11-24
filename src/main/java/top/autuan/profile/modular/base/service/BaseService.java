package top.autuan.profile.modular.base.service;

import top.autuan.profile.modular.base.entity.PageInfo;
import top.autuan.profile.modular.base.entity.Project;

import java.util.List;

/**
 * @author 晚成
 */
public interface BaseService {
    PageInfo pageInfos();

    List<Project> listProjects();
}
