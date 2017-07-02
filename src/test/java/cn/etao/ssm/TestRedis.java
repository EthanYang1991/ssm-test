package cn.etao.ssm;
import cn.etao.ssm.pojo.Animal;
import cn.etao.ssm.pojo.Dog;
import cn.etao.ssm.pojo.User;
import cn.etao.ssm.redis.RedisService;
import cn.etao.ssm.service.IUserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by yangli on 2016/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class TestRedis {

    @Resource
    RedisService redisService;

    @Resource
    private IUserService userService = null;

    @Test
    public void test3() {
//        User user = userService.getUserById(1);
//        System.out.println(user.getUserName());
//
//        System.out.println("--------------------------------" + user.toString());


    }
//
    @Test
    public void test1() {
//        redisService.set("redis_name","123");
        //RedisService redisService=new RedisService();
        redisService.set("test_name","abc");
        System.out.println("test1");
    }
//
    @Test
    public void test2(){
//        String str=redisService.get("java_ssm_redis_name",String.class);
        //edisService redisService=new RedisService();
       String tt=  redisService.get("test_name",String.class);
        System.out.println("test2"+tt);
    }
}


