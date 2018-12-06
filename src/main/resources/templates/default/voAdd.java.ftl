package ${package.VO};

import ${package.Entity}.${entity};
import org.springframework.beans.BeanUtils;

import io.swagger.annotations.ApiModel;

/**
 * ${table.voAddName}
 *
 * @author ${author}
 * @since ${date}
 */
@ApiModel("添加<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if><#else>${entity}</#if>VO")
public class ${table.voAddName}{

}
