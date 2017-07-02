package cn.etao.ssm;

import cn.etao.ssm.common.ListCommon;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.annotation.Resource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.sun.javaws.JnlpxArgs.verify;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by yangli on 2016/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:spring/*.xml"})
@WebAppConfiguration
public class TestBasic {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Test //Lambda
    public void test1() {
        String b="";

        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);

        /*通过过滤创建一个String列表
        List<String> strList= Arrays.asList("aaa","cc","c","dddddd");
        List<String> list= strList.stream().filter(x->x.length()>2).collect(Collectors.toList());
        */

        //* Lambda表达式的map和reduce
        /*
        List<Integer> list= Arrays.asList(100,200,300,400,500);
        list.stream().map((cost)->cost+0.12*cost).forEach(System.out::println);
        double bill= list.stream().map((cost)->cost+0.12*cost).reduce((average,cost)->average+cost).get();
        */

        /* Lambda 表达式中加入Predicate
        List<String> languages=Arrays.asList("Java","C++","OC","NodeJs","Javascript");

        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong=(n)->n.length()==6;

        languages.stream()
                .filter(startsWithJ.or(fourLetterLong))
                .forEach((n)-> System.out.println("abc:"+n));
                */

        /* 集合遍历
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");

        /*
        for(String feature:features){
            System.out.println(feature);
        }*/
        /*
        features.forEach(n-> System.out.println(n));
        */

        /*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello wold!");
            }
        }).start();
        new Thread(()-> System.out.println("aa")).start();
        */

        /* Lambda表达式对列队进行迭代
        JButton show=new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("abc");
            }
        });
        show.addActionListener((e)->{
            System.out.println("ccccc");
        });*/



        String a="";
    }

    @Test //遍历map
    public void  test2(){
        Map<String,String> map=new HashMap<String,String>();
        map.put("1","value1");
        map.put("2","value2");
        map.put("3","value3");

        //第一种
       /* for (String key:map.keySet()){
            System.out.println("key:"+key+",value:"+map.get(key));
        }*/

       //第二种
        /*Iterator<Map.Entry<String,String>> it=map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> entry=it.next();
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
        }*/

        //第三种
        /*for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
        }*/

        //第四种
        /*for (String v:map.values()){
            System.out.println("value:"+v);
        }*/

    }

    @Test //泛型方法
    public void test3(){
       /* Integer[] intArray={1,2,3,4,5};
        String[] strArray={"aa","bb","cc"};
        Double[] dblArray={1.1,2.3,4.2,5.1};*/
        //printArray(strArray);

        //String num= maximum("a","f","b");

        //泛型类
        /*ListCommon<String> listCommon=new ListCommon<>();
        listCommon.add("abcc");
        String str=listCommon.get();*/

        //类型通配符
        /*List<String> strList= Arrays.asList("aaa","bbb","ccc");
        List<Integer> intList=Arrays.asList(23,546,12);
        getData(intList);*/

        String a="";
    }

    public static void filter(List<String> names, Predicate condition){
        for(String name:names){
            if(condition.test(name)){
                System.out.println(name+" ");
            }
        }
    }

    //泛型方法-比较大小
    public static <E> void printArray(E[] inputArray){
        for(E element:inputArray){
            System.out.printf("%s,",element);
        }
        System.out.println();
    }

    //泛型方法-比较大小
    public static <T extends Comparable<T>> T maximum(T x,T y,T z){
        T max=x;
        if (y.compareTo(max)>0){
            max=y;
        }
        if(z.compareTo(max)>0){
            max=z;
        }
        return max;
    }

    public static void getData(List<?> data){
        System.out.println("data:"+data.get(0));
    }

    @Test
    public void test5(){

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello wold!");
            }
        }).start();
        */

        //new Thread(()-> System.out.println("aa")).start();

        new Thread(()-> System.out.println("hello world!")).start();



        String str="";
    }

    //Mockito
    @Test
    public void test6(){


        //mock一个Iterator类
        Iterator iterator = mock(Iterator.class);
        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

        //logger.info("-----abc----"+df.format(n);

        //验证结果
        assertEquals("hello world world",result);


        String str="";
    }


    //线程池
    @Test
    public void test7(){

        //region **newCachedThreadPool 可缓存线程池**
//        ExecutorService cachedThreadPool= Executors.newCachedThreadPool();
//        for(int i=0;i<10;i++){
//            final int index=i;
//            try {
//                Thread.sleep(index*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            cachedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(index);
//                }
//            });
//        }
        //endregion

        //region **newFixedThreadPool 定长线程池**
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            fixedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(index);
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
        //endregion

        //**region newScheduledThreadPool 定长线程池 支持定时及周期性任务执行 **
//        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        scheduledThreadPool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("delay 3 seconds");
//            }
//        }, 3, TimeUnit.SECONDS);
        //endregion

        //region **newSingleThreadExecutor 单线程化的线程池**
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            singleThreadExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(index);
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
        //endregion

        String str="";
    }

    @Test
    public void test8(){
        Set<String> set = new HashSet<String>();

        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");

        set.add("e");//不能放入重复数据

        /**
         * 遍历方法一，迭代遍历
         */
        for(Iterator<String> iterator = set.iterator();iterator.hasNext();){
            System.out.print(iterator.next()+" ");
        }

        System.out.println();
        System.out.println("********************");

        String str="";
    }

    @Test
    public void test9(){
        Aminal.name="zs";
        Aminal.age=20;
        System.out.println(Aminal.msg);
    }

    @Test
    public void test10(){
//        Map<String, String> maps = Maps.newHashMapWithExpectedSize(2);
//        maps.put("111","aaa");
//        maps.put("222","bbb");
//        maps.put("333","ccc");
//
//        for (Map.Entry<String, String> entry : maps.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }
        String str1="aaa";
        String str2="bbb";

        int num= str1.compareTo(str2);

        String a="";
    }

    @Test
    public void test11(){
        String today = "2017-06-13";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));

    }

}
