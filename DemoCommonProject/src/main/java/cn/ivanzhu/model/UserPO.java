package cn.ivanzhu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ivanzhu
 * @date 2020/4/29
 * @time 14:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPO {
    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private String sex;
}
