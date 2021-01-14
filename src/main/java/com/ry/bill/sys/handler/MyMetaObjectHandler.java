package com.ry.bill.sys.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author CKFuture
 * @since 2021-01-02 14:52
 * @descrption 自动填充处理
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("gmt_creat", new Date(), metaObject);
        this.setFieldValByName("gmt_modified", new Date(), metaObject);
    }
    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmt_modified", new Date(), metaObject);
    }
}
