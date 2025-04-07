package com.shl.demo.sqzxbg;

import com.google.gson.GsonBuilder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class LocalResultDto {
    /**
     * 0为有效  1为无效
     */
    public final static String disIfValid = "0";
    public final static String isifValid = "1";


    /**
     * 查询时间
     */
    private String cxsj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    private String nsrsbh;
    /**
     * 查询是否有效
     */
    private String ifValid;
    /**
     * 查询无效原因
     */
    private String invalidReason;
    /**
     * GMV
     */
    private String gmv;
}
