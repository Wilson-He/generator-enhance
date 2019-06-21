package ${package.Constant};

import com.google.common.collect.ImmutableBiMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ${entity}常量类
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity}Constant {
<#list table.fields as field>
<#if field.constantField && swagger2>
    String ${field.name?upperCase}_COMMENT = "${field.comment?split("(")[0]}(<#list field.fieldEnums as fieldEnum><#if fieldEnum_has_next>${fieldEnum.originValue}-${fieldEnum.comment}, <#else >${fieldEnum.originValue}-${fieldEnum.comment}</#if></#list>)";
</#if>
</#list>

<#list table.fields as field>
    <#if field.constantField>
    /**
     * ${field.comment}
     */
    interface ${field.propertyName?capFirst} {
        <#list field.fieldEnums as fieldEnum>
        /**
         * ${fieldEnum.comment}
         */
        ${field.columnType.type} ${fieldEnum.key} = ${fieldEnum.value};
        </#list>
        Map<${field.columnType.type}, String> MAP = ImmutableBiMap.of(
        <#list field.fieldEnums as fieldEnum>
            <#if fieldEnum_has_next>
                ${fieldEnum.value}, "${fieldEnum.comment}",
            <#else >
                ${fieldEnum.value}, "${fieldEnum.comment}");
            </#if>
        </#list>
    }

    </#if>
</#list>
}
