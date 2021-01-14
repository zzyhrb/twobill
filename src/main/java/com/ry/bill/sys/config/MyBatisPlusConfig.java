package com.ry.bill.sys.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;





/**
 * @author CKFuture
 * @since 2021-01-02 15:45
 * @descrption MyaBatisPlus 配置类
 */
@MapperScan(basePackages = {"com.ry.bill.sys.mapper"})  //扫描Mapper 文件夹
@EnableTransactionManagement    //事务管理
@Configuration  //配置类
public class MyBatisPlusConfig {
    /*
    注册乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
    /*
    分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
