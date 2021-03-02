package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BussinessReceivable;
import com.ruoyi.system.domain.Commission;

/**
 * 应收金额Service接口
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public interface IBussinessReceivableService 
{
    /**
     * 查询应收金额
     * 
     * @param id 应收金额ID
     * @return 应收金额
     */
    public BussinessReceivable selectBussinessReceivableById(Long id);

    /**
     * 查询应收金额列表
     * 
     * @param bussinessReceivable 应收金额
     * @return 应收金额集合
     */
    public List<BussinessReceivable> selectBussinessReceivableList(BussinessReceivable bussinessReceivable);

    /**
     * 新增应收金额
     * 
     * @param bussinessReceivable 应收金额
     * @return 结果
     */
    public int insertBussinessReceivable(BussinessReceivable bussinessReceivable);

    /**
     * 修改应收金额
     * 
     * @param bussinessReceivable 应收金额
     * @return 结果
     */
    public int updateBussinessReceivable(BussinessReceivable bussinessReceivable);

    /**
     * 批量删除应收金额
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBussinessReceivableByIds(String ids);

    /**
     * 删除应收金额信息
     * 
     * @param id 应收金额ID
     * @return 结果
     */
    public int deleteBussinessReceivableById(Long id);

    public String importContract(List<BussinessReceivable> List);
}
