package com.winiis.cloud.translator.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * WiniisTranslatorDemoApplication.
 *
 * @author zj.
 *         Created on 2019/4/1 0001.
 */
@Configuration
@ComponentScan({"com.winiis"})
@EnableScheduling
@SpringBootApplication
public class WiniisTranslatorApplication extends SpringBootServletInitializer {
    public WiniisTranslatorApplication() {
        this.setRegisterErrorPageFilter(false);
    }

    public static void main(String[] args){
        SpringApplication.run(WiniisTranslatorApplication.class,args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
