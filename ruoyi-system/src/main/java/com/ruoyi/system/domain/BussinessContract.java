package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同管理对象 bussiness_contract
 * 
 * @author ruoyi
 * @date 2020-10-22
 */
public class BussinessContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同ID */
    private Long contractId;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String contractName;

    /** 甲方公司名称 */
    @Excel(name = "甲方公司名称")
    private String aCompanyName;

    /** 甲方授权代表 */
    @Excel(name = "甲方授权代表")
    private String aCompanyDelegate;

    /** 乙方授权代表 */
    @Excel(name = "乙方授权代表")
    private String bCompanyDelegate;

    /** 签署日期 */
    @Excel(name = "签署日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    /** 合同终止日期 */
    @Excel(name = "合同终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 合同状态（0未过期 1过期） */
    @Excel(name = "合同状态", readConverterExp = "0=未过期,1=过期")
    private String status;

    private String filePath;

    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }
    public void setaCompanyName(String aCompanyName) 
    {
        this.aCompanyName = aCompanyName;
    }

    public String getaCompanyName() 
    {
        return aCompanyName;
    }
    public void setaCompanyDelegate(String aCompanyDelegate) 
    {
        this.aCompanyDelegate = aCompanyDelegate;
    }

    public String getaCompanyDelegate() 
    {
        return aCompanyDelegate;
    }
    public void setbCompanyDelegate(String bCompanyDelegate) 
    {
        this.bCompanyDelegate = bCompanyDelegate;
    }

    public String getbCompanyDelegate() 
    {
        return bCompanyDelegate;
    }
    public void setSignTime(Date signTime) 
    {
        this.signTime = signTime;
    }

    public Date getSignTime() 
    {
        return signTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractId", getContractId())
            .append("contractName", getContractName())
            .append("aCompanyName", getaCompanyName())
            .append("aCompanyDelegate", getaCompanyDelegate())
            .append("bCompanyDelegate", getbCompanyDelegate())
            .append("signTime", getSignTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("remark", getRemark())
            .toString();
    }
}
