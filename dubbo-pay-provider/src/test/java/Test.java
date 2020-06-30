import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/30 11:26
 */
public class Test {

    static  int total=0;
    public static void main(String[] args) throws InterruptedException {
        Config config=new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient client= Redisson.create(config);

        RRateLimiter rateLimiter=client.getRateLimiter("rate_limiter2");
        rateLimiter.trySetRate(RateType.PER_CLIENT,5,1, RateIntervalUnit.SECONDS);
        for(int i=0;i<10;i++){
            final int k=i;
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    rateLimiter.acquire();
                    runs(k);
                }
            }).start();
        }
    }

    private static void runs(int i){
        System.out.println((i+1)+"号跑完了");
    }
}
