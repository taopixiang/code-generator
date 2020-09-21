<#assign className=table.className>
<#assign classNameLower=className?uncap_first>

package ${basepackage}.service.client.api;

import com.baomidou.mybatisplus.plugins.Page;
import ${basepackage}.dto.${className}Dto;

import java.util.List;
import java.util.Map;

import io.platform.base.common.utils.ExecuteResult;

/**
 * Created by sevelli on 2017/8/31.
 */
public interface ${className}ServiceClient{
        /**
         * 根据ID 查询单个对象
         */
        ${className}Dto queryObject(Long id);
        /**
         * 根据参数查询 列表  无分页
         */
        List<${className}Dto>queryList(Map<String, Object> map);
        /**
         * 根据参数查询 对应参数的 集合大小
         */
        int queryTotal(Map<String, Object> map);
        /**
         * 保存对象
         */
        ExecuteResult<String> save(${className}Dto ${classNameLower}Dto);
        /**
         * 更新对象
         */
        ExecuteResult<String> update(${className}Dto ${classNameLower}Dto);
        /**
         * 根据主键 删除对象
         */
        ExecuteResult<String> delete(Long id);
        /**
         * 根据主键集合 批量删除
         */
        ExecuteResult<String> deleteBatch(List<Long> idList);
        /**
         * 根据分页参数 获取 分页集合
         */
        Page<${className}Dto>getPage(Page<${className}Dto>page,${className}Dto ${classNameLower}Dto);
}
