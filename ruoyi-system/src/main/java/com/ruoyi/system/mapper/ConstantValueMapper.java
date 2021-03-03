package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ConstantValue;

/**
 * 固化值管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public interface ConstantValueMapper 
{
    /**
     * 查询固化值管理
     * 
     * @param valueId 固化值管理ID
     * @return 固化值管理
     */
    public ConstantValue selectConstantValueById(Long valueId);


    public ConstantValue selectNewValueByCode(String contractCode);

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
     * 删除固化值管理
     * 
     * @param valueId 固化值管理ID
     * @return 结果
     */
    public int deleteConstantValueById(Long valueId);

    /**
     * 批量删除固化值管理
     * 
     * @param valueIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteConstantValueByIds(String[] valueIds);
}
