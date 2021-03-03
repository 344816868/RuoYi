package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 手续费管理对象 commission
 * 
 * @author ruoyi
 * @date 2021-02-04
 */
public class Commission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 手续费ID */
    private Long commissionId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String contractCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String contractName;

    /** 是否有发票(0是 1否) */
    @Excel(name = "是否有发票",readConverterExp = "0=是,1=否" )
    private String isbill;

    /** 手续费收取时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "手续费收取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date commissionTime;

    /** 手续费收取备注 */
    @Excel(name = "手续费收取备注")
    private String commissionRemark;

    /** 手续费发票时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "手续费发票时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date billTime;

    /** 手续费发票备注 */
    @Excel(name = "手续费发票备注")
    private String billRemark;

    private String filePath ; //发票存储路径

    /** 手续费应收金额 */
    @Excel(name = "总金额")
    private String receivable;

    /** 手续费实收金额 */
    @Excel(name = "实收金额")
    private String fundsReceived;

    /** 手续费待收金额 */
    @Excel(name = "待收金额")
    private String fundsSurplus;

    /** 手续费收取开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收取开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 手续费收取结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收取结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 收款银行账号 */
    @Excel(name = "收款银行账号")
    private String bankNum;

    private String text1;

    private String text2; //

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getReceivable() {
        return receivable;
    }

    public void setReceivable(String receivable) {
        this.receivable = receivable;
    }

    public String getFundsReceived() {
        return fundsReceived;
    }

    public void setFundsReceived(String fundsReceived) {
        this.fundsReceived = fundsReceived;
    }

    public String getFundsSurplus() {
        return fundsSurplus;
    }

    public void setFundsSurplus(String fundsSurplus) {
        this.fundsSurplus = fundsSurplus;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setCommissionId(Long commissionId)
    {
        this.commissionId = commissionId;
    }

    public Long getCommissionId() 
    {
        return commissionId;
    }
    public void setContractCode(String contractCode) 
    {
        this.contractCode = contractCode;
    }

    public String getContractCode() 
    {
        return contractCode;
    }
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }
    public void setIsbill(String isbill)
    {
        this.isbill = isbill;
    }

    public String getIsbill()
    {
        return isbill;
    }
    public void setCommissionTime(Date commissionTime) 
    {
        this.commissionTime = commissionTime;
    }

    public Date getCommissionTime() 
    {
        return commissionTime;
    }
    public void setCommissionRemark(String commissionRemark) 
    {
        this.commissionRemark = commissionRemark;
    }

    public String getCommissionRemark() 
    {
        return commissionRemark;
    }
    public void setBillTime(Date billTime) 
    {
        this.billTime = billTime;
    }

    public Date getBillTime() 
    {
        return billTime;
    }
    public void setBillRemark(String billRemark) 
    {
        this.billRemark = billRemark;
    }

    public String getBillRemark() 
    {
        return billRemark;
    }

    @Override
    public String toString() {
        return "Commission{" +
                "commissionId=" + commissionId +
                ", contractCode='" + contractCode + '\'' +
                ", contractName='" + contractName + '\'' +
                ", isbill='" + isbill + '\'' +
                ", commissionTime=" + commissionTime +
                ", commissionRemark='" + commissionRemark + '\'' +
                ", billTime=" + billTime +
                ", billRemark='" + billRemark + '\'' +
                ", filePath='" + filePath + '\'' +
                ", receivable='" + receivable + '\'' +
                ", fundsReceived='" + fundsReceived + '\'' +
                ", fundsSurplus='" + fundsSurplus + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", bankNum='" + bankNum + '\'' +
                ", text1='" + text1 + '\'' +
                ", text2='" + text2 + '\'' +
                '}';
    }
}
