package com.nancy.petshow.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author chen
 * @date 2020/5/11 13:57
 */
public class JsonUtil {
    private static ObjectMapper om = new ObjectMapper();

    /**
     * json转object 支持一层嵌套 T<K,L>
     *
     * @param json
     * @param t
     * @param k
     * @param l
     * @param <T>
     * @param <K>
     * @param <L>
     * @return
     * @throws JsonProcessingException
     */
    public static <T, K, L> T jsonToObject(String json, Class<T> t, Class<K> k, Class<L> l) throws IOException {
        JavaType javaType = om.getTypeFactory().constructParametricType(t, k, l);
        return om.readValue(json, javaType);
    }

    /**
     * json转object 支持一层嵌套 T<K>
     *
     * @param json
     * @param t
     * @param k
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> T jsonToObject(String json, Class<T> t, Class<K> k) throws IOException {
        JavaType javaType = om.getTypeFactory().constructParametricType(t, k);
        return om.readValue(json, javaType);
    }

    /**
     * json转object 不支持嵌套对象
     *
     * @param json
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> c) throws IOException {
        return om.readValue(json, c);
    }

    /**
     * object转json
     *
     * @param object
     * @return
     */
    public static String objectToJson(Object object) throws JsonProcessingException {
        return om.writeValueAsString(object);
    }
}
