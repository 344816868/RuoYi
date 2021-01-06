package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BussinessContract;

/**
 * 合同管理Service接口
 * 
 * @author ruoyi
 * @date 2020-10-22
 */
public interface IBussinessContractService 
{
    /**
     * 查询合同管理
     * 
     * @param contractId 合同管理ID
     * @return 合同管理
     */
    public BussinessContract selectBussinessContractById(Long contractId);

    /**
     * 查询合同管理列表
     * 
     * @param bussinessContract 合同管理
     * @return 合同管理集合
     */
    public List<BussinessContract> selectBussinessContractList(BussinessContract bussinessContract);

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

    /**
     * 批量删除合同管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBussinessContractByIds(String ids);

    /**
     * 删除合同管理信息
     * 
     * @param contractId 合同管理ID
     * @return 结果
     */
    public int deleteBussinessContractById(Long contractId);
}
