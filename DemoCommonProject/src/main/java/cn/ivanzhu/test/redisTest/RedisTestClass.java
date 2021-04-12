package cn.ivanzhu.test.redisTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ivanzhu
 * @time 2021/4/7 11:20
 * @Version 1.0
 */
public class RedisTestClass {
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static final String KEY = "root_item_inventory_id";

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            RedisExec command = new RedisExec();
            command.setKey(KEY);
            executorService.execute(command);
        }
    }

    private Jedis jedis = new Jedis("192.168.1.127", 6379);

    {
        jedis.set(KEY, "100");
        jedis.close();
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RedisExec implements Runnable {
        private String key;
        private Jedis jedis = new Jedis("192.168.1.127", 6379);

        @Override
        public void run() {
            String watch = jedis.watch(key);
            System.out.println("watch返回值：" + watch);
            int num = NumberUtils.toInt(jedis.get(key));
            Transaction transaction = jedis.multi();
            transaction.set(key, String.valueOf(--num));
            List<Object> exec = transaction.exec();
            System.out.println(exec);
            if (exec.size() == 0) {
                System.out.println("扣减失败");
            } else {
                System.out.println("扣减成功");
            }
            jedis.close();
        }
    }
}
