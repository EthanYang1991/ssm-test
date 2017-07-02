package cn.etao.ssm;

import cn.etao.ssm.pojo.Animal;
import cn.etao.ssm.pojo.Dog;
import cn.etao.ssm.pojo.User;
import cn.etao.ssm.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.mangofactory.swagger.models.alternates.Entry;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import org.apache.log4j.Logger;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

import static org.mockito.Mockito.*;

/**
 * Created by yangli on 2016/8/10.
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
@WebAppConfiguration
public class TestMyBatis {
//    private static Logger logger = Logger.getLogger("test");
    @Resource
    private IUserService userService = null;

    private IUserService mockUserService;

    @Test
    public void test1() {
        Date date=new Date(0);
        User user = userService.getUserById(1);
         System.out.println(user.getUserName());
//        logger.info("值："+user.getUserName());
//        logger.info(JSON.toJSONString(user));
        System.out.println("--------------------------------" + user.toString());
    }

    @Test
    public void test2(){



        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



        try
        {


            Date d1 = df.parse("2004-03-26 13:31:40");
            Date d2 = df.parse("2004-01-02 11:30:24");
            long diff = d1.getTime() - d2.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            String bb="";
        }
        catch (Exception e)
        {

        }

        String str="";
    }

    @Test
    public void test3(){

//        String dateStr="";
//        String date1="10:00";
//
//        Date date = new Date();
//        DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        dateStr = sdf.format(date);

        String time = "15:30:18";
        DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date date=new Date();
        try{
            date= sdf.parse(time);
        }catch (Exception ex){

        }



        String aa="";
    }


    @Test
    public void test4(){
        Map<String,String> map=new HashMap<String, String>();
        map.put("aaa","123");
        map.put("bbb","456");
        map.put("ccc","789");

        //keySet遍历key+value（写法1）：
        Iterator<String> iterator=map.keySet().iterator();
        while (iterator.hasNext()){
            String key=iterator.next();
            String value=map.get(key);
        }


        //keySet遍历key+value（写法2）：
        for (String key:map.keySet()){
            String value=map.get(key);
        }

        //entrySet遍历key+value（写法1）：


        List<String> list= Lists.newArrayList();
        List<String> lt=new ArrayList<String>();

        String bb="456";
    }

    @Test
    public void test5(){
        //List<String> list= Splitter.on(",").trimResults().omitEmptyStrings().splitToList(" ,a ,b,c ");

        Multimap<String,String> myMutimap= ArrayListMultimap.create();
        myMutimap.put("Fruits","Bannana");
        myMutimap.put("Fruits","Apple");
        myMutimap.put("Fruits","Pear");
        myMutimap.put("Fruits","Pear");

        int size=myMutimap.size();

        Collection<String> fruits= myMutimap.get("Fruits");

        String abc="";
    }

    @Test  //集合
    public void test6(){

        //List
        //        ArrayList<String> m_ArrayList=new ArrayList<String>();
        //        m_ArrayList.add("xiaohong");
        //        m_ArrayList.add("xiaoming");
        //        m_ArrayList.add("xiaojun");
        //        m_ArrayList.set(1,"haha");
        //
        //        Iterator<String> it_ArrayList=m_ArrayList.iterator();
        //        while (it_ArrayList.hasNext()){
        //            System.out.println(it_ArrayList.next());
        //        }

        //Map
        Map<String,String> map=new HashMap<String, String>();
        map.put("first","linlin");
        map.put("second","bingbing");
        map.put("third","wenwen");
        map.put("first","linlin2");

        //第一种：通过Map.keySet遍历key和value
        /*
        for (String key:map.keySet()){
            System.out.println("key:"+key+",value:"+map.get(key));
        }*/

        //第二种：通过Map.entrySet使用iterator遍历key和value
        /*
        Iterator<Map.Entry<String,String>> it=map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> entry=it.next();
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
        }*/

        //第三种：通过Map.entrySet遍历key和value
        /*
        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
        }*/

        //第四种：通过Map.values()遍历所有的value，但是不能遍历键key
        /*
        for(String v:map.values()){
            System.out.println("values:"+v);
        }*/






        String str="";
    }

    @Test //guava
    public void test7(){
        /*
        String strWorld="wer|dfd|dd|dfd|dda|de|dr";
        String[] words=strWorld.split("\\|");
        List<String> worddList=new ArrayList<String>();
        for (String word:words){
            worddList.add(word);
        }
        Multiset<String> wordsMultiset = HashMultiset.create();
        wordsMultiset.addAll(worddList);

        for(String key:wordsMultiset.elementSet()){
            System.out.println(key+",count:"+wordsMultiset.count(key));
        }
        */

        //ConcurrentMap<String,Object> map1=new MapMaker().concurrencyLevel(8).makeMap();

                /*
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge()-o1.getAge();
            }
        });
        */

        List<Person> list=new ArrayList<Person>(){};
        Person p1=new Person();
        p1.setId(1);
        p1.setName("zhangsan");
        p1.setAge(20);
        list.add(p1);

        Person p2=new Person();
        p2.setId(2);
        p2.setName("lisi");
        p2.setAge(25);
        list.add(p2);

        Person p3=new Person();
        p3.setId(3);
        p3.setName("wangwu");
        p3.setAge(30);
        list.add(p3);

        List<Integer> list1=new ArrayList<Integer>();
        list1.add(62);
        list1.add(46);
        list1.add(32);
        list1.add(15);

        Collection<Integer>  filterCollection =
                Collections2.filter(list1, new Predicate<Integer>(){
                    @Override
                    public boolean apply(Integer input) {
                        return input >= 20;
                    }});

        list1.add(2);

        String str="123";
    }


    @Test
    public void test8(){
        /*
        Joiner joiner=Joiner.on(",").skipNulls();
        String str=joiner.join("aa","bb","cc");
        String str1=Joiner.on(",").join(Arrays.asList(1,5,6));
        Iterable<String> list= Splitter.on('.').trimResults().omitEmptyStrings().split("foo,bar,,   qux");
        String abc="";
        */

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello wold!");
            }
        }).start();

        String abc="";
    }

    //Mock
    @Test
    public void test9(){
        IUserService mockUserService = mock(IUserService.class);
        User user=new User();
        user.setUserName("sky");

        when(mockUserService.getUserById(1)).thenReturn(user);
        User user1 = userService.getUserById(1);



        String str="";
    }
}

