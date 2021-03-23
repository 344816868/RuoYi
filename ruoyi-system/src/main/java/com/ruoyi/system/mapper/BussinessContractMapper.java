package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BussinessContract;

/**
 * 合同管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-22
 */
public interface BussinessContractMapper 
{
    /**
     * 查询合同管理
     * 
     * @param contractId 合同管理ID
     * @return 合同管理
     */
    public BussinessContract selectBussinessContractById(Long contractId);
    /**
     * 查询合同管理
     *
     * @param contractCode 项目编号
     * @return 合同管理
     */
    public BussinessContract selectBussinessContractByCode(String contractCode);

    /**
     * 查询合同管理列表
     * 
     * @param bussinessContract 合同管理
     * @return 合同管理集合
     */
    public List<BussinessContract> selectBussinessContractList(BussinessContract bussinessContract);
    /**
     * 导出项目信息列表
     *
     * @param bussinessContract 合同管理
     * @return 合同管理集合
     */
    public List<BussinessContract> selectExportBussinessContractList(BussinessContract bussinessContract);
    /**
     * 查询合同管理列表
     *
     * @param contractIds 合同管理
     * @return 合同管理集合
     */
    public List<BussinessContract> selectBussinessContractListByIds(String[] contractIds);

    /**
     * 新增合同管理
     * 
     * @param bussinessContract 合同管理
     * @return 结果
     */
    public int insertBussinessContract(BussinessContract bussinessContract);

    /**
     * 修改合同管理
     * 
     * @param bussinessContract 合同管理
     * @return 结果
     */
    public int updateBussinessContract(BussinessContract bussinessContract);

    public int updateBussinessContractByCode(BussinessContract bussinessContract);

    /**
     * 删除合同管理
     * 
     * @param contractId 合同管理ID
     * @return 结果
     */
    public int deleteBussinessContractById(Long contractId);

    /**
     * 批量删除合同管理
     * 
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBussinessContractByIds(String[] contractIds);
}
