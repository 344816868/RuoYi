package com.ruoyi.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Commission;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BussinessContractMapper;
import com.ruoyi.system.domain.BussinessContract;
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
    private CommissionServiceImpl commissionService;
    @Autowired
    private ISysDictDataService sysDictDataService;

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
        return bussinessContractMapper.selectBussinessContractList(bussinessContract);
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
        Commission commission1=commissionService.selectCommissionByCode(bussinessContract.getContractCode());
        Commission commission = new Commission();
        commission.setContractCode(bussinessContract.getContractCode());
        commission.setContractName(bussinessContract.getContractName());
        if(StringUtils.isNull(commission1)){
            commissionService.insertCommission(commission);
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
                    this.insertBussinessContract(bussinessContract);
                }else{
                    this.updateBussinessContract(bussinessContract);
                }

                successNum++;
            //    successMsg.append("<br/>" + successNum + "、合同名称 " + bussinessContract.getContractName() + " 导入成功");
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、合同名称 " + bussinessContract.getContractName() + " 导入失败：";
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
    public List<BussinessContract> selectExportBussinessContract(BussinessContract bussinessContract) {
        //查询合同到期提醒的天数
        List<SysDictData> dicList=sysDictDataService.selectDictDataByType("sys_bussiness_notice");
        String limitTime="0";
        if(dicList.size()>0){
            limitTime= dicList.get(0).getDictValue();
        }
        Long days=Long.valueOf(limitTime);
        Date nowTime=new Date();
        Long l=24*60*60*1000*days;
        //合同提醒的起始时间
        Date endTime=new Date(nowTime.getTime() + l);
        Map<String, Object> params=new HashMap();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        params.put("beginEndTime",sdf.format(nowTime));
        params.put("endEndTime",sdf.format(endTime));
        bussinessContract.setParams(params);
        bussinessContract.setStatus("0");
        List<BussinessContract> list = this.selectBussinessContractList(bussinessContract);
        return list;
    }
}
