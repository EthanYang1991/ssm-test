package cn.etao.ssm.utils;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lil.yang on 2017/5/16.
 */
@Component(value = "JestService")
public class JestService {
    public JestClient jestClient;
    public JestService(){
        this.jestClient=getJestClient();
    }

    /**
     * 获取JestClient对象
     * @return
     */
    public JestClient getJestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://esapicn2.corp.qunar.com")
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create())
                .connTimeout(1000000)
                .readTimeout(1000000)
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }

    /**
     * 搜索文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param query
     * @return
     * @throws Exception
     */
    public SearchResult search(JestClient jestClient, String indexName, String typeName, String query) throws Exception {
        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        return jestClient.execute(search);
    }







    /**
     * 创建索引
     * @param jestClient
     * @param indexName
     * @return
     * @throws Exception
     */
    public boolean createIndex(JestClient jestClient, String indexName) throws Exception {

        JestResult jr = jestClient.execute(new CreateIndex.Builder(indexName).build());
        return jr.isSucceeded();
    }

    /**
     * Put映射
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param source
     * @return
     * @throws Exception
     */
    public boolean createIndexMapping(JestClient jestClient, String indexName, String typeName, String source) throws Exception {

        PutMapping putMapping = new PutMapping.Builder(indexName, typeName, source).build();
        JestResult jr = jestClient.execute(putMapping);
        return jr.isSucceeded();
    }

    /**
     * Get映射
     * @param jestClient
     * @param indexName
     * @param typeName
     * @return
     * @throws Exception
     */
    public String getIndexMapping(JestClient jestClient, String indexName, String typeName) throws Exception {

        GetMapping getMapping = new GetMapping.Builder().addIndex(indexName).addType(typeName).build();
        JestResult jr = jestClient.execute(getMapping);
        return jr.getJsonString();
    }

    /**
     * 索引文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param objs
     * @return
     * @throws Exception
     */
    public boolean index(JestClient jestClient, String indexName, String typeName, List<Object> objs) throws Exception {

        Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
        for (Object obj : objs) {
            Index index = new Index.Builder(obj).build();
            bulk.addAction(index);
        }
        BulkResult br = jestClient.execute(bulk.build());
        return br.isSucceeded();
    }



    /**
     * Count文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param query
     * @return
     * @throws Exception
     */
    public Double count(JestClient jestClient, String indexName, String typeName, String query) throws Exception {

        Count count = new Count.Builder()
                .addIndex(indexName)
                .addType(typeName)
                .query(query)
                .build();
        CountResult results = jestClient.execute(count);
        return results.getCount();
    }

    /**
     * Get文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param id
     * @return
     * @throws Exception
     */
    public JestResult get(JestClient jestClient, String indexName, String typeName, String id) throws Exception {

        Get get = new Get.Builder(indexName, id).type(typeName).build();
        return jestClient.execute(get);
    }

    /**
     * Delete索引
     * @param jestClient
     * @param indexName
     * @return
     * @throws Exception
     */
    public boolean delete(JestClient jestClient, String indexName) throws Exception {

        JestResult jr = jestClient.execute(new DeleteIndex.Builder(indexName).build());
        return jr.isSucceeded();
    }

    /**
     * Delete文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(JestClient jestClient, String indexName, String typeName, String id) throws Exception {

        DocumentResult dr = jestClient.execute(new Delete.Builder(id).index(indexName).type(typeName).build());
        return dr.isSucceeded();
    }

    /**
     * 关闭JestClient客户端
     * @param jestClient
     * @throws Exception
     */
    public void closeJestClient(JestClient jestClient) throws Exception {

        if (jestClient != null) {
            jestClient.shutdownClient();
        }
    }


    public QueryBuilder createQuery(){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("order_no", "sdc170517114855230");
        boolQueryBuilder = boolQueryBuilder.must(termsQueryBuilder);
        return boolQueryBuilder;
    }

}


