

<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.biz.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import lombok.Data;

@Data
@TableName("${table.sqlName}")
public class ${className} extends Model<${className}>  implements java.io.Serializable{
private static final long serialVersionUID = 5454155825314635342L;


<#list table.pkColumns as column>
    /**
     * ${column.columnAlias!}       db_column: ${column.sqlName}
     */
    @TableId(value="${column.sqlName}", type= IdType.AUTO)
    private ${column.javaType} ${column.columnNameLower};


    @Override
    protected Serializable pkVal() {
        return this.${column.columnNameLower};
    }
</#list>
<#list table.notPkColumns as column>

    /**
     * ${column.columnAlias!}       db_column: ${column.sqlName}
     */
    @TableField("${column.sqlName}")
    private ${column.javaType} ${column.columnNameLower};
</#list>
//columns END

}
