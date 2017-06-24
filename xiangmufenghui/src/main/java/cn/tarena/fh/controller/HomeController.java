package cn.tarena.fh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/6/3.
 */
@Controller
public class HomeController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/toCal")
    public String toCal(){
        return "redirect:/calendar.html";
    }
}
