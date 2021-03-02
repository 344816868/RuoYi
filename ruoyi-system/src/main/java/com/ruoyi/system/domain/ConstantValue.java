package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 固化值管理对象 constant_value
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public class ConstantValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long valueId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String contractCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String contractName;

    /** 固化值 */
    @Excel(name = "固化值")
    private String constantValue;

    /** 时间 */
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date valueTime;

    public void setValueId(Long valueId) 
    {
        this.valueId = valueId;
    }

    public Long getValueId() 
    {
        return valueId;
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
    public void setConstantValue(String constantValue) 
    {
        this.constantValue = constantValue;
    }

    public String getConstantValue() 
    {
        return constantValue;
    }
    public void setValueTime(Date valueTime) 
    {
        this.valueTime = valueTime;
    }

    public Date getValueTime() 
    {
        return valueTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("valueId", getValueId())
            .append("contractCode", getContractCode())
            .append("contractName", getContractName())
            .append("constantValue", getConstantValue())
            .append("valueTime", getValueTime())
            .append("remark", getRemark())
            .toString();
    }
}
