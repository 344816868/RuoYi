package com.ruoyi.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: wangjunchao
 * @Date: 2021/2/19 14:52
 * @Description: 短信发送接口
 * @Version: 1.0
 */
@Component
public class MsgSend {

    @Value("${msgSend.sendUrl}")
    private String sendUrl;

    @Value("${msgSend.msgType}")
    private String msgType;

    private String MsgBody;

    private int totalNum;

    private String[] dealList;


    public void sendMsg(MsgSend msgSend){
        String msgJson = JSON.toJSONString(msgSend);
        System.out.println(msgJson);
    }

    public MsgSend( String msgBody, int totalNum, String[] dealList) {
        this.MsgBody = msgBody;
        this.totalNum = totalNum;
        this.dealList = dealList;
    }

    public static void main(String[] args) {
        int totalNum=3;
        String [] list=new String[totalNum];
        for(int i=0;i<list.length;i++){
            JSONObject json=new JSONObject();
            json.put("reqSN","1583013780"+i);
            list[i]=json.toJSONString();
        }
        String msgBody="有"+totalNum+"份项目合同即将到期";
        MsgSend msgSend = new MsgSend(msgBody,totalNum,list);
        msgSend.sendMsg(msgSend);
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgBody() {
        return MsgBody;
    }

    public void setMsgBody(String msgBody) {
        MsgBody = msgBody;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String[] getDealList() {
        return dealList;
    }

    public void setDealList(String[] dealList) {
        this.dealList = dealList;
    }
}
