package cn.etao.ssm.common;

import org.springframework.stereotype.Component;

/**
 * Created by lil.yang on 2017/4/19.
 */
@Component
public class ListCommon<T> {
    private T t;

    public void add(T t){
        this.t=t;
    }

    public T get(){
        return t;
    }
}
