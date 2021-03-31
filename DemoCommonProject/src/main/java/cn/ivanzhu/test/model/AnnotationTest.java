package cn.ivanzhu.test.model;

import cn.ivanzhu.test.enumeration.DoubleAnnotation;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ivanzhu
 * @time 2021/3/20 17:53
 * @Version 1.0
 */
@Data
public class AnnotationTest {
    @DoubleAnnotation("id1")
    @DoubleAnnotation("id2")
    private Integer id;

    public static void main(String[] args) {
//        Class<?> clazz = AnnotationTest.class;
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            DoubleAnnotation[] annotations = field.getAnnotationsByType(DoubleAnnotation.class);
//            for (DoubleAnnotation annotation : annotations) {
//                System.out.println(annotation.value());
//            }
//        }
//        String str = " a s ";
//        System.out.println(StringUtils.trim(str));
//        List<Integer> list = Lists.newArrayList(1, 2);
//        list.sort((o1, o2) -> o2 - o1);
//        System.out.println(list);
//        Map<Boolean, List<Integer>> collect = list.stream().collect(Collectors.partitioningBy(id -> id == 1));
//        System.out.println(collect.get(true));
//        System.out.println(collect.get(false));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
//        LocalDateTime parse = LocalDateTime.parse("2021-03-18 06:00:00 AM", formatter);
//        LocalDateTime parse = LocalDateTime.parse("2019-03-27T18:00:00");
//        System.out.println(parse);
//        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
//        Map<Integer, List<Integer>> map = Maps.newHashMap();
//        for (Integer i : list) {
//            List<Integer> absent = map.computeIfAbsent(Math.floorMod(i, 2), k -> Lists.newArrayList());
//            absent.add(i);
//        }
//        System.out.println(map);
        BigDecimal bigDecimal = BigDecimal.valueOf(10000000, 6);
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.stripTrailingZeros().toPlainString());
    }
}
