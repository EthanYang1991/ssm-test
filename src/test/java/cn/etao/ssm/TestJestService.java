package cn.etao.ssm;

import cn.etao.ssm.utils.DateTimeUtils;
import cn.etao.ssm.utils.JestService;
import io.searchbox.client.JestClient;
import io.searchbox.core.SearchResult;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lil.yang on 2017/5/16.
 */
public class TestJestService {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private JestService jestService;
    //private JestClient jestClient;
    private String indexName = "hwd";
    private String typeName = "user";

    @Before
    public void setUp() throws Exception {
        jestService = new JestService();
        //jestClient = jestService.getJestClient();
    }

    @After
    public void tearDown() throws Exception {
        //jestService.closeJestClient(jestClient);
    }

    @Test
    public void test1() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        logger.info("=======abc=======");
        /* adfd
        //logs_logger_flight_f_tts_core-2017.20

//        String time = "2017-05-01 00:00:01";
//        String time2 = "2017-05-10 00:00:01";
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//        String startTime=DateTimeUtils.formatDate(dateTimeFormatter.parseDateTime(time));
//        String endTime=DateTimeUtils.formatDate(dateTimeFormatter.parseDateTime(time2));

        String time1="2017-05-01 00:00:01";
        String time2="2017-05-10 00:00:01";
        String startTime=dateToStamp(time1);
        String endTime=dateToStamp(time2);
        */

        //SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        TermQueryBuilder termsQueryBuilder = QueryBuilders.termQuery("order_no", "sdc170517114855230");
        boolQueryBuilder.must(termsQueryBuilder);

//        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("timestamp").gte(startTime).lt(endTime);
//        boolQueryBuilder.must(rangeQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);

        Calendar now = Calendar.getInstance();
        String indexName= String.format("logs_logger_flight_f_tts_core-%d.%d",now.get(Calendar.YEAR),getWeekofYear());  //""+getWeekofYear("");

        SearchResult result= jestService.search(jestService.getJestClient(),indexName,"tts",searchSourceBuilder.toString());





        String aa="";
    }


    @Test
    public void test2(){
        Calendar now = Calendar.getInstance();
        System.out.println("年：" + now.get(Calendar.YEAR));
        System.out.println("月：" + (now.get(Calendar.MONTH) + 1));
        System.out.println("日：" + now.get(Calendar.DAY_OF_MONTH));
        System.out.println("时：" + now.get(Calendar.HOUR_OF_DAY));
        System.out.println("分：" + now.get(Calendar.MINUTE));
        System.out.println("秒：" + now.get(Calendar.SECOND));

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + sdf.format(d));



        //int num=getWeekofYear("2017-05-18");
        //System.out.println(num);

//        String today = "2017-05-18";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = format.parse(today);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.setTime(date);
//
//        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
    }

    @Test
    public void test3(){
        int num= getWeekofYear();

        String str="";
    }

    /*
    * 将时间转换为时间戳
    */
    public static String dateToStamp(String str) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(str);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public static int getWeekofYear(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR)-1;
    }


}
