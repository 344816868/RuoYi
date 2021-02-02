package com.ruoyi.web.controller.business;

import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BussinessContract;
import com.ruoyi.system.service.IBussinessContractService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 合同管理Controller
 *
 * @author ruoyi
 * @date 2020-10-22
 */
@Controller
@RequestMapping("/business/contract")
public class BussinessContractController extends BaseController
{
    private String prefix = "business/contract";

    @Autowired
    private IBussinessContractService bussinessContractService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @RequiresPermissions("business:contract:view")
    @GetMapping()
    public String contract()
    {
        return prefix + "/contract";
    }

    /**
     * 即将到期合同页面
     * @return
     */
    @RequiresPermissions("business:contract:View")
    @GetMapping("/expireContract")
    public String expireContract()
    {
        return prefix + "/expireContract";
    }

    /**
     * 查询合同管理列表
     */
    @RequiresPermissions("business:contract:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BussinessContract bussinessContract)
    {
        startPage();
        List<BussinessContract> list = bussinessContractService.selectBussinessContractList(bussinessContract);
        return getDataTable(list);
    }

    /**
     * 查询即将到期合同管理列表
     */
    @RequiresPermissions("business:contract:list")
    @PostMapping("/expireList")
    @ResponseBody
    public TableDataInfo expireList(BussinessContract bussinessContract)
    {
        startPage();
        List<SysDictData> dicList=sysDictDataService.selectDictDataByType("sys_bussiness_notice");
        String limitTime=dicList.get(0).getDictValue();//合同到期提醒的天数
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
        List<BussinessContract> list = bussinessContractService.selectBussinessContractList(bussinessContract);
        return getDataTable(list);
    }

    /**
     * 导出合同管理列表
     */
    @RequiresPermissions("business:contract:export")
    @Log(title = "合同管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BussinessContract bussinessContract)
    {
        List<BussinessContract> list = bussinessContractService.selectBussinessContractList(bussinessContract);
        ExcelUtil<BussinessContract> util = new ExcelUtil<BussinessContract>(BussinessContract.class);
        return util.exportExcel(list, "合同信息列表");
    }

    /**
     * 新增合同管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合同管理
     */
    @RequiresPermissions("business:contract:add")
    @Log(title = "合同管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BussinessContract bussinessContract)
    {
        return toAjax(bussinessContractService.insertBussinessContract(bussinessContract));
    }

    /**
     * 修改合同管理
     */
    @GetMapping("/edit/{contractId}")
    public String edit(@PathVariable("contractId") Long contractId, ModelMap mmap)
    {
        BussinessContract bussinessContract = bussinessContractService.selectBussinessContractById(contractId);
        mmap.put("bussinessContract", bussinessContract);
        return prefix + "/edit";
    }

    /**
     * 修改保存合同管理
     */
    @RequiresPermissions("business:contract:edit")
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BussinessContract bussinessContract)
    {
        return toAjax(bussinessContractService.updateBussinessContract(bussinessContract));
    }

    /**
     * 删除合同管理
     */
    @RequiresPermissions("business:contract:remove")
    @Log(title = "合同管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bussinessContractService.deleteBussinessContractByIds(ids));
    }

    /**
     * 合同上传
     */
    @RequestMapping("/filesUpload")
    @ResponseBody
    public AjaxResult uploadFiles(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        try
        {
            request.setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            /** 页面控件的文件流* */
            MultipartFile multipartFile = null;
            Map map =multipartRequest.getFileMap();
            for (Iterator i = map.keySet().iterator(); i.hasNext();) {
                Object obj = i.next();
                multipartFile=(MultipartFile) map.get(obj);

            }
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, multipartFile);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 合同下载
     */
    @GetMapping("/download/contract")
    public void contractDownload(Long contractId, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BussinessContract bussinessContract = bussinessContractService.selectBussinessContractById(contractId);
        String filePath=bussinessContract.getFilePath();
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(filePath, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, downloadName);

        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }

    /**
     * 合同预览
     */
    @GetMapping("/contractPDF/{contractId}")
    public String contractPDF(@PathVariable("contractId") Long contractId, ModelMap mmap)
    {

        mmap.put("contract", bussinessContractService.selectBussinessContractById(contractId));
        return prefix + "/contractPDF";
    }

}
