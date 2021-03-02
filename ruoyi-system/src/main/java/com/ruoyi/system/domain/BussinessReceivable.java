package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 应收金额对象 bussiness_receivable
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public class BussinessReceivable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String contractCode;

    /** 应收金额 */
    @Excel(name = "应收金额")
    private String receivable;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("contractCode", getContractCode())
            .append("receivable", getReceivable())
            .toString();
    }
}
