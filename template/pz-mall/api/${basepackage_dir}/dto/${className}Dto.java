<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dto;

import lombok.Data;

@Data
public class ${className}Dto  implements java.io.Serializable{
private static final long serialVersionUID = 5454155825314635342L;


<#list table.columns as column>
/**
 * ${column.columnAlias!}       db_column: ${column.sqlName}
 */
private ${column.javaType} ${column.columnNameLower};
</#list>
//columns END

}
