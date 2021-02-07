package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同对象 bussiness_contract
 *
 * @author ruoyi
 * @date 2021-02-04
 */
public class BussinessContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同ID */
    private Long contractId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String contractCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String contractName;
    /** 项目类别 */
    @Excel(name = "项目类别")
    private String contractType;

    /** 系统商名称 */
    @Excel(name = "系统商名称")
    private String sysCompanyName;

    /** 收费单位 */
    @Excel(name = "收费单位")
    private String chargeUnit;

    /** 项目状态（0上线 1下线 2暂停 3其他） */
    @Excel(name = "项目状态", readConverterExp = "0=上线,1=下线,2=暂停,3=其他")
    private String contractStatus;

    /** 代理行前置码 */
    @Excel(name = "代理行前置码")
    private String agentCode;

    /** 代理行名称 */
    @Excel(name = "代理行名称")
    private String agentBankName;

    /** 存款落地行编号 */
    @Excel(name = "存款落地行编号")
    private String depositBankCode;

    /** 存款落地行名称 */
    @Excel(name = "存款落地行名称")
    private String depositBankName;

    /** 营销支行编号 */
    @Excel(name = "营销支行编号")
    private String marketBankCode;

    /** 营销支行名称 */
    @Excel(name = "营销支行名称")
    private String marketBankName;

    /** 是否开户（0是 1否） */
    @Excel(name = "是否开户", readConverterExp = "0=是,1=否")
    private String isopen;

    /** 对公客户号 */
    @Excel(name = "对公客户号")
    private String clientCode;

    /** 对公存款账户 */
    @Excel(name = "对公存款账户")
    private String checkingAccout;

    /** 手续费收取方式（0按笔数、1按金额、2不收取） */
 //   @Excel(name = "手续费收取方式", readConverterExp = "0=按笔数,1=按金额,2=不收取")
    private String commissionWay;

    /** 手续费收取比例 */
  //  @Excel(name = "手续费收取比例")
    private String commissionScale;

    /** 单笔手续费限额 */
  //  @Excel(name = "单笔手续费限额")
    private String commissionNorm;

    @Excel(name = "手续费收取方式(收取方式|收取比例|单笔手续费限额)")
    /** 收取方式 */
    private String commissionWay1;

    /** 缴费资金划款方式（0轧差、1收支分离、2全额清算） */
    @Excel(name = "缴费资金划款方式", readConverterExp = "0=轧差,1=收支分离,2=全额清算")
    private String fundWay;

    /** 子项目名称 */
    @Excel(name = "子项目名称")
    private String contractNameChild;

    /** 项目上线时间 */
    @Excel(name = "项目上线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractTime;

    /** 项目数 */
    @Excel(name = "项目数")
    private String contractCount;

    /** 对公缴费授权时间 */
    @Excel(name = "对公缴费授权时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date empowerTime;

    /** 项目签署日期 */
    @Excel(name = "项目签署日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    /** 项目终止日期 */
    @Excel(name = "项目终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 合同状态（0未过期 1过期） */
    @Excel(name = "合同状态", readConverterExp = "0=未过期,1=过期")
    private String status;

    /** 文件路径 */
//    @Excel(name = "文件路径")
    private String filePath;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getSysCompanyName() {
        return sysCompanyName;
    }

    public void setSysCompanyName(String sysCompanyName) {
        this.sysCompanyName = sysCompanyName;
    }

    public String getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(String chargeUnit) {
        this.chargeUnit = chargeUnit;
    }

    public String getAgentBankName() {
        return agentBankName;
    }

    public void setAgentBankName(String agentBankName) {
        this.agentBankName = agentBankName;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }


    public String getDepositBankCode() {
        return depositBankCode;
    }

    public void setDepositBankCode(String depositBankCode) {
        this.depositBankCode = depositBankCode;
    }

    public String getDepositBankName() {
        return depositBankName;
    }

    public void setDepositBankName(String depositBankName) {
        this.depositBankName = depositBankName;
    }

    public String getMarketBankCode() {
        return marketBankCode;
    }

    public void setMarketBankCode(String marketBankCode) {
        this.marketBankCode = marketBankCode;
    }

    public String getMarketBankName() {
        return marketBankName;
    }

    public void setMarketBankName(String marketBankName) {
        this.marketBankName = marketBankName;
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getCheckingAccout() {
        return checkingAccout;
    }

    public void setCheckingAccout(String checkingAccout) {
        this.checkingAccout = checkingAccout;
    }

    public String getCommissionWay() {
        return commissionWay;
    }

    public void setCommissionWay(String commissionWay) {
        this.commissionWay = commissionWay;
    }

    public String getCommissionScale() {
        return commissionScale;
    }

    public void setCommissionScale(String commissionScale) {
        this.commissionScale = commissionScale;
    }

    public String getCommissionNorm() {
        return commissionNorm;
    }

    public void setCommissionNorm(String commissionNorm) {
        this.commissionNorm = commissionNorm;
    }

    public String getCommissionWay1() {
        return commissionWay1;
    }

    public void setCommissionWay1(String commissionWay1) {
        this.commissionWay1 = commissionWay1;
    }

    public String getFundWay() {
        return fundWay;
    }

    public void setFundWay(String fundWay) {
        this.fundWay = fundWay;
    }

    public String getContractNameChild() {
        return contractNameChild;
    }

    public void setContractNameChild(String contractNameChild) {
        this.contractNameChild = contractNameChild;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public String getContractCount() {
        return contractCount;
    }

    public void setContractCount(String contractCount) {
        this.contractCount = contractCount;
    }

    public Date getEmpowerTime() {
        return empowerTime;
    }

    public void setEmpowerTime(Date empowerTime) {
        this.empowerTime = empowerTime;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                .append("sysCompanyName", getSysCompanyName())
                .append("chargeUnit", getChargeUnit())
                .append("agentBankName", getAgentBankName())
                .append("signTime", getSignTime())
                .append("endTime", getEndTime())
                .append("status", getStatus())
                .append("remark", getRemark())
                .append("filePath", getFilePath())
                .append("contractCode", getContractCode())
                .append("contractType", getContractType())
                .append("contractStatus", getContractStatus())
                .append("agentCode", getAgentCode())
                .append("depositBankCode", getDepositBankCode())
                .append("depositBankName", getDepositBankName())
                .append("marketBankCode", getMarketBankCode())
                .append("marketBankName", getMarketBankName())
                .append("isopen", getIsopen())
                .append("clientCode", getClientCode())
                .append("checkingAccout", getCheckingAccout())
                .append("commissionWay", getCommissionWay())
                .append("commissionScale", getCommissionScale())
                .append("commissionNorm", getCommissionNorm())
                .append("fundWay", getFundWay())
                .append("contractNameChild", getContractNameChild())
                .append("contractTime", getContractTime())
                .append("contractCount", getContractCount())
                .append("empowerTime", getEmpowerTime())
                .toString();
    }
}
