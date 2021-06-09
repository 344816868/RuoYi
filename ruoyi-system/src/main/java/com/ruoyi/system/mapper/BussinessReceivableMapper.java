package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BussinessReceivable;
import org.apache.ibatis.annotations.Param;

/**
 * 应收金额Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public interface BussinessReceivableMapper 
{
    /**
     * 查询应收金额
     * 
     * @param id 应收金额ID
     * @return 应收金额
     */
    public BussinessReceivable selectBussinessReceivableById(Long id);

    public BussinessReceivable selectBussinessReceivableByCode(String contractCode);

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

    public int updateBussinessReceivableByCode(BussinessReceivable bussinessReceivable);

    /**
     * 删除应收金额
     * 
     * @param id 应收金额ID
     * @return 结果
     */
    public int deleteBussinessReceivableById(Long id);

    /**
     * 批量删除应收金额
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBussinessReceivableByIds(String[] ids);

    public double getReceivableSum (String contractCode);

    public BussinessReceivable selectBussinessReceivable(@Param("time") String time, @Param("contractCode") String contractCode);
}
