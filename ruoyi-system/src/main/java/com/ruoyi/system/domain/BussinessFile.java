package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同文件管理对象 bussiness_file
 * 
 * @author ruoyi
 * @date 2021-03-01
 */
public class BussinessFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同文件ID */
    private Long fileId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String contractCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String contractName;

    /** 合同时间 */
    @Excel(name = "合同时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 备注 */
    @Excel(name = "备注")
    private String fileRemark;

    private String updateContractCode ; //

    private String [] postIds;

    public String[] getPostIds() {
        return postIds;
    }

    public void setPostIds(String[] postIds) {
        this.postIds = postIds;
    }

    public String getUpdateContractCode() {
        return updateContractCode;
    }

    public void setUpdateContractCode(String updateContractCode) {
        this.updateContractCode = updateContractCode;
    }

    public void setFileId(Long fileId)
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
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
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileRemark(String fileRemark) 
    {
        this.fileRemark = fileRemark;
    }

    public String getFileRemark() 
    {
        return fileRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("contractCode", getContractCode())
            .append("contractName", getContractName())
            .append("uploadTime", getUploadTime())
            .append("createTime", getCreateTime())
            .append("filePath", getFilePath())
            .append("fileRemark", getFileRemark())
            .toString();
    }
}
