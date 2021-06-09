package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.ICommissionService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private BussinessReceivableMapper bussinessReceivableMapper;
    @Autowired
    private BussinessFileMapper bussinessFileMapper;

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
            //查询每个项目的初始化实收和待收金额
            Map<String,Object> constantValueSum=constantValueMapper.selectSum(commission1.getContractCode());
            BussinessContract bussinessContract=bussinessContractMapper.selectBussinessContractByCode(commission1.getContractCode());
            //查询每个项目的应收金额
            Double receivableSum=bussinessReceivableMapper.getReceivableSum(commission1.getContractCode());
            Double totalValue=0.0;//总应收金额=截止到2020年年底的实收+待收+每年应收
            Double fundsSurplusVal;//每个项目的总待收金额=总应收金额-总实收金额
            Double fundsReceivedSum;//总实收金额=每年的实收金额+截止到2020年年底的实收
            if(constantValueSum!=null && receivableSum !=null){
                //初始化实收金额
                Double constanSum =Double.valueOf(constantValueSum.get("CONSTRAINTSUM").toString());
                //初始化待收金额
                Double daishouSum=Double.valueOf(constantValueSum.get("DAISHOUSUM").toString());
                if(constanSum!=null && daishouSum!=null){
                    totalValue=constanSum + daishouSum+receivableSum;
                    commission1.setReceivable(""+totalValue);
                    if(commission1.getFundsReceived()!=null){
                        fundsReceivedSum=constanSum+Double.valueOf(commission1.getFundsReceived());
                        fundsSurplusVal=totalValue-fundsReceivedSum;
                    }else{
                        fundsSurplusVal=totalValue-constanSum;
                    }
                    commission1.setFundsSurplus(""+fundsSurplusVal);
                }else if(constanSum!=null && daishouSum==null){
                    totalValue=constanSum +receivableSum;
                    commission1.setReceivable(""+totalValue);
                    if(commission1.getFundsReceived()!=null){
                        fundsReceivedSum=constanSum+Double.valueOf(commission1.getFundsReceived());
                        fundsSurplusVal=totalValue-fundsReceivedSum;
                    }else{
                        fundsSurplusVal=totalValue-constanSum;
                    }
                    commission1.setFundsSurplus(""+fundsSurplusVal);
                }else if(constanSum==null && daishouSum!=null){
                    totalValue=daishouSum +receivableSum;
                    commission1.setReceivable(""+totalValue);
                    if(commission1.getFundsReceived()!=null){
                        fundsReceivedSum=constanSum+Double.valueOf(commission1.getFundsReceived());
                        fundsSurplusVal=totalValue-fundsReceivedSum;
                    }else{
                        fundsSurplusVal=totalValue-constanSum;
                    }
                    commission1.setFundsSurplus(""+fundsSurplusVal);
                }
            }else if(constantValueSum!=null && receivableSum==null){
                //初始化实收金额
                Double constanSum =Double.valueOf(constantValueSum.get("CONSTRAINTSUM").toString());
                //初始化待收金额
                Double daishouSum=Double.valueOf(constantValueSum.get("DAISHOUSUM").toString());
                if(constanSum!=null && daishouSum!=null){
                    totalValue=constanSum + daishouSum;
                    commission1.setReceivable(""+totalValue);
                    if(commission1.getFundsReceived()!=null){
                        fundsSurplusVal=totalValue - Double.valueOf(commission1.getFundsReceived());
                    }else{
                        fundsSurplusVal=totalValue;
                    }
                    commission1.setFundsSurplus(""+fundsSurplusVal);
                } else if(constanSum!=null && daishouSum==null){
                    totalValue=constanSum ;
                    commission1.setReceivable(""+totalValue);
                    if(commission1.getFundsReceived()!=null){
                        fundsReceivedSum=constanSum+Double.valueOf(commission1.getFundsReceived());
                        fundsSurplusVal=totalValue-fundsReceivedSum;
                    }else{
                        fundsSurplusVal=totalValue-constanSum;
                    }
                    commission1.setFundsSurplus(""+fundsSurplusVal);
                }else if(constanSum==null && daishouSum!=null){
                    totalValue=daishouSum;
                    commission1.setReceivable(""+totalValue);
                    if(commission1.getFundsReceived()!=null){
                        fundsReceivedSum=constanSum+Double.valueOf(commission1.getFundsReceived());
                        fundsSurplusVal=totalValue-fundsReceivedSum;
                    }else{
                        fundsSurplusVal=totalValue-constanSum;
                    }
                    commission1.setFundsSurplus(""+fundsSurplusVal);
                }
            }else if(constantValueSum==null && receivableSum!=null){
                totalValue=receivableSum;
                commission1.setReceivable(""+totalValue);
                if(commission1.getFundsReceived()!=null){
                    fundsSurplusVal=totalValue - Double.valueOf(commission1.getFundsReceived());
                }else{
                    fundsSurplusVal=totalValue;
                }
                commission1.setFundsSurplus(""+fundsSurplusVal);
            }else{
                totalValue=0.00;
                if(commission1.getFundsReceived()!=null){
                    fundsSurplusVal=totalValue - Double.valueOf(commission1.getFundsReceived());
                }else{
                    fundsSurplusVal=totalValue;
                }
                commission1.setFundsSurplus(""+fundsSurplusVal);
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
        //计算总金额，总实收金额，总待收金额
        Map<String, Object> map=new HashMap<>();
        Double receivableSum=bussinessReceivableMapper.getReceivableSum(null);
        Map<String, Object> constantValueMap=constantValueMapper.selectSum(null);
        //总实收金额
        Double commissionSum=commissionMapper.getCommissionSum();
        Double total=0.00;//总应收
        Double fReceivanle=0.00;//总实收
        Double fplus=0.00;//总代收
        if(receivableSum!=null && constantValueMap!=null){
            Double constanSum=0.00; //初始化实收金额
            Double daishouSum=0.00; //初始化待收金额
            if(constantValueMap.get("CONSTRAINTSUM")!=null && constantValueMap.get("DAISHOUSUM")!=null){
                constanSum =Double.valueOf(constantValueMap.get("CONSTRAINTSUM").toString());
                daishouSum=Double.valueOf(constantValueMap.get("DAISHOUSUM").toString());
            }else if(constantValueMap.get("CONSTRAINTSUM")!=null && constantValueMap.get("DAISHOUSUM")==null){
                constanSum =Double.valueOf(constantValueMap.get("CONSTRAINTSUM").toString());

            }else if(constantValueMap.get("CONSTRAINTSUM")==null && constantValueMap.get("DAISHOUSUM")!=null){
                daishouSum=Double.valueOf(constantValueMap.get("DAISHOUSUM").toString());
            }
            total=receivableSum+constanSum+daishouSum;
            map.put("RECEIABLESUM",""+total);
            if(commissionSum!=null){
                fReceivanle=commissionSum+constanSum;
                Double fundssurplus=total-fReceivanle;
                map.put("FUNDSSURPLUS",""+fundssurplus);
                map.put("FUNDSRECEIVED",""+fReceivanle);
            }else if(commissionSum ==null){
                map.put("FUNDSSURPLUS",""+total);
                map.put("FUNDSRECEIVED","0.00");
            }
        } else if(receivableSum!=null && constantValueMap==null){
            total=receivableSum;
            if(commissionSum!=null){
                fReceivanle=commissionSum;
                Double fundssurplus=total-fReceivanle;
                map.put("FUNDSSURPLUS",""+fundssurplus);
                map.put("FUNDSRECEIVED",""+fReceivanle);
            }else if(commissionSum ==null){
                map.put("FUNDSSURPLUS",""+total);
                map.put("FUNDSRECEIVED","0.00");
            }
        } else if(receivableSum==null && constantValueMap!=null){
            Double constanSum=0.00; //初始化实收金额
            Double daishouSum=0.00; //初始化待收金额
            if(constantValueMap.get("CONSTRAINTSUM")!=null && constantValueMap.get("DAISHOUSUM")!=null){
                constanSum =Double.valueOf(constantValueMap.get("CONSTRAINTSUM").toString());
                daishouSum=Double.valueOf(constantValueMap.get("DAISHOUSUM").toString());
            }else if(constantValueMap.get("CONSTRAINTSUM")!=null && constantValueMap.get("DAISHOUSUM")==null){
                constanSum =Double.valueOf(constantValueMap.get("CONSTRAINTSUM").toString());

            }else if(constantValueMap.get("CONSTRAINTSUM")==null && constantValueMap.get("DAISHOUSUM")!=null){
                daishouSum=Double.valueOf(constantValueMap.get("DAISHOUSUM").toString());
            }
            total=constanSum+daishouSum;
            if(commissionSum!=null){
                fReceivanle=commissionSum+constanSum;
                Double fundssurplus=total-fReceivanle;
                map.put("FUNDSSURPLUS",""+fundssurplus);
                map.put("FUNDSRECEIVED",""+fReceivanle);
            }else if(commissionSum ==null){
                map.put("FUNDSSURPLUS",""+total);
                map.put("FUNDSRECEIVED","0.00");
            }
        } else{
            map.put("RECEIABLESUM","0.00");
            map.put("FUNDSRECEIVED","0.00");
            map.put("FUNDSSURPLUS","0.00");
        }

        return map;
    }

//    @Override
//    public Map<String, Object> selectSum() {
//        //计算总金额，总实收金额，总待收金额
//        Map<String, Object> map=new HashMap<>();
//        Map<String, Object> commissionMap=commissionMapper.selectSum();
//        Map<String, Object> constantValueMap=constantValueMapper.selectConstantSum();
//        Double total=0.00;
//        Double fReceivanle=0.00;
//        Double fplus=0.00;
//        if(commissionMap!=null && constantValueMap!=null){
//            String str="0.00";
//            if(commissionMap.get("RECEIVABLE")!=null){
//                str=commissionMap.get("RECEIVABLE").toString();
//            }
//            Double receivable=Double.valueOf(str);
//            String str2="0.00";
//            if(constantValueMap.get("CONSTRAINTSUM")!=null){
//                str2=constantValueMap.get("CONSTRAINTSUM").toString();
//            }
//            Double contantSum=Double.valueOf(str2);
//            total=receivable+contantSum;
//            map.put("RECEIABLESUM",""+total);
//            if(commissionMap.get("FUNDSSURPLUS")==null&&commissionMap.get("FUNDSRECEIVED")!=null){
//                Double fundssurplus=total-Double.valueOf(commissionMap.get("FUNDSRECEIVED").toString());
//                map.put("FUNDSSURPLUS",""+fundssurplus);
//                map.put("FUNDSRECEIVED",commissionMap.get("FUNDSRECEIVED").toString());
//            }else if(commissionMap.get("FUNDSSURPLUS")==null&&commissionMap.get("FUNDSRECEIVED")==null){
//                map.put("FUNDSSURPLUS",""+total);
//                map.put("FUNDSRECEIVED","0.00");
//            }else{
//                Double fundssurplus=total-Double.valueOf(commissionMap.get("FUNDSRECEIVED").toString());
//                map.put("FUNDSSURPLUS",""+fundssurplus);
//                map.put("FUNDSRECEIVED",commissionMap.get("FUNDSRECEIVED").toString());
//            }
//        } else if(commissionMap==null&&constantValueMap!=null){
//            String sum=constantValueMap.get("CONSTRAINTSUM").toString();
//            map.put("RECEIABLESUM",sum);
//            map.put("FUNDSRECEIVED","0.00");
//            map.put("FUNDSSURPLUS",sum);
//        } else if(commissionMap!=null&&constantValueMap==null){
//            if(commissionMap.get("RECEIVABLE")!=null){
//                total=Double.valueOf(commissionMap.get("RECEIVABLE").toString());
//            }
//            if(commissionMap.get("FUNDSRECEIVED")!=null){
//                fReceivanle=Double.valueOf(commissionMap.get("FUNDSRECEIVED").toString());
//            }
//            if(commissionMap.get("RECEIVABLE")!=null && commissionMap.get("FUNDSRECEIVED")!=null){
//                fplus=Double.valueOf(commissionMap.get("RECEIVABLE").toString())-Double.valueOf(commissionMap.get("FUNDSRECEIVED").toString());
//            }else if(commissionMap.get("RECEIVABLE")!=null && commissionMap.get("FUNDSRECEIVED")==null){
//                fplus=Double.valueOf(commissionMap.get("RECEIVABLE").toString());
//            }else if(commissionMap.get("RECEIVABLE")==null && commissionMap.get("FUNDSRECEIVED")!=null){
//                fplus=0-Double.valueOf(commissionMap.get("FUNDSRECEIVED").toString());
//            }
//            map.put("RECEIABLESUM",total);
//            map.put("FUNDSRECEIVED",fReceivanle);
//            map.put("FUNDSSURPLUS",fplus);
//        } else{
//            map.put("RECEIABLESUM","0.00");
//            map.put("FUNDSRECEIVED","0.00");
//            map.put("FUNDSSURPLUS","0.00");
//        }
//
//        return map;
//    }

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
        //查询旧数据
        Commission commission1=commissionMapper.selectCommissionById(commission.getCommissionId());
        if(commission1!=null){
            //修改项目信息
            BussinessContract bussinessContract=bussinessContractMapper.selectBussinessContractByCode(commission1.getContractCode());
            bussinessContract.setContractCode(commission.getContractCode());
            bussinessContract.setContractName(commission.getContractName());
            bussinessContractMapper.updateBussinessContract(bussinessContract);
            //修改应收金额信息
            BussinessReceivable bussinessReceivable = new BussinessReceivable();
            bussinessReceivable.setContractCode(commission.getContractCode());
            bussinessReceivable.setUpdateContractCode(commission1.getContractCode());
            bussinessReceivableMapper.updateBussinessReceivableByCode(bussinessReceivable);
            //修改合同文件信息
            BussinessFile bussinessFile = new BussinessFile();
            bussinessFile.setContractCode(commission.getContractCode());
            bussinessFile.setContractName(commission.getContractName());
            bussinessFile.setUpdateContractCode(commission1.getContractCode());
            bussinessFileMapper.updateBussinessFileByCode(bussinessFile);
            //修改固化值信息
            ConstantValue constantValue = new ConstantValue();
            constantValue.setContractCode(commission.getContractCode());
            constantValue.setContractName(commission.getContractName());
            constantValue.setUpdateContractCode(commission1.getContractCode());
            constantValueMapper.updateConstantValueByCode(constantValue);


            if("1".equals(commission.getUpdateType())){
                commission.setUpdateContractCode(commission1.getContractCode());
                commissionMapper.updateCommissionByCode(commission);
            }
        }
        int count=commissionMapper.updateCommission(commission);
        return count;
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
        String str="0.00";//应收金额
        String str1="0.00";//固化值
        String str3="0.00";//实收金额
        if(constantValue!=null){
            str1= constantValue.getConstantValue();
        }
        if(commission!=null){
            if(StringUtils.isNotNull(commission.getReceivable())){
                str=commission.getReceivable();
            }
            if(StringUtils.isNotNull(commission.getFundsReceived())){
                str3= commission.getFundsReceived();
            }else{
                commission.setFundsReceived(str3);
            }

        }else{
            commission=new Commission();
            BussinessReceivable bussinessReceivable=bussinessReceivableMapper.selectBussinessReceivableByCode(contractCode);
            if(bussinessReceivable!=null){
                str=bussinessReceivable.getReceivable();
            }
            commission.setFundsReceived(str3);
        }
        //总金额
        Double total=0.00;
        if(!"0.00".equals(str) && !"0.00".equals(str1)){
            total=Double.valueOf(str)+Double.valueOf(str1);
        }else if(!"0.00".equals(str) && "0.00".equals(str1)){
            total=Double.valueOf(str);
        }else if("0.00".equals(str) && !"0.00".equals(str1)){
            total=Double.valueOf(str1);
        }
        //总待收金额
        Double fundsSurplus=0.00;
        if(!"0.00".equals(str3)){
            fundsSurplus=total-Double.valueOf(str3);
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
