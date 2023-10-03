//package com.log.logs.config;
//
////import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
////import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.context.annotation.Primary;
////
////@Configuration
////@ConditionalOnClass(org.apache.logging.log4j.Logger.class)
////public class Log4j2Config {
////
////    @Bean
////    @Primary
////    @ConditionalOnMissingBean
////    public Log4j2LoggingSystem log4j2LoggingSystem() {
////        return new Log4j2LoggingSystem(this.getClass().getClassLoader());
////    }
////}
//
