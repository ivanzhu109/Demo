package cn.ivanzhu.common;

import cn.ivanzhu.controller.dto.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ivanzhu
 * @date 2020/4/29
 * @time 14:00
 */
@Slf4j
@ControllerAdvice
public class BaseController {
    @Resource
    private HttpServletRequest httpServletRequest;
    @Resource
    private HttpServletResponse httpServletResponse;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult dealException(Exception e) throws IOException {
        log.error("服务器内部错误：");
        e.printStackTrace();
        return BaseResult.builder()
                .code(500)
                .msg("服务器内部错误")
                .build();
    }
}
