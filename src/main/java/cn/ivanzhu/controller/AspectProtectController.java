package cn.ivanzhu.controller;

import cn.ivanzhu.controller.aspect.AspectAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ivanzhu
 * @date 2020/4/28
 * @time 10:00
 */
@RestController
@RequestMapping("/api/aspect")
public class AspectProtectController {

    @GetMapping("")
    @AspectAnnotation(editName = true, editAge = true)
    public String aspectTest(@RequestParam("name") String name, @RequestParam("age") Integer age){
        return "名称：" + name + "，年龄：" + age;
    }
}
