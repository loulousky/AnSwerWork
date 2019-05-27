package com.liuhai.answerwork.utils;

import com.google.gson.Gson;
import com.liuhai.bean.BasicAnserDate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 解析助理类
 */
public class GsonUtils {

    /**
     * 拿到题目组数据
     *
     * @param json
     * @param clazz
     * @return
     */
    public static BasicAnserDate getResult(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(BasicAnserDate.class, clazz);
        return gson.fromJson(json, objectType);

    }


    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}