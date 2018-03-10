package com.yanggaochao.springboot.learn.springbootjdbclearn;

import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author 杨高超
 * @since 2018-03-09
 */
public class UserApiTest {
    private String userAddUrl = "http://localhost:3030/security/api/v1/user/add";
    private String userLocateUrl = "http://localhost:3030/security/api/v1/user/locate/";
    private String userDeleteUrl = "http://localhost:3030/security/api/v1/user/delete/";
    private String userUpdateUrl = "http://localhost:3030/security/api/v1/user/update";
    private String userMatchUrl = "http://localhost:3030/security/api/v1/user/match/";
    JSONObject addUser = new JSONObject();
    Long addUserId = null;

    List<Long> userIds = new ArrayList<>();

    @Before
    public void before() throws Exception {
        addUser.put("name", "美羊羊");
        JSONObject addResultJson = new JSONObject(new HttpClientHelper().httpPostRequest(userAddUrl, addUser.toString(), null));
        assert ("success".equals(addResultJson.getString("result")));
        addUserId = addResultJson.getJSONObject("item").getLong("id");

        JSONObject user = new JSONObject();
        user.put("name", "喜羊羊");
        addResultJson = new JSONObject(new HttpClientHelper().httpPostRequest(userAddUrl, user.toString(), null));
        assert ("success".equals(addResultJson.getString("result")));
        userIds.add(addResultJson.getJSONObject("item").getLong("id"));
        user.put("name", "灰太狼");
        addResultJson = new JSONObject(new HttpClientHelper().httpPostRequest(userAddUrl, user.toString(), null));
        assert ("success".equals(addResultJson.getString("result")));
        userIds.add(addResultJson.getJSONObject("item").getLong("id"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        JSONObject user = new JSONObject();
        user.put("name", "霉羊羊");
        user.put("id", addUserId);
        new HttpClientHelper().httpPostRequest(userUpdateUrl, user.toString(), null);
        JSONObject locateResultJson = new JSONObject(new HttpClientHelper().httpGetRequest(userLocateUrl + addUserId, null));
        assert (user.getString("name").equals(locateResultJson.getJSONObject("item").getString("name")));
    }


    @Test
    public void testMatchUser() throws Exception {
        JSONObject matchResultJson = new JSONObject(new HttpClientHelper().httpGetRequest(userMatchUrl + URLEncoder.encode("羊","UTF-8"), null));
        assert (matchResultJson.has("items") && matchResultJson.getJSONArray("items").length() == 2);
        matchResultJson = new JSONObject(new HttpClientHelper().httpGetRequest(userMatchUrl + URLEncoder.encode("狼","UTF-8"), null));
        assert (matchResultJson.has("items") && matchResultJson.getJSONArray("items").length() == 1);
    }

    @After
    public void after() throws Exception {
        if (addUserId != null) {
            JSONObject deleteResultJson = new JSONObject(new HttpClientHelper().httpGetRequest(userDeleteUrl + addUserId, null));
            assert ("success".equals(deleteResultJson.getString("result")));
        }

        for (Long userId : userIds) {
            JSONObject deleteResultJson = new JSONObject(new HttpClientHelper().httpGetRequest(userDeleteUrl + userId, null));
            assert ("success".equals(deleteResultJson.getString("result")));
        }
    }
}
