package top.autuan.profile.modular.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 晚成
 */
@Controller
public class BaseController {
    /**
     * 页面跳转
     * @param mav
     * @return
     */
    @RequestMapping("/")
    ModelAndView index(ModelAndView mav){
        mav.setViewName("index");
        return mav;
    }
}
