package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BussinessContract;
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
import com.ruoyi.system.domain.ConstantValue;
import com.ruoyi.system.service.IConstantValueService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 固化值管理Controller
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
@Controller
@RequestMapping("/system/constantValue")
public class ConstantValueController extends BaseController
{
    private String prefix = "system/constantValue";

    @Autowired
    private IConstantValueService constantValueService;

    @RequiresPermissions("system:constantValue:view")
    @GetMapping()
    public String constantValue()
    {
        return prefix + "/constantValue";
    }

    /**
     * 查询固化值管理列表
     */
    @RequiresPermissions("system:constantValue:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConstantValue constantValue)
    {
        startPage();
        List<ConstantValue> list = constantValueService.selectConstantValueList(constantValue);
        return getDataTable(list);
    }

    /**
     * 导出固化值管理列表
     */
    @RequiresPermissions("system:constantValue:export")
    @Log(title = "固化值管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConstantValue constantValue)
    {
        List<ConstantValue> list = constantValueService.selectConstantValueList(constantValue);
        ExcelUtil<ConstantValue> util = new ExcelUtil<ConstantValue>(ConstantValue.class);
        return util.exportExcel(list, "固化值信息");
    }

    /**
     * 新增固化值管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存固化值管理
     */
    @RequiresPermissions("system:constantValue:add")
    @Log(title = "固化值管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConstantValue constantValue)
    {
        return toAjax(constantValueService.insertConstantValue(constantValue));
    }

    /**
     * 修改固化值管理
     */
    @GetMapping("/edit/{valueId}")
    public String edit(@PathVariable("valueId") Long valueId, ModelMap mmap)
    {
        ConstantValue constantValue = constantValueService.selectConstantValueById(valueId);
        mmap.put("constantValue", constantValue);
        return prefix + "/edit";
    }

    /**
     * 修改保存固化值管理
     */
    @RequiresPermissions("system:constantValue:edit")
    @Log(title = "固化值管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConstantValue constantValue)
    {
        return toAjax(constantValueService.updateConstantValue(constantValue));
    }

    /**
     * 删除固化值管理
     */
    @RequiresPermissions("system:constantValue:remove")
    @Log(title = "固化值管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(constantValueService.deleteConstantValueByIds(ids));
    }

    /**
     * 导出excel模板
     * @return
     */
    @RequiresPermissions("system:constantValue:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<ConstantValue> util = new ExcelUtil<ConstantValue>(ConstantValue.class);
        return util.importTemplateExcel("固化值信息");
    }

    @Log(title = "固化值信息管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("business:contract:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<ConstantValue> util = new ExcelUtil<ConstantValue>(ConstantValue.class);
        List<ConstantValue> list = util.importExcel(file.getInputStream());
        String message = constantValueService.importContract(list);
        return AjaxResult.success(message);
    }

    @PostMapping("/addDatas")
    @ResponseBody
    public AjaxResult addDatas(){
        return toAjax(constantValueService.addDatas());
    }

}
