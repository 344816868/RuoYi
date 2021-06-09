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
import com.ruoyi.system.domain.BussinessReceivable;
import com.ruoyi.system.service.IBussinessReceivableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 应收金额Controller
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
@Controller
@RequestMapping("/system/receivable")
public class BussinessReceivableController extends BaseController
{
    private String prefix = "system/receivable";

    @Autowired
    private IBussinessReceivableService bussinessReceivableService;

    @RequiresPermissions("system:receivable:view")
    @GetMapping()
    public String receivable()
    {
        return prefix + "/receivable";
    }

    /**
     * 查询应收金额列表
     */
    @RequiresPermissions("system:receivable:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BussinessReceivable bussinessReceivable)
    {
        startPage();
        List<BussinessReceivable> list = bussinessReceivableService.selectBussinessReceivableList(bussinessReceivable);
        return getDataTable(list);
    }

    /**
     * 导出应收金额列表
     */
    @RequiresPermissions("system:receivable:export")
    @Log(title = "应收金额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BussinessReceivable bussinessReceivable)
    {
        List<BussinessReceivable> list = bussinessReceivableService.selectBussinessReceivableList(bussinessReceivable);
        ExcelUtil<BussinessReceivable> util = new ExcelUtil<BussinessReceivable>(BussinessReceivable.class);
        return util.exportExcel(list, "receivable");
    }

    /**
     * 新增应收金额
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存应收金额
     */
    @RequiresPermissions("system:receivable:add")
    @Log(title = "应收金额", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BussinessReceivable bussinessReceivable)
    {
        return toAjax(bussinessReceivableService.insertBussinessReceivable(bussinessReceivable));
    }

    /**
     * 修改应收金额
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BussinessReceivable bussinessReceivable = bussinessReceivableService.selectBussinessReceivableById(id);
        mmap.put("bussinessReceivable", bussinessReceivable);
        return prefix + "/edit";
    }

    /**
     * 修改保存应收金额
     */
    @RequiresPermissions("system:receivable:edit")
    @Log(title = "应收金额", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BussinessReceivable bussinessReceivable)
    {
        return toAjax(bussinessReceivableService.updateBussinessReceivable(bussinessReceivable));
    }

    /**
     * 删除应收金额
     */
    @RequiresPermissions("system:receivable:remove")
    @Log(title = "应收金额", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bussinessReceivableService.deleteBussinessReceivableByIds(ids));
    }

   // @RequiresPermissions("system:receivable:import")
    @PostMapping("/importReceivable")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<BussinessReceivable> util = new ExcelUtil<BussinessReceivable>(BussinessReceivable.class);
        List<BussinessReceivable> list = util.importExcel(file.getInputStream());
        String message = bussinessReceivableService.importContract(list);
        return AjaxResult.success(message);
    }

    /**
     * 导出excel模板
     * @return
     */
    @RequiresPermissions("business:contract:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BussinessReceivable> util = new ExcelUtil<BussinessReceivable>(BussinessReceivable.class);
        return util.importTemplateExcel("应收金额信息");
    }
}
