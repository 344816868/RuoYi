package com.ruoyi.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IBussinessFileService;
import com.ruoyi.system.service.ICommissionService;
import com.ruoyi.system.service.ISysDictDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IBussinessContractService;
import com.ruoyi.common.core.text.Convert;

/**
 * 合同管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-22
 */
@Service
public class BussinessContractServiceImpl implements IBussinessContractService 
{
    @Autowired
    private BussinessContractMapper bussinessContractMapper;
    @Autowired
    private ICommissionService commissionService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private IBussinessFileService bussinessFileService;
    @Autowired
    private CommissionMapper commissionMapper;
    @Autowired
    private BussinessReceivableMapper bussinessReceivableMapper;
    @Autowired
    private ConstantValueMapper constantValueMapper;
    @Autowired
    private BussinessFileMapper bussinessFileMapper;

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /**
     * 查询合同管理
     * 
     * @param contractId 合同管理ID
     * @return 合同管理
     */
    @Override
    public BussinessContract selectBussinessContractById(Long contractId)
    {
        return bussinessContractMapper.selectBussinessContractById(contractId);
    }

    @Override
    public BussinessContract selectBussinessContractByCode(String contractCode) {
        return bussinessContractMapper.selectBussinessContractByCode(contractCode);
    }

    /**
     * 查询合同管理列表
     * 
     * @param bussinessContract 合同管理
     * @return 合同管理
     */
    @Override
    public List<BussinessContract> selectBussinessContractList(BussinessContract bussinessContract)
    {
        List<BussinessContract> list=bussinessContractMapper.selectBussinessContractList(bussinessContract);
        if(list!=null){
            for (int i=0;i<list.size();i++){
                BussinessContract bussinessContract1=list.get(i);
                int fileNum=bussinessFileMapper.selectBussinessFileNumBycontractCode(bussinessContract1.getContractCode());
                bussinessContract1.setFileNum(fileNum);
            }
        }
        return list;
    }

    @Override
    public List<BussinessContract> selectExportBussinessContractList(BussinessContract bussinessContract) {
        List<BussinessContract> bussinessContractList=bussinessContractMapper.selectExportBussinessContractList(bussinessContract);
        for(BussinessContract bussinessContract1:bussinessContractList){
            Commission commission = new Commission();
            commission.setContractCode(bussinessContract.getContractCode());
            List<Commission> commissionList=commissionService.selectCommissionList(commission);
            if(commissionList.size()>0){
                bussinessContract1.setReceivable(commissionList.get(0).getReceivable());
                bussinessContract1.setFundsReceived(commissionList.get(0).getFundsReceived());
                bussinessContract1.setFundsSurplus(commissionList.get(0).getFundsSurplus());
            }

        }

        return bussinessContractList;
    }

    /**
     * 新增合同管理
     * 
     * @param bussinessContract 合同管理
     * @return 结果
     */
    @Override
    public int insertBussinessContract( BussinessContract bussinessContract)
    {
    //    Commission commission1=commissionService.selectCommissionByCode(bussinessContract.getContractCode());
        Commission commission = new Commission();
        commission.setContractCode(bussinessContract.getContractCode());
        commission.setContractName(bussinessContract.getContractName());
        commission.setReceivable(bussinessContract.getReceivable());//应收
        commission.setFundsReceived(bussinessContract.getFundsReceived());//实收
        commission.setFundsSurplus(bussinessContract.getFundsSurplus());//待收
     //   if(StringUtils.isNull(commission1)){
//            commissionService.insertCommission(commission);
//        }
        //添加手续费信息
        commissionService.insertCommission(commission);
        bussinessContract.setStatus("0");//默认合同状态是正常的未过期的
        //当前时间和合同截止时间比较
        Long now=new Date().getTime();
        Long endDateLong;
        if(StringUtils.isNotNull(bussinessContract.getEndTime())){
            endDateLong=bussinessContract.getEndTime().getTime();
            //修改合同过期状态
            if(now>endDateLong){
                bussinessContract.setStatus("1");
            }
        }

        return bussinessContractMapper.insertBussinessContract(bussinessContract);
    }

    /**
     * 导入添加项目信息
     * @param bussinessContract 合同管理
     * @return
     */
    public int importInsertBussinessContract( BussinessContract bussinessContract)
    {
        //添加合同文件基本信息
//        BussinessFile bussinessFile = new BussinessFile();
//        bussinessFile.setContractCode(bussinessContract.getContractCode());
//        bussinessFile.setContractName(bussinessContract.getContractName());
//        bussinessFile.setUploadTime(DateUtils.getNowDate());
//        bussinessFileService.insertBussinessFile(bussinessFile);

        bussinessContract.setStatus("0");//默认合同状态是正常的未过期的
        //当前时间和合同截止时间比较
        Long now=new Date().getTime();
        Long endDateLong;
        if(StringUtils.isNotNull(bussinessContract.getEndTime())){
            endDateLong=bussinessContract.getEndTime().getTime();
            //修改合同过期状态
            if(now>endDateLong){
                bussinessContract.setStatus("1");
            }
        }

        return bussinessContractMapper.insertBussinessContract(bussinessContract);
    }

    /**
     * 修改合同管理
     * 
     * @param bussinessContract 合同管理
     * @return 结果
     */
    @Override
    public int updateBussinessContract(BussinessContract bussinessContract)
    {
        //查询数据库中的旧数据
        BussinessContract bussinessContract1=bussinessContractMapper.selectBussinessContractById(bussinessContract.getContractId());
        if(!bussinessContract.getContractCode().equals(bussinessContract1.getContractCode()) || !bussinessContract.getContractName().equals(bussinessContract1.getContractName())){
            //修改手续费信息
            Commission commission=new Commission();
            commission.setContractCode(bussinessContract.getContractCode());
            commission.setContractName(bussinessContract.getContractName());
            commission.setUpdateContractCode(bussinessContract1.getContractCode());
            commissionMapper.updateCommissionByCode(commission);
            //修改应收金额项目编号
            BussinessReceivable bussinessReceivable = new BussinessReceivable();
            bussinessReceivable.setContractCode(bussinessContract.getContractCode());
            bussinessReceivable.setUpdateContractCode(bussinessContract1.getContractCode());
            bussinessReceivableMapper.updateBussinessReceivableByCode(bussinessReceivable);
            //修改固化值项目编号信息
            ConstantValue constantValue = new ConstantValue();
            constantValue.setContractCode(bussinessContract.getContractCode());
            constantValue.setContractName(bussinessContract.getContractName());
            constantValue.setUpdateContractCode(bussinessContract1.getContractCode());
            constantValueMapper.updateConstantValueByCode(constantValue);
            //修改合同文件的项目信息
            BussinessFile bussinessFile = new BussinessFile();
            bussinessFile.setContractCode(bussinessContract.getContractCode());
            bussinessFile.setContractName(bussinessContract.getContractName());
            bussinessFile.setUpdateContractCode(bussinessContract1.getContractCode());
            bussinessFileMapper.updateBussinessFileByCode(bussinessFile);
        }

        //当前时间和合同截止时间比较
        Long now=new Date().getTime();
        Long endDateLong;
        if(StringUtils.isNotNull(bussinessContract.getEndTime())){
            endDateLong =bussinessContract.getEndTime().getTime();
            //修改合同过期状态
            if(now>endDateLong){
                bussinessContract.setStatus("1");
            }
            //判断是否续约，续约就会增加一条手续费信息
            if(StringUtils.isNotNull(bussinessContract1.getEndTime())){
                Long oldEndDateLong=bussinessContract1.getEndTime().getTime();
                if(endDateLong>oldEndDateLong){
                    Commission commission1=new Commission();
                    commission1.setContractCode(bussinessContract.getContractCode());
                    commission1.setContractName(bussinessContract.getContractName());
                    commissionService.insertCommission(commission1);
                }
            }
        }else{
            bussinessContract.setStatus("0");
        }

        return bussinessContractMapper.updateBussinessContract(bussinessContract);
    }

    /**
     * 删除合同管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBussinessContractByIds(String ids)
    {
        List<BussinessContract> list=bussinessContractMapper.selectBussinessContractListByIds(Convert.toStrArray(ids));
        for(BussinessContract bussinessContract:list){
            commissionService.deleteCommissionByCode(bussinessContract.getContractCode());//删除发票
            bussinessFileService.deleteBussinessFileByCode(bussinessContract.getContractCode());//删除合同
        }
        return bussinessContractMapper.deleteBussinessContractByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同管理信息
     * 
     * @param contractId 合同管理ID
     * @return 结果
     */
    @Override
    public int deleteBussinessContractById(Long contractId)
    {
        //删除发票信息
        BussinessContract bussinessContract=bussinessContractMapper.selectBussinessContractById(contractId);
    //    Commission commission=commissionService.selectCommissionByCode(bussinessContract.getContractCode());
        commissionService.deleteCommissionByCode(bussinessContract.getContractCode());//删除发票信息
        bussinessFileService.deleteBussinessFileByCode(bussinessContract.getContractCode());//删除合同信息
        return bussinessContractMapper.deleteBussinessContractById(contractId);
    }

    /**
     * 功能描述:导入
     * @author:
     * @param:  * @param null
     * @Date: 16:01 2021/2/3
     * @return:
     */
    @Override
    public String importContract(List<BussinessContract> List) {
        if (StringUtils.isNull(List) || List.size() == 0)
        {
            throw new BusinessException("导入合同数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BussinessContract bussinessContract : List)
        {
            try
            {
                BussinessContract bussinessContract1=this.selectBussinessContractByCode(bussinessContract.getContractCode());
                if (StringUtils.isNull(bussinessContract1)){
                    this.importInsertBussinessContract(bussinessContract);
                }else{
                    bussinessContract.setContractId(bussinessContract1.getContractId());
                    this.updateBussinessContract(bussinessContract);
                }

                successNum++;
            //    successMsg.append("<br/>" + successNum + "、合同名称 " + bussinessContract.getContractName() + " 导入成功");
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "项目编号"+bussinessContract.getContractCode()+"、合同名称 " + bussinessContract.getContractName() + " 导入失败：";
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

    @Override
    public List<BussinessContract> selectExpireBussinessContract(BussinessContract bussinessContract) {
        List<BussinessContract> list=this.selectBussinessContractList(bussinessContract);
        if(list!=null){
            for (int i=0;i<list.size();i++){
                BussinessContract bussinessContract1=list.get(i);
                int fileNum=bussinessFileMapper.selectBussinessFileNumBycontractCode(bussinessContract1.getContractCode());
                bussinessContract1.setFileNum(fileNum);
            }
        }
 //       List<BussinessContract> list = this.selectExportBussinessContractList(bussinessContract);
//        for(BussinessContract bussinessContract1:list){
//            Commission commission = new Commission();
//            commission.setContractCode(bussinessContract.getContractCode());
//            List<Commission> commissionList=commissionService.selectCommissionList(commission);
//            if(commissionList.size()>0){
//                bussinessContract1.setReceivable(commissionList.get(0).getReceivable());
//                bussinessContract1.setFundsReceived(commissionList.get(0).getFundsReceived());
//                bussinessContract1.setFundsSurplus(commissionList.get(0).getFundsSurplus());
//            }
//        }
        return list;
    }
}
