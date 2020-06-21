package com.omen;

import com.omen.annotation.IDubboService;
import com.omen.utils.PackageUtil;
import lombok.SneakyThrows;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.lang.reflect.Field;
import java.util.Set;


/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/17 16:55
 */
@SpringBootApplication()
@ImportResource(locations={"classpath:pay-provider.xml"})
@DubboComponentScan(basePackages = "com.omen.service")
public class PayApplication{

    public static void main(String[] args)  {
        try {
            PackageUtil.scanIDubboService("com.omen.service");
            SpringApplication.run(PayApplication.class, args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
