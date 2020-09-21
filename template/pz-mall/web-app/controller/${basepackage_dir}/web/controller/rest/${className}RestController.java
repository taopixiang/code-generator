<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.web.controller.rest;


import ${basepackage}.dto.${className}Dto;
import ${basepackage}.web.remote.${className}ServiceRemote;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.plugins.Page;
import io.platform.base.common.annotation.SysLog;
import io.platform.base.common.controller.AbstractController;
import io.platform.base.common.utils.ExecuteResult;
import io.platform.base.common.utils.PageUtils;
import io.platform.base.common.utils.R;
import io.platform.base.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/${namespace}/${classNameLower}")
public class ${className}RestController extends AbstractController {

    @Autowired
	private ${className}ServiceRemote ${classNameLower}ServiceRemote;
	
	/**
	 * 所有列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("${namespace}:${classNameLower}:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		return R.ok().put("list", ${classNameLower}ServiceRemote.getList(params));
	}

	/**
	 * 分页列表
	 */
	@RequestMapping("/page")
	@RequiresPermissions("${namespace}:${classNameLower}:page")
	public R page(Page page,${className}Dto ${classNameLower}Dto){
		return R.ok().put("page", ${classNameLower}ServiceRemote.getPage(page,${classNameLower}Dto));
	}
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("${namespace}:${classNameLower}:info")
	public R info(@PathVariable("id") Long id){
		${className}Dto ${classNameLower}Dto = ${classNameLower}ServiceRemote.getOne(id);
		return R.ok().put("${classNameLower}Dto", ${classNameLower}Dto);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存")
	@RequestMapping("/save")
	@RequiresPermissions("${namespace}:${classNameLower}:save")
	public R save(@RequestBody ${className}Dto ${classNameLower}Dto){
        ValidatorUtils.validateEntity(${classNameLower}Dto);
        ExecuteResult<String> result= ${classNameLower}ServiceRemote.save(${classNameLower}Dto);
        if(result.isSuccess()){
            return R.ok(result.getSuccessMessage());
        }else{
            return R.error(result.getErrorMessages().get(0));
        }
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	@RequiresPermissions("${namespace}:${classNameLower}:edit")
	public R update(@RequestBody ${className}Dto ${classNameLower}Dto){
        ValidatorUtils.validateEntity(${classNameLower}Dto);
        ExecuteResult<String> result= ${classNameLower}ServiceRemote.update(${classNameLower}Dto);
        if(result.isSuccess()){
        return R.ok(result.getSuccessMessage());
        }else{
        return R.error(result.getErrorMessages().get(0));
        }
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除")
	@RequestMapping("/delete")
	@RequiresPermissions("${namespace}:${classNameLower}:delete")
	public R delete(@RequestBody List<Long> ids){
        ExecuteResult<String> result= ${classNameLower}ServiceRemote.deleteBatch(ids);
        if(result.isSuccess()){
        return R.ok(result.getSuccessMessage());
        }else{
        return R.error(result.getErrorMessages().get(0));
        }
	}

}
