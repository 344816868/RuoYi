package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 应收金额对象 bussiness_receivable
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
public class BussinessReceivable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String contractCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String contractName;

    /**每年的应收金额*/
    @Excel(name = "应收金额/年")
    private String receivableSum;

    /** 应收金额 （实时的）*/
    @Excel(name = "应收金额/月")
    private String receivable;
    /** 时间 */
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receivableTime;

    /** 备注 */
    @Excel(name = "备注")
    private String receivableRemark;


    public Date getReceivableTime() {
        return receivableTime;
    }

    public void setReceivableTime(Date receivableTime) {
        this.receivableTime = receivableTime;
    }

    public String getReceivableRemark() {
        return receivableRemark;
    }

    public void setReceivableRemark(String receivableRemark) {
        this.receivableRemark = receivableRemark;
    }

    private String updateContractCode;

    public String getReceivableSum() {
        return receivableSum;
    }

    public void setReceivableSum(String receivableSum) {
        this.receivableSum = receivableSum;
    }
	
	public String getUpdateContractCode() {
        return updateContractCode;
    }

    public void setUpdateContractCode(String updateContractCode) {
        this.updateContractCode = updateContractCode;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setContractCode(String contractCode) 
    {
        this.contractCode = contractCode;
    }

    public String getContractCode() 
    {
        return contractCode;
    }
    public void setReceivable(String receivable) 
    {
        this.receivable = receivable;
    }

    public String getReceivable() 
    {
        return receivable;
    }
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("contractCode", getContractCode())
            .append("receivable", getReceivable())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .append("contractName", getContractName())
            .toString();
    }
}
