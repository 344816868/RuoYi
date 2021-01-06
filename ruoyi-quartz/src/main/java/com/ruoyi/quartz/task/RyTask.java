package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BussinessContract;
import com.ruoyi.system.service.IBussinessContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    @Autowired
    private IBussinessContractService bussinessContractService;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    /**
     * 定时判断合同的到期状态
     */
    public void checkContractStatus()
    {
        System.out.println("检查合同状态");
        Long now=new Date().getTime();
        BussinessContract bussinessContract = new BussinessContract();
        List<BussinessContract> list=bussinessContractService.selectBussinessContractList(bussinessContract);
        for(BussinessContract bussinessContract1:list){
            //当前时间和合同截止时间比较
            Long endDateLong=bussinessContract1.getEndTime().getTime();
            //修改合同过期状态
            if(now>endDateLong){
                bussinessContract1.setStatus("1");
                bussinessContractService.updateBussinessContract(bussinessContract1);
            }
        }
    }
}
