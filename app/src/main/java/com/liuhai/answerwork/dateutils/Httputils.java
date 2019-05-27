package com.liuhai.answerwork.dateutils;

import android.app.Application;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.CookieStore;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by admin on 2017/4/6.
 * 网络请求简单封装
 * @author liuhai
 */

public class Httputils {


    public static volatile Httputils instance = null;

    public static Httputils getInstance(Application application) {
        synchronized (Httputils.class) {
            if (instance == null) {
                instance = new Httputils(application);


            }
        }
        return instance;
    }

    /**
     * 初始化
     */
    private Httputils(Application application) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
//log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
//非必要情况，不建议使用，第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(application)));

//方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        OkGo.getInstance().init(application)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                           //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0

        OkGo.getInstance().init(application);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数


    }

    /**
     *convertSuccess():解析网络返回的数据回调
     parseError():解析网络失败的数据回调
     onBefore():网络请求真正执行前回调
     onSuccess():网络请求成功的回调
     onCacheSuccess():缓存读取成功的回调
     onError():网络请求失败的回调
     onCacheError():网络缓存读取失败的回调
     onAfter():网络请求结束的回调,无论成功失败一定会执行
     upProgress():上传进度的回调
     downloadProgress():下载进度的回调
     * get请求
     * @param url
     * @param callback
     */
    public static void HttpGet(String url, StringCallback callback){

        OkGo.<String>get(url)     // 请求方式和请求url
                .tag(url)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")
                                   // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                                      // 缓存模式，详细请看缓存介绍
                .execute(callback);

    }


    public void HttpDelet(String url){

        OkGo.delete(url);

    }








    /**
     *
     * FileCallback():空参构造
     FileCallback(String destFileName):可以额外指定文件下载完成后的文件名
     FileCallback(String destFileDir, String destFileName):可以额外指定文件的下载目录和下载完成后的文件名
     文件目录如果不指定,默认下载的目录为 sdcard/download/
     * 文件下载
     * @param url
     * @param fileCallback
     */
    public  void HttpDownload(String url,FileCallback fileCallback){
        OkGo.<File>get(url)//
                .tag(url)//
                .execute(fileCallback);
    }


    /**
     *
     *
     *  Post 请求
     *
     */

    public  void HttpPost(String url, HashMap map,StringCallback callback){
        OkGo.post(url).params(map).cacheMode(CacheMode.DEFAULT).execute(callback);

    }


}
