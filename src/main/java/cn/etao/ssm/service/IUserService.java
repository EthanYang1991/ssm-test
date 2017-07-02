package cn.etao.ssm.service;
import cn.etao.ssm.pojo.User;

/**
 * Created by yangli on 2016/8/10.
 */
public interface IUserService {
    public User getUserById(int userId);

    public int saveUser(User user);
}
