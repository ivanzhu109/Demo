package cn.ivanzhu.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestEnum {
    /**
     *
     */
    HAHAH(1) {
        @Override
        public void test() {
            System.out.println("加载枚举1..................");
        }
    };
    {
        System.out.println("加载枚举.............");
    }
    private int id;

    public abstract void test();
}