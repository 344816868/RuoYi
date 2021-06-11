package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BussinessReceivableMapper;
import com.ruoyi.system.domain.BussinessReceivable;
import com.ruoyi.system.service.IBussinessReceivableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 应收金额Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
@Service
public class BussinessReceivableServiceImpl implements IBussinessReceivableService 
{
    @Autowired
    private BussinessReceivableMapper bussinessReceivableMapper;
//    @Autowired
//    private ConstantValueMapper constantValueMapper; //

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    /**
     * 查询应收金额
     * 
     * @param id 应收金额ID
     * @return 应收金额
     */
    @Override
    public BussinessReceivable selectBussinessReceivableById(Long id)
    {
        return bussinessReceivableMapper.selectBussinessReceivableById(id);
    }

    /**
     * 查询应收金额列表
     * 
     * @param bussinessReceivable 应收金额
     * @return 应收金额
     */
    @Override
    public List<BussinessReceivable> selectBussinessReceivableList(BussinessReceivable bussinessReceivable)
    {
        List<BussinessReceivable> list=bussinessReceivableMapper.selectBussinessReceivableList(bussinessReceivable);
        for(BussinessReceivable receivable:list){
            String sum=bussinessReceivableMapper.getReceivableSum(receivable.getContractCode());
            receivable.setReceivableSum(sum);
        }
        return list;
    }

    /**
     * 新增应收金额
     * 
     * @param bussinessReceivable 应收金额
     * @return 结果
     */
    @Override
    public int insertBussinessReceivable(BussinessReceivable bussinessReceivable)
    {
        return bussinessReceivableMapper.insertBussinessReceivable(bussinessReceivable);
    }

    /**
     * 修改应收金额
     * 
     * @param bussinessReceivable 应收金额
     * @return 结果
     */
    @Override
    public int updateBussinessReceivable(BussinessReceivable bussinessReceivable)
    {
        return bussinessReceivableMapper.updateBussinessReceivable(bussinessReceivable);
    }

    /**
     * 删除应收金额对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBussinessReceivableByIds(String ids)
    {
        return bussinessReceivableMapper.deleteBussinessReceivableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除应收金额信息
     * 
     * @param id 应收金额ID
     * @return 结果
     */
    @Override
    public int deleteBussinessReceivableById(Long id)
    {
        return bussinessReceivableMapper.deleteBussinessReceivableById(id);
    }

    @Override
    public String importContract(List<BussinessReceivable> List) {
        if (StringUtils.isNull(List) || List.size() == 0)
        {
            throw new BusinessException("导入合同数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BussinessReceivable bussinessReceivable : List)
        {
            String time= DateUtils.parseDateToStr("YYYY",bussinessReceivable.getReceivableTime());
            String code=bussinessReceivable.getContractCode();
            BussinessReceivable bussinessReceivable1=bussinessReceivableMapper.selectBussinessReceivable(time,code);
            try{
                if(bussinessReceivable1!=null){
                    bussinessReceivable1.setReceivable(bussinessReceivable.getReceivable());
                    bussinessReceivable1.setContractName(bussinessReceivable.getContractName());
                    bussinessReceivable1.setReceivableTime(bussinessReceivable.getReceivableTime());
                    this.updateBussinessReceivable(bussinessReceivable1);
                }else{
                    this.insertBussinessReceivable(bussinessReceivable);
                }
                successNum++;
                //    successMsg.append("<br/>" + successNum + "、合同名称 " + bussinessContract.getContractName() + " 导入成功");
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }
}
