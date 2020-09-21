<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.service.client.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;


import ${basepackage}.biz.entity.${className};
import ${basepackage}.biz.service.${className}Service;
import ${basepackage}.dto.${className}Dto;
import ${basepackage}.service.client.api.${className}ServiceClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import io.platform.base.common.BaseServiceClient;
import io.platform.base.common.utils.ExecuteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by sevelli on 2017/8/31.
 */
@Component
@Service()
public class ${className}ServiceClientImpl extends BaseServiceClient<${className},${className}Dto> implements ${className}ServiceClient {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ${className}Service ${classNameLower}Service;

    @Override
    public ${className}Dto queryObject(Long id) {
        ${className} ${classNameLower} = ${classNameLower}Service.selectById(id);
        ${className}Dto ${classNameLower}Dto = new ${className}Dto();
        BeanUtils.copyProperties(${classNameLower},${classNameLower}Dto);
        return ${classNameLower}Dto;
    }

    @Override
    public List<${className}Dto> queryList(Map<String, Object> map) {
        List<${className}> ${classNameLower}List = ${classNameLower}Service.selectByMap(map);
        return convertList(${classNameLower}List,${className}Dto.class);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        EntityWrapper<${className}> ew = new EntityWrapper<>();
        ew.allEq(map);
        return ${classNameLower}Service.selectCount(ew);
    }

    @Override
    public ExecuteResult<String> save(${className}Dto ${classNameLower}Dto) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            ${className} ${classNameLower} = new ${className}();
            revert(${classNameLower}Dto,${classNameLower});
            boolean flag = ${classNameLower}Service.insert(${classNameLower});
            if(!flag){
                result.addErrorMessage("保存失败。");
            }
        }
        catch (Exception e) {
            logger.error(" 保存错误：" + e.toString(), e);
            result.addErrorMessage("保存错误：" + e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<String> update(${className}Dto ${classNameLower}Dto) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            ${className} ${classNameLower} = new ${className}();
            revert(${classNameLower}Dto,${classNameLower});

            boolean flag = ${classNameLower}Service.updateById(${classNameLower});
            if(!flag){
                result.addErrorMessage("更新失败。");
            }
        }
        catch (Exception e) {
            logger.error(" 更新错误：" + e.toString(), e);
            result.addErrorMessage("更新错误：" + e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<String> delete(Long id) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            boolean flag = ${classNameLower}Service.deleteById(id);
            if(!flag){
                result.addErrorMessage("删除失败。");
            }
        }
        catch (Exception e) {
            logger.error(" 删除错误：" + e.toString(), e);
            result.addErrorMessage("删除错误：" + e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<String> deleteBatch(List<Long> ids) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            boolean flag = ${classNameLower}Service.deleteBatchIds(ids);
            if(!flag){
                result.addErrorMessage("批量删除失败。");
            }
        }
        catch (Exception e) {
            logger.error(" 删除错误：" + e.toString(), e);
            result.addErrorMessage("删除错误：" + e.toString());
        }
        return result;
    }

    @Override
    public Page<${className}Dto> getPage(Page<${className}Dto> pageDto,${className}Dto ${classNameLower}Dto) {
        Page<${className}> page = new Page<>(pageDto.getCurrent(),pageDto.getSize());
        page.setAsc(false);
        page.setOrderByField("create_time");
        page.setCondition(pageDto.getCondition());
        ${className} ${classNameLower} = new ${className}();
        revert(${classNameLower}Dto,${classNameLower});
        EntityWrapper<${className}> wrapper = new EntityWrapper<>();
        wrapper.setEntity(${classNameLower});
        page = ${classNameLower}Service.selectPage(page,wrapper);
        List<${className}Dto> ${classNameLower}DtoList = convertList(page.getRecords(),${className}Dto.class);
        pageDto.setTotal(${classNameLower}Service.selectCount(wrapper));
        pageDto.setRecords(${classNameLower}DtoList);
        return pageDto;
    }
}
