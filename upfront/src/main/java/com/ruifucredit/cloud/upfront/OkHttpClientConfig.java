package com.ruifucredit.cloud.upfront;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Configuration
@AutoConfigureBefore(RestTemplateConfig.class)
public class OkHttpClientConfig {

    private final Logger logger = LoggerFactory.getLogger(OkHttpClient.class);

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                // 使用java8的lambda表达式 传入一个接口的实现类
                .addNetworkInterceptor(new HttpLoggingInterceptor(logger::info)
                        .setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }

}
