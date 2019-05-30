package com.util.sms.lexinsms;

public class LeXinSendBack {
    private Integer replyCode;
    private String replyMsg;
    private Integer succeedNum;
    private Integer failedNum;
    private Integer chargCount;
    private Integer deduction;
    private Integer balance;

    public Integer getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(Integer replyCode) {
        this.replyCode = replyCode;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public Integer getSucceedNum() {
        return succeedNum;
    }

    public void setSucceedNum(Integer succeedNum) {
        this.succeedNum = succeedNum;
    }

    public Integer getFailedNum() {
        return failedNum;
    }

    public void setFailedNum(Integer failedNum) {
        this.failedNum = failedNum;
    }

    public Integer getChargCount() {
        return chargCount;
    }

    public void setChargCount(Integer chargCount) {
        this.chargCount = chargCount;
    }

    public Integer getDeduction() {
        return deduction;
    }

    public void setDeduction(Integer deduction) {
        this.deduction = deduction;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
