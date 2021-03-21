package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BussinessContract;
import com.ruoyi.system.domain.ConstantValue;
import com.ruoyi.system.mapper.BussinessContractMapper;
import com.ruoyi.system.mapper.ConstantValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private ConstantValueMapper constantValueMapper;
    @Autowired
    private BussinessContractMapper bussinessContractMapper; //
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

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

        List<Commission> commissionList=commissionMapper.selectCommissionList(commission);
        for(Commission commission1:commissionList){
            ConstantValue constantValue=constantValueMapper.selectNewValueByCode(commission1.getContractCode());
            BussinessContract bussinessContract=bussinessContractMapper.selectBussinessContractByCode(commission1.getContractCode());
            Double totalValue=0.0;
            Double fundsSurplusVal;
            if(StringUtils.isNotNull(constantValue)){
                if(StringUtils.isNotEmpty(constantValue.getConstantValue()) && StringUtils.isNotEmpty(commission1.getReceivable())){
                    totalValue=Double.valueOf(constantValue.getConstantValue()) + Double.valueOf(commission1.getReceivable());
                    commission1.setReceivable(""+totalValue);
                    fundsSurplusVal=totalValue-Double.valueOf(commission1.getFundsReceived());
                    commission1.setFundsSurplus(""+fundsSurplusVal);

                }else if(StringUtils.isNotEmpty(constantValue.getConstantValue()) && StringUtils.isEmpty(commission1.getReceivable())){
                    totalValue=Double.valueOf(constantValue.getConstantValue());
                    commission1.setReceivable(""+totalValue);
                    fundsSurplusVal=Double.valueOf(constantValue.getConstantValue()) - Double.valueOf(commission1.getFundsReceived());
                    commission1.setFundsSurplus(""+fundsSurplusVal);
                }
            }else{
                if(StringUtils.isNotNull(commission1.getReceivable())){
                    totalValue=Double.valueOf(commission1.getReceivable());
                    fundsSurplusVal=Double.valueOf(commission1.getReceivable()) - Double.valueOf(commission1.getFundsReceived());
                    commission1.setFundsSurplus(""+fundsSurplusVal);
                }

            }
            if(!"2".equals(bussinessContract.getFundWay())){
                commission1.setFundsSurplus("0");
                commission1.setFundsReceived(""+totalValue);
            }


        }
        return commissionList;
    }

    @Override
    public Map<String, Object> selectSum() {
        Map<String, Object> map=commissionMapper.selectSum();
        String str=map.get("RECEIVABLE").toString();
        Integer receivable=Integer.valueOf(str);
        Map<String, Object> map1=constantValueMapper.selectConstantSum();
        String str2=map1.get("CONSTRAINTSUM").toString();
        Integer contantSum=Integer.valueOf(str2);
        Integer total=receivable+contantSum;
        map.put("RECEIABLESUM",""+total);
        return map;
    }

    @Override
    public List<Commission> selectCommissionInfoList(Commission commission) {
        List<Commission> commissionList=commissionMapper.selectCommissionInfoList(commission);
        for(Commission commission1:commissionList){
            ConstantValue constantValue=constantValueMapper.selectNewValueByCode(commission1.getContractCode());
            Double d1=0.00;
            Double d2=0.00;
            Double d3=0.00;
            if(constantValue!=null){
                if(StringUtils.isNotNull(constantValue.getConstantValue())&&StringUtils.isNotNull(commission1.getReceivable())){
                    d1=Double.valueOf(constantValue.getConstantValue());
                    d2=Double.valueOf(commission1.getReceivable());
                    d3=d1+d2;
                }
                if(StringUtils.isNotNull(constantValue.getConstantValue())&&StringUtils.isNull(commission1.getReceivable())){
                    d3=Double.valueOf(constantValue.getConstantValue());
                }
            }
            if(commission1.getReceivable()!=null){
                d3=Double.valueOf(commission1.getReceivable());
            }
            commission1.setReceivable(""+d3);
        }

        return commissionList;
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

    @Override
    public String importContract(List<Commission> List) {
        if (StringUtils.isNull(List) || List.size() == 0)
        {
            throw new BusinessException("导入合同数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Commission commission : List)
        {
            try
            {
               this.insertCommission(commission);

                successNum++;
                //    successMsg.append("<br/>" + successNum + "、合同名称 " + bussinessContract.getContractName() + " 导入成功");
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、合同名称 " + commission.getContractName() + " 导入失败：";
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

    //计算每个项目的手续费总金额、实收金额、待收金额
    @Override
    public Commission commissionSum(String contractCode) {
        Commission commission=commissionMapper.selectCommissionSum(contractCode);
        ConstantValue constantValue=constantValueMapper.selectNewValueByCode(contractCode);
        String str=commission.getReceivable();//应收金额
        String str1="0.00";//固化值
        if(constantValue!=null){
            str1= constantValue.getConstantValue();
        }
        //总金额
        Double total=0.00;
        if(StringUtils.isNotNull(str) && StringUtils.isNotNull(str1)){
            total=Double.valueOf(str)+Double.valueOf(str1);
        }else if(StringUtils.isNotNull(str) && StringUtils.isNull(str1)){
            total=Double.valueOf(str);
        }else if(StringUtils.isNull(str) && StringUtils.isNotNull(str1)){
            total=Double.valueOf(str1);
        }
        //总待收金额
        Double fundsSurplus=0.00;
        if(StringUtils.isNotNull(commission.getFundsReceived())){
            fundsSurplus=total-Double.valueOf(commission.getFundsReceived());
        }else{
            fundsSurplus=total;
        }
        commission.setFundsSurplus(""+fundsSurplus);
        commission.setReceivable(""+total);
        //非全额清算时的总金额和实收金额 待收金额为0.00
        BussinessContract bussinessContract=bussinessContractMapper.selectBussinessContractByCode(contractCode);
        if(!"2".equals(bussinessContract.getFundWay())){
            commission.setFundsReceived(""+total);
            commission.setFundsSurplus("0.00");
        }
        return commission;
    }

}
