package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequiresPermissions("system:commission:view")
    @GetMapping()
    public String commission()
    {
        return prefix + "/commission";
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
        return util.exportExcel(list, "commission");
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
}
