<#assign className = table.className>
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.biz.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import ${basepackage}.biz.mapper.${className}Mapper;
import ${basepackage}.biz.entity.${className};

import org.springframework.stereotype.Service;


@Service("${classNameLower}Service")
public class ${className}Service extends ServiceImpl<${className}Mapper,${className}>{

}
