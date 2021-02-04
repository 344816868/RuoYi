package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Commission;

/**
 * 手续费管理Service接口
 * 
 * @author ruoyi
 * @date 2021-02-04
 */
public interface ICommissionService 
{
    /**
     * 查询手续费管理
     * 
     * @param commissionId 手续费管理ID
     * @return 手续费管理
     */
    public Commission selectCommissionById(Long commissionId);

    /**
     * 查询手续费管理列表
     * 
     * @param commission 手续费管理
     * @return 手续费管理集合
     */
    public List<Commission> selectCommissionList(Commission commission);

    /**
     * 新增手续费管理
     * 
     * @param commission 手续费管理
     * @return 结果
     */
    public int insertCommission(Commission commission);

    /**
     * 修改手续费管理
     * 
     * @param commission 手续费管理
     * @return 结果
     */
    public int updateCommission(Commission commission);

    /**
     * 批量删除手续费管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommissionByIds(String ids);

    /**
     * 删除手续费管理信息
     * 
     * @param commissionId 手续费管理ID
     * @return 结果
     */
    public int deleteCommissionById(Long commissionId);
}
