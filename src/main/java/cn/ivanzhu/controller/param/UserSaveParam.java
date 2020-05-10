package cn.ivanzhu.controller.param;

import cn.ivanzhu.model.UserPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ivanzhu
 * @date 2020/4/29
 * @time 13:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveParam {
    private String name;
    private Integer age;
    private String phone;
    private Integer sex;

    public UserPO getUserPO() {
        return UserPO.builder()
                .name(this.name)
                .age(this.age)
                .phone(this.phone)
                .sex(this.sex == null ? "中性" : this.sex == 1 ? "男性" : "女性")
                .build();
    }
}
