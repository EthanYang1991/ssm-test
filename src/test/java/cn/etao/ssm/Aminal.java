package cn.etao.ssm;

/**
 * Created by lil.yang on 2017/6/14.
 */
public class Aminal {
    static String name;
    static int age;
    static String msg;

    static {
        msg=name+ String.valueOf(age) ;
    }

    public static String say(String name, int age){
        return String.format("%s say hello,%d",age);
    }
}
