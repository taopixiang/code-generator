<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.web.remote;
import com.baomidou.mybatisplus.plugins.Page;
import com.alibaba.dubbo.config.annotation.Reference;
import io.platform.base.common.utils.ExecuteResult;
import io.platform.base.common.utils.PageUtils;
import io.platform.base.common.utils.Query;
import ${basepackage}.dto.${className}Dto;
import ${basepackage}.service.client.api.${className}ServiceClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


/**
 * Created by sevelli on 2017/8/31.
 */
@Service("${classNameLower}ServiceRemote")
public class ${className}ServiceRemote {

    @Reference(check = false)
    private ${className}ServiceClient ${classNameLower}ServiceClient;
    /**
     * 根据参数获取列表  无分页
     */
    public List<${className}Dto> getList(Map<String, Object> params){
        return ${classNameLower}ServiceClient.queryList(params);
    }
    /**
     * 根据参数进行 分页查询
     */
    public Page<${className}Dto> getPage(Page<${className}Dto> page,${className}Dto ${classNameLower}Dto){
        return ${classNameLower}ServiceClient.getPage(page,${classNameLower}Dto);
    }
    /**
     * 根据主键 获取单条数据明细
     */
    public ${className}Dto getOne(Long id){
        ${className}Dto ${classNameLower}Dto = ${classNameLower}ServiceClient.queryObject(id);
        return ${classNameLower}Dto;
    }
    /**
     * 保存 数据
     */
    public ExecuteResult<String> save(${className}Dto ${classNameLower}Dto){

       return  ${classNameLower}ServiceClient.save(${classNameLower}Dto);
    }
    /**
     * 修改单条数据
     */
    public ExecuteResult<String> update(${className}Dto ${classNameLower}Dto){

        return ${classNameLower}ServiceClient.update(${classNameLower}Dto);
    }
    /**
     * 批量删除
     */
    public ExecuteResult<String> deleteBatch(List<Long> ids){
        return  ${classNameLower}ServiceClient.deleteBatch(ids);
    }



}
