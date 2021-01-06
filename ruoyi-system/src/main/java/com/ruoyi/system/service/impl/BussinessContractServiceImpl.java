package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BussinessContractMapper;
import com.ruoyi.system.domain.BussinessContract;
import com.ruoyi.system.service.IBussinessContractService;
import com.ruoyi.common.core.text.Convert;

/**
 * 合同管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-22
 */
@Service
public class BussinessContractServiceImpl implements IBussinessContractService 
{
    @Autowired
    private BussinessContractMapper bussinessContractMapper;

    /**
     * 查询合同管理
     * 
     * @param contractId 合同管理ID
     * @return 合同管理
     */
    @Override
    public BussinessContract selectBussinessContractById(Long contractId)
    {
        return bussinessContractMapper.selectBussinessContractById(contractId);
    }

    /**
     * 查询合同管理列表
     * 
     * @param bussinessContract 合同管理
     * @return 合同管理
     */
    @Override
    public List<BussinessContract> selectBussinessContractList(BussinessContract bussinessContract)
    {
        return bussinessContractMapper.selectBussinessContractList(bussinessContract);
    }

    /**
     * 新增合同管理
     * 
     * @param bussinessContract 合同管理
     * @return 结果
     */
    @Override
    public int insertBussinessContract(BussinessContract bussinessContract)
    {
        return bussinessContractMapper.insertBussinessContract(bussinessContract);
    }

    /**
     * 修改合同管理
     * 
     * @param bussinessContract 合同管理
     * @return 结果
     */
    @Override
    public int updateBussinessContract(BussinessContract bussinessContract)
    {
        return bussinessContractMapper.updateBussinessContract(bussinessContract);
    }

    /**
     * 删除合同管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBussinessContractByIds(String ids)
    {
        return bussinessContractMapper.deleteBussinessContractByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同管理信息
     * 
     * @param contractId 合同管理ID
     * @return 结果
     */
    @Override
    public int deleteBussinessContractById(Long contractId)
    {
        return bussinessContractMapper.deleteBussinessContractById(contractId);
    }
}
