package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CommissionMapper;
import com.ruoyi.system.domain.Commission;
import com.ruoyi.system.service.ICommissionService;
import com.ruoyi.common.core.text.Convert;

/**
 * 手续费管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-04
 */
@Service
public class CommissionServiceImpl implements ICommissionService 
{
    @Autowired
    private CommissionMapper commissionMapper;

    /**
     * 查询手续费管理
     * 
     * @param commissionId 手续费管理ID
     * @return 手续费管理
     */
    @Override
    public Commission selectCommissionById(Long commissionId)
    {
        return commissionMapper.selectCommissionById(commissionId);
    }

    /**
     * 查询手续费管理列表
     * 
     * @param commission 手续费管理
     * @return 手续费管理
     */
    @Override
    public List<Commission> selectCommissionList(Commission commission)
    {
        return commissionMapper.selectCommissionList(commission);
    }

    @Override
    public List<Commission> selectCommissionInfoList(Commission commission) {
        return commissionMapper.selectCommissionInfoList(commission);
    }

    /**
     * 新增手续费管理
     * 
     * @param commission 手续费管理
     * @return 结果
     */
    @Override
    public int insertCommission(Commission commission)
    {
        return commissionMapper.insertCommission(commission);
    }

    /**
     * 修改手续费管理
     * 
     * @param commission 手续费管理
     * @return 结果
     */
    @Override
    public int updateCommission(Commission commission)
    {
        return commissionMapper.updateCommission(commission);
    }

    /**
     * 删除手续费管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCommissionByIds(String ids)
    {
        return commissionMapper.deleteCommissionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除手续费管理信息
     * 
     * @param commissionId 手续费管理ID
     * @return 结果
     */
    @Override
    public int deleteCommissionById(Long commissionId)
    {
        return commissionMapper.deleteCommissionById(commissionId);
    }

    /**
     * 查询手续费信息
     * @param contractCode 项目编号
     * @return
     */
    @Override
    public Commission selectCommissionByCode(String contractCode) {
        return commissionMapper.selectCommissionByCode(contractCode);
    }

    @Override
    public int deleteCommissionByCode(String contractCode) {
        return commissionMapper.deleteCommissionByCode(contractCode);
    }

}
