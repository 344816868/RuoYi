package com.ruoyi.quartz.task;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.BussinessContract;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IBussinessContractService;
import com.ruoyi.system.service.ISysUserService;
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
    @Autowired
    private ISysUserService sysUserService;

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

    /**
     * 定时发送合同到期短信提醒
     */
    public void postMessage()
    {
        System.out.println("发送短信提醒");
        BussinessContract bussinessContract = new BussinessContract();
        List<BussinessContract> list=bussinessContractService.selectExportBussinessContract(bussinessContract);
        int total=list.size();//即将过期的项目数量
        if(total>0){
            //需要发送短信的用户
            List<SysUser> sysUsers=sysUserService.selectSendUserList("0");
            if(sysUsers.size()>0){
                JSONObject sendJson=new JSONObject();
                int totalNum=sysUsers.size();
                String [] userPhones=new String[totalNum];
                for(int i=0;i<sysUsers.size();i++){
                    JSONObject phoneJson=new JSONObject();
                    phoneJson.put("reqSN",sysUsers.get(i).getPhonenumber());
                    userPhones[i]=phoneJson.toJSONString();
                }
                String msgBody="有"+totalNum+"份项目合同即将到期";
                sendJson.put("msgType","DX001");
                sendJson.put("MsgBody",msgBody);
                sendJson.put("transDate",DateUtils.dateTimeNow());
                sendJson.put("totalNum",totalNum);
                sendJson.put("dealList",userPhones);
                String param=sendJson.toString();
                System.out.println(param);
                //调用发送短信的接口
                String url="http://10.222.37.6:9001";
                String result=HttpUtils.sendPost(url,param);
                System.out.println(result);
            }
        }

    }

}
