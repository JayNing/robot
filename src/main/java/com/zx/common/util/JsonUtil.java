package com.zx.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ning
 * //TODO json工具类
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {
    }

    private static Gson gson = new Gson();

    private static ObjectMapper mapper = new ObjectMapper();

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static {
        mapper.setDateFormat(dateFormat);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static Gson getInstance() {
        return gson;
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("JsonUtil fromJson Type:" + clazz.getName() + ", Json:" + json, e);
            return null;
        }
    }

    public static <T> List<T> fromListJson(String json, Class<T> clazz) {
        try {
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(json).getAsJsonArray();

            List<T> lst = new ArrayList<T>();
            for (final JsonElement js : array) {
                T entity = gson.fromJson(js, clazz);
                lst.add(entity);
            }

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("JsonUtil fromJson Type:List<" + clazz.getName() + ">, Json:" + json, e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T toObject(String content, T t) {
        T result = null;

        try {
            result = (T) mapper.readValue(content, t.getClass());
        } catch (JsonParseException e) {
            logger.info("JsonUtil   toObject  JsonParseException---------------> " + e);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            logger.info("JsonUtil   toObject  JsonMappingException---------------> " + e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("JsonUtil   toObject  IOException---------------> " + e);
            e.printStackTrace();
        }

        return result;
    }

    public static String toJsonFromObj(Object obj) {
        JSONObject json = JSONObject.fromObject(obj);
        String strJson = json.toString();
        return strJson;
    }

    public static <T> String toJsonFromArry(List<T> list) {
        String pojoJson = JSONArray.fromObject(list).toString();
        return pojoJson;
    }

    public static void main(String[] args) {
    }

    public static <T> T fromJsonForGson(String body, Type type) {
        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(body, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return t;
    }
}
