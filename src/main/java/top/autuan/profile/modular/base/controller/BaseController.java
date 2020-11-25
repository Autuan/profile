package top.autuan.profile.modular.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.autuan.profile.modular.base.entity.PageInfo;
import top.autuan.profile.modular.base.entity.Project;
import top.autuan.profile.modular.base.service.BaseService;

import java.util.List;

/**
 * @author 晚成
 */
@Controller
public class BaseController {
    @Autowired
    private BaseService baseService;
    /**
     * 页面跳转
     * @param mav
     * @return
     */
    @RequestMapping("/")
    ModelAndView index(ModelAndView mav){
        PageInfo pageInfo = baseService.pageInfos();
        mav.addObject("info",pageInfo);
        List<Project> projects = baseService.listProjects();
        mav.addObject("projects",projects);
        mav.setViewName("index");
        return mav;
    }
}
