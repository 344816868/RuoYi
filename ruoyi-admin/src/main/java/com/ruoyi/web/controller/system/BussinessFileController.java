package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.BussinessContract;
import com.ruoyi.system.domain.BussinessFile;
import com.ruoyi.system.service.IBussinessContractService;
import com.ruoyi.system.service.IBussinessFileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 合同文件管理Controller
 * 
 * @author ruoyi
 * @date 2021-03-01
 */
@Controller
@RequestMapping("/system/bussinessFile")
public class BussinessFileController extends BaseController
{
    private String prefix = "system/bussinessFile";

    @Autowired
    private IBussinessFileService bussinessFileService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IBussinessContractService bussinessContractService;

    @RequiresPermissions("system:bussinessFile:view")
    @GetMapping()
    public String file()
    {
        return prefix + "/bussinessFile";
    }

    @RequiresPermissions("system:bussinessFile:view")
    @GetMapping("/bussinessFile/{contractCode}")
    public String bussinessFile(@PathVariable("contractCode") String contractCode,ModelMap mmap)
    {
        mmap.put("contractCode",contractCode);
        return prefix + "/bussinessFile";
    }

    /**
     * 查询合同文件管理列表
     */
    @RequiresPermissions("system:bussinessFile:list")
    @PostMapping("/list/{contractCode}")
    @ResponseBody
    public TableDataInfo list(BussinessFile bussinessFile)
    {
        startPage();
        List<BussinessFile> list = bussinessFileService.selectBussinessFileList(bussinessFile);
        return getDataTable(list);
    }

    /**
     * 导出合同文件管理列表
     */
    @RequiresPermissions("system:bussinessFile:export")
    @Log(title = "合同文件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BussinessFile bussinessFile)
    {
        List<BussinessFile> list = bussinessFileService.selectBussinessFileList(bussinessFile);
        ExcelUtil<BussinessFile> util = new ExcelUtil<BussinessFile>(BussinessFile.class);
        return util.exportExcel(list, "bussinessFile");
    }

    /**
     * 新增合同文件管理
     */
    @GetMapping("/add/{contractCode}")
    public String add(@PathVariable("contractCode") String contractCode,ModelMap mmap)
    {
        BussinessContract bussinessContract=bussinessContractService.selectBussinessContractByCode(contractCode);
        mmap.put("bussinessContract",bussinessContract);
        return prefix + "/add";
    }

    /**
     * 新增保存合同文件管理
     */
    @RequiresPermissions("system:bussinessFile:add")
    @Log(title = "合同文件管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BussinessFile bussinessFile)
    {
        return toAjax(bussinessFileService.insertBussinessFile(bussinessFile));
    }

    /**
     * 修改合同文件管理
     */
    @GetMapping("/edit/{fileId}")
    public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap)
    {
        BussinessFile bussinessFile = bussinessFileService.selectBussinessFileById(fileId);
        mmap.put("bussinessFile", bussinessFile);
        return prefix + "/edit";
    }

    /**
     * 修改保存合同文件管理
     */
    @RequiresPermissions("system:bussinessFile:edit")
    @Log(title = "合同文件管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BussinessFile bussinessFile)
    {
        return toAjax(bussinessFileService.updateBussinessFile(bussinessFile));
    }

    /**
     * 删除合同文件管理
     */
    @RequiresPermissions("system:bussinessFile:remove")
    @Log(title = "合同文件管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bussinessFileService.deleteBussinessFileByIds(ids));
    }

    /**
     * 合同下载
     */
    @GetMapping("/download/bussinessFile")
    public void contractDownload(Long fileId, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BussinessFile bussinessFile=bussinessFileService.selectBussinessFileById(fileId);
        String filePath=bussinessFile.getFilePath();
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
    @GetMapping("/bussinessFilePDF/{fileId}")
    public String contractPDF(@PathVariable("fileId") Long fileId, ModelMap mmap)
    {

        mmap.put("bussinessFile", bussinessFileService.selectBussinessFileById(fileId));
        return prefix + "/contractPDF";
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


}
