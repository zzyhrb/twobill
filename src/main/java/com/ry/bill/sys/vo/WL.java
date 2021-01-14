package com.ry.bill.sys.vo;

import lombok.Data;

/**
 * @author CKFuture
 * @since 2020-12-30 09:43
 * @description 电子围栏实体类，用于Json反序列化
 */
@Data
public class WL {

    /*
    纬度
     */
    private String latitude;

    /*
    经度
     */
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
