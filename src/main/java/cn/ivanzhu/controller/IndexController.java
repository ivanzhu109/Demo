package cn.ivanzhu.controller;

import cn.ivanzhu.enumeration.TestEnum;
import cn.ivanzhu.model.TestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ivanzhu
 * @date 2020/5/10
 * @time 22:58
 */
@RestController
public class IndexController {

    @GetMapping("")
    public String index(){
        TestModel.staticClass staticClass = new TestModel.staticClass();
        TestModel.TestEnum hahah = TestModel.TestEnum.HAHAH;
        TestEnum hahah1 = TestEnum.HAHAH;
        return "欢迎来到 ivanzhu 的个人网站";
    }
}
