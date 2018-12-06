package ${package.VO};

import ${package.Entity}.${entity};
import org.springframework.beans.BeanUtils;

import io.swagger.annotations.ApiModel;

/**
 * ${table.voUpdateName}
 *
 * @author ${author}
 * @since ${date}
 */
@ApiModel("更新<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}</#if><#else>${entity}</#if>VO")
public class ${table.voUpdateName}{

}
