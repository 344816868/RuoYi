package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Commission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ConstantValueMapper;
import com.ruoyi.system.domain.ConstantValue;
import com.ruoyi.system.service.IConstantValueService;
import com.ruoyi.common.core.text.Convert;

/**
 * 固化值管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
@Service
public class ConstantValueServiceImpl implements IConstantValueService 
{
    @Autowired
    private ConstantValueMapper constantValueMapper;
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    /**
     * 查询固化值管理
     * 
     * @param valueId 固化值管理ID
     * @return 固化值管理
     */
    @Override
    public ConstantValue selectConstantValueById(Long valueId)
    {
        return constantValueMapper.selectConstantValueById(valueId);
    }

    /**
     * 查询固化值管理列表
     * 
     * @param constantValue 固化值管理
     * @return 固化值管理
     */
    @Override
    public List<ConstantValue> selectConstantValueList(ConstantValue constantValue)
    {
        return constantValueMapper.selectConstantValueList(constantValue);
    }

    /**
     * 新增固化值管理
     * 
     * @param constantValue 固化值管理
     * @return 结果
     */
    @Override
    public int insertConstantValue(ConstantValue constantValue)
    {
        return constantValueMapper.insertConstantValue(constantValue);
    }

    /**
     * 修改固化值管理
     * 
     * @param constantValue 固化值管理
     * @return 结果
     */
    @Override
    public int updateConstantValue(ConstantValue constantValue)
    {
        return constantValueMapper.updateConstantValue(constantValue);
    }

    /**
     * 删除固化值管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConstantValueByIds(String ids)
    {
        return constantValueMapper.deleteConstantValueByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除固化值管理信息
     * 
     * @param valueId 固化值管理ID
     * @return 结果
     */
    @Override
    public int deleteConstantValueById(Long valueId)
    {
        return constantValueMapper.deleteConstantValueById(valueId);
    }

    @Override
    public String importContract(List<ConstantValue> List) {
        if (StringUtils.isNull(List) || List.size() == 0)
        {
            throw new BusinessException("导入合同数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConstantValue constantValue : List)
        {
            try
            {
                this.insertConstantValue(constantValue);

                successNum++;
                //    successMsg.append("<br/>" + successNum + "、合同名称 " + bussinessContract.getContractName() + " 导入成功");
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、合同名称 " + constantValue.getContractName() + " 导入失败：";
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
