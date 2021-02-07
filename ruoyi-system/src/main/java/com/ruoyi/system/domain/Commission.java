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
    @Excel(name = "是否有发票(0是 1否)")
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commissionId", getCommissionId())
            .append("contractCode", getContractCode())
            .append("contractName", getContractName())
            .append("isbill", getIsbill())
            .append("commissionTime", getCommissionTime())
            .append("commissionRemark", getCommissionRemark())
            .append("billTime", getBillTime())
            .append("billRemark", getBillRemark())
            .toString();
    }
}
