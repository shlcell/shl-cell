package com.shl.demo.sqzxbg;

public class REQUEST_Xxlshz {

    /**
     * 销方纳税人识别号
     */
    private String nsrsbh;
    /**
     * 第三方采集编码
     */
    private String dsfcjbm;

    public String getByzd1() {
        return byzd1;
    }

    public void setByzd1(String byzd1) {
        this.byzd1 = byzd1;
    }

    /**
     * 挡板规则区分字段
     */
    private String byzd1;

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getDsfcjbm() {
        return dsfcjbm;
    }

    public void setDsfcjbm(String dsfcjbm) {
        this.dsfcjbm = dsfcjbm;
    }

    @Override
    public String toString() {
        return "REQUEST_Xxlshz{" +
                "nsrsbh='" + nsrsbh + '\'' +
                ", dsfcjbm='" + dsfcjbm + '\'' +
                '}';
    }
}
