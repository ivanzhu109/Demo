package cn.ivanzhu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author ivanzhu
 * @date 2020/5/19
 * @time 10:08
 */
public class TestModel {

    @Data
    @AllArgsConstructor
    public static class staticClass{
        {
            System.out.println("静态内部类构造方法");
        }
    }

    @Getter
    @AllArgsConstructor
    public enum TestEnum {
        /**
         *
         */
        HAHAH(1){
            @Override
            public void test() {
                System.out.println("加载枚举..................");
            }
        };
        {
            System.out.println("加载内部枚举.............");
        }
        private int id;
        public abstract void test();
    }

}
