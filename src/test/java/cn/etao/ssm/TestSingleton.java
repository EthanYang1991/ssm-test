package cn.etao.ssm;

import org.junit.Test;

/**
 * Created by lil.yang on 2017/5/21.
 */
public class TestSingleton {
    //单例模式七种写法
    @Test
    public void Test(){

    }
}

// 懒汉，线程不安全
// 这种写法lazy loading很明显，但是致命的是在多线程不能正常工作。
/*
class Singleton {
    private static Singleton instance;
    private Singleton(){}
    public static Singleton getInstance(){
        if(instance==null){
            instance=new Singleton();
        }
        return instance;
    }
}*/

//懒汉，线程安全
//这种写法能够在多线程中很好的工作，而且看起来它也具备很好的lazy loading，但是，遗憾的是，效率很低，99%情况下不需要同步。
/*
class Singleton{
    private static Singleton instance;
    private Singleton(){}
    public static synchronized Singleton getInstance(){
        if (instance==null){
            instance=new Singleton();
        }
        return instance;
    }
}*/

//饿汉
//这种方式基于classloder机制避免了多线程的同步问题，不过，instance在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用getInstance方法， 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化instance显然没有达到lazy loading的效果。
/*class Singleton{
    private static Singleton instance =new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }
}*/

//第四种（饿汉，变种）：
/*
class Singleton {
    private Singleton instance = null;
    static {instance = new Singleton();}
    private Singleton (){}
    public static Singleton getInstance() {
         return instance;
    }
}*/


//第五种 静态内部类
//这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，它跟第三种和第四种方式不同的是（很细微的差别）：
// 第三种和第四种方式是只要Singleton类被装载了，那么instance就会被实例化（没有达到lazy loading效果），
// 而这种方式是Singleton类被装载了，instance不一定被初始化。因为SingletonHolder类没有被主动使用，
// 只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance。想象一下，
// 如果实例化instance很消耗资源，我想让他延迟加载，另外一方面，我不希望在Singleton类加载时就实例化，
// 因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，
// 那么这个时候实例化instance显然是不合适的。这个时候，这种方式相比第三和第四种方式就显得很合理。
/*class Singleton{
    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton(){}
    public static final Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}*/

//双重校验锁
/*
class Singleton{
    private volatile static Singleton singleton;
    private Singleton(){}
    public static Singleton getSingleton(){
        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton=new Singleton();
                }
            }
        }
        return singleton;
    }
}*/


