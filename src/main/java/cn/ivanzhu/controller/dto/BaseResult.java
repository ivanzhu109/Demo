package cn.ivanzhu.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ivanzhu
 * @date 2020/2/19
 * @time 11:36
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult {
    private Integer code;
    private String msg;
    private Object data;
}
