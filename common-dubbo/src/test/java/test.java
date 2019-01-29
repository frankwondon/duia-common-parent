import com.duia.bean.common.dubbo.sentinel.SentinelContext;
import org.apache.commons.lang3.RandomUtils;

public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtils.nextInt(0,2));
        }

    }
}
