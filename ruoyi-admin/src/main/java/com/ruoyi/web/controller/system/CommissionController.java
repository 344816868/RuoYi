package com.ruoyi.web.controller.system;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.BussinessContract;
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
import com.ruoyi.system.domain.Commission;
import com.ruoyi.system.service.ICommissionService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 手续费管理Controller
 * 
 * @author ruoyi
 * @date 2021-02-04
 */
@Controller
@RequestMapping("/system/commission")
public class CommissionController extends BaseController
{
    private String prefix = "system/commission";

    @Autowired
    private ICommissionService commissionService;

    @Autowired
    private ServerConfig serverConfig;

    @RequiresPermissions("system:commission:view")
    @GetMapping()
    public String commission()
    {
        return prefix + "/commission";
    }

    @RequiresPermissions("system:commission:view")
    @GetMapping("/info/{contractCode}")
    public String commissionInfo(@PathVariable("contractCode") String contractCode, ModelMap mmap)
    {
        mmap.put("contractCode", contractCode);
        return prefix + "/commissionInfo";
    }


    /**
     * 查询手续费管理列表
     */
    @RequiresPermissions("system:commission:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Commission commission)
    {
        startPage();
        List<Commission> list = commissionService.selectCommissionList(commission);
        return getDataTable(list);
    }

    /**
     * 查询发票详情
     */
    @RequiresPermissions("system:commission:list")
    @PostMapping("/infoList/{contractCode}")
    @ResponseBody
    public TableDataInfo infoList(Commission commission)
    {
        startPage();
        List<Commission> list = commissionService.selectCommissionList(commission);
        return getDataTable(list);
    }
    /**
     * 导出手续费管理列表
     */
    @RequiresPermissions("system:commission:export")
    @Log(title = "手续费管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Commission commission)
    {
        List<Commission> list = commissionService.selectCommissionList(commission);
        ExcelUtil<Commission> util = new ExcelUtil<Commission>(Commission.class);
        return util.exportExcel(list, "手续费发票信息");
    }

    /**
     * 新增手续费管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存手续费管理
     */
    @RequiresPermissions("system:commission:add")
    @Log(title = "手续费管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Commission commission)
    {
        return toAjax(commissionService.insertCommission(commission));
    }

    /**
     * 修改手续费管理
     */
    @GetMapping("/edit/{commissionId}")
    public String edit(@PathVariable("commissionId") Long commissionId, ModelMap mmap)
    {
        Commission commission = commissionService.selectCommissionById(commissionId);
        mmap.put("commission", commission);
        return prefix + "/edit";
    }

    /**
     * 根据项目编号修改手续费管理
     */
    @GetMapping("/editCode/{contractCode}")
    public String editByContractCode(@PathVariable("contractCode") String contractCode, ModelMap mmap)
    {
        Commission commission = commissionService.selectCommissionByCode(contractCode);
        mmap.put("commission", commission);
        return prefix + "/edit";
    }

    /**
     * 修改保存手续费管理
     */
    @RequiresPermissions("system:commission:edit")
    @Log(title = "手续费管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Commission commission)
    {
        return toAjax(commissionService.updateCommission(commission));
    }

    /**
     * 删除手续费管理
     */
    @RequiresPermissions("system:commission:remove")
    @Log(title = "手续费管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(commissionService.deleteCommissionByIds(ids));
    }


    /**
     * 发票上传
     */
    @RequestMapping("/filesUpload")
    @ResponseBody
    public AjaxResult uploadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception
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
     * 发票下载
     */
    @GetMapping("/download/commission")
    public void contractDownload(Long commissionId, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        Commission commission=commissionService.selectCommissionById(commissionId);
        String filePath=commission.getFilePath();
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
     * 发票预览
     */
    @GetMapping("/commissionPDF/{commissionId}")
    public String contractPDF(@PathVariable("commissionId") Long commissionId, ModelMap mmap)
    {

        mmap.put("commission", commissionService.selectCommissionById(commissionId));
        return prefix + "/commissionPDF";
    }
}
