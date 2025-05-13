package generator.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 企业标签化服务订单结果表
 * @TableName entCredit_orderResultInfo
 */
@Data
public class EntcreditOrderresultinfo implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 订单编号
     */
    private String queryno;

    /**
     * Base64加密公钥
     */
    private String cerStr;

    /**
     * 模型文件base64-税务
     */
    private String modelTaxFile;

    /**
     * 模型文件base64-发票
     */
    private String modelInvoiceFile;

    /**
     * 模型文件base64-核企上下游
     */
    private String modelChainFile;

    /**
     * 返回报文
     */
    private String result;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}