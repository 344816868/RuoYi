package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BussinessContract;
import com.ruoyi.system.domain.SysUser;

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
    /**
     * 功能描述:导入
     * @author:
     * @param:  * @param null
     * @Date: 16:00 2021/2/3
     * @return:
     */
    public String importContract(List<BussinessContract> List);

    /**
     * 功能描述:查询即将过期的项目
     * @author:
     * @param:  * @param null
     * @Date: 16:00 2021/2/3
     * @return:
     */
    public List<BussinessContract> selectExpireBussinessContract(BussinessContract bussinessContract);
}
