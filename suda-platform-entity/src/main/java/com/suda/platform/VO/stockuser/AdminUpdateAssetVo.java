package com.suda.platform.VO.stockuser;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 用户充值扣款
 */
public class AdminUpdateAssetVo {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 币种
     */
    private String stockCode;
    /**
     * 操作(1-充值2-扣款)
     */
    private Byte operation;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 备注
     */
    private String remark;

    /**
     * agent_user_id
     * 代理商id
     */
    private Long agentUserId;


    @ApiModelProperty(value = "钱包账号 卡号")
    @TableField("card_num")
    private String cardNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Byte getOperation() {
        return operation;
    }

    public void setOperation(Byte operation) {
        this.operation = operation;
    }

    public Long getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(Long agentUserId) {
        this.agentUserId = agentUserId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
