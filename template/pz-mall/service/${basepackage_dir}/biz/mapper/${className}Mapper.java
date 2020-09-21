
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.biz.mapper;

import io.platform.base.common.BaseMapper;
import ${basepackage}.biz.entity.${className};
import org.apache.ibatis.annotations.Mapper;


/**
 * Created by sevelli on 2017/9/6.
 */
@Mapper
public interface ${className}Mapper extends BaseMapper<${className}>{

}
