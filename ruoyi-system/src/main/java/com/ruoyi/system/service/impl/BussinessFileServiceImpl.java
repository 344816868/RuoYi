package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BussinessFileMapper;
import com.ruoyi.system.domain.BussinessFile;
import com.ruoyi.system.service.IBussinessFileService;
import com.ruoyi.common.core.text.Convert;

/**
 * 合同文件管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-01
 */
@Service
public class BussinessFileServiceImpl implements IBussinessFileService 
{
    @Autowired
    private BussinessFileMapper bussinessFileMapper;

    /**
     * 查询合同文件管理
     * 
     * @param fileId 合同文件管理ID
     * @return 合同文件管理
     */
    @Override
    public BussinessFile selectBussinessFileById(Long fileId)
    {
        return bussinessFileMapper.selectBussinessFileById(fileId);
    }

    /**
     * 查询合同文件管理列表
     * 
     * @param bussinessFile 合同文件管理
     * @return 合同文件管理
     */
    @Override
    public List<BussinessFile> selectBussinessFileList(BussinessFile bussinessFile)
    {
        return bussinessFileMapper.selectBussinessFileList(bussinessFile);
    }

    /**
     * 新增合同文件管理
     * 
     * @param bussinessFile 合同文件管理
     * @return 结果
     */
    @Override
    public int insertBussinessFile(BussinessFile bussinessFile)
    {
        bussinessFile.setCreateTime(DateUtils.getNowDate());
        return bussinessFileMapper.insertBussinessFile(bussinessFile);
    }

    /**
     * 修改合同文件管理
     * 
     * @param bussinessFile 合同文件管理
     * @return 结果
     */
    @Override
    public int updateBussinessFile(BussinessFile bussinessFile)
    {
        return bussinessFileMapper.updateBussinessFile(bussinessFile);
    }

    /**
     * 删除合同文件管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBussinessFileByIds(String ids)
    {
        return bussinessFileMapper.deleteBussinessFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同文件管理信息
     * 
     * @param fileId 合同文件管理ID
     * @return 结果
     */
    @Override
    public int deleteBussinessFileById(Long fileId)
    {
        return bussinessFileMapper.deleteBussinessFileById(fileId);
    }

    @Override
    public int deleteBussinessFileByCode(String contractCode) {
        return bussinessFileMapper.deleteBussinessFileByCode(contractCode);
    }
}
