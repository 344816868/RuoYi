package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Commission;
import com.ruoyi.system.domain.ConstantValue;

/**
 * 固化值管理Service接口
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public interface IConstantValueService 
{
    /**
     * 查询固化值管理
     * 
     * @param valueId 固化值管理ID
     * @return 固化值管理
     */
    public ConstantValue selectConstantValueById(Long valueId);

    /**
     * 查询固化值管理列表
     * 
     * @param constantValue 固化值管理
     * @return 固化值管理集合
     */
    public List<ConstantValue> selectConstantValueList(ConstantValue constantValue);

    /**
     * 新增固化值管理
     * 
     * @param constantValue 固化值管理
     * @return 结果
     */
    public int insertConstantValue(ConstantValue constantValue);

    /**
     * 修改固化值管理
     * 
     * @param constantValue 固化值管理
     * @return 结果
     */
    public int updateConstantValue(ConstantValue constantValue);

    /**
     * 批量删除固化值管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConstantValueByIds(String ids);

    /**
     * 删除固化值管理信息
     * 
     * @param valueId 固化值管理ID
     * @return 结果
     */
    public int deleteConstantValueById(Long valueId);

    public String importContract(List<ConstantValue> List);

    public int addDatas();


}
