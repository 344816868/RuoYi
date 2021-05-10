package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BussinessFile;

/**
 * 合同文件管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-01
 */
public interface BussinessFileMapper 
{
    /**
     * 查询合同文件管理
     * 
     * @param fileId 合同文件管理ID
     * @return 合同文件管理
     */
    public BussinessFile selectBussinessFileById(Long fileId);

    /**
     * 查询合同文件管理列表
     * 
     * @param bussinessFile 合同文件管理
     * @return 合同文件管理集合
     */
    public List<BussinessFile> selectBussinessFileList(BussinessFile bussinessFile);

    /**
     * 新增合同文件管理
     * 
     * @param bussinessFile 合同文件管理
     * @return 结果
     */
    public int insertBussinessFile(BussinessFile bussinessFile);

    /**
     * 修改合同文件管理
     * 
     * @param bussinessFile 合同文件管理
     * @return 结果
     */
    public int updateBussinessFile(BussinessFile bussinessFile);

    public int updateBussinessFileByCode(BussinessFile bussinessFile);

    /**
     * 删除合同文件管理
     * 
     * @param fileId 合同文件管理ID
     * @return 结果
     */
    public int deleteBussinessFileById(Long fileId);

    /**
     * 批量删除合同文件管理
     * 
     * @param fileIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBussinessFileByIds(String[] fileIds);

    public int deleteBussinessFileByCode(String contractCode);

    public int selectBussinessFileNumBycontractCode(String contractCode);
}
