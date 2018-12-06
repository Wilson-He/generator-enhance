package ${package.Constant};

import java.util.HashMap;
import java.util.Map;

/**
 * ${entity}Constant
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity}Constant {
<#list table.fields as field>
    <#if field.constantField>
    interface ${field.propertyName?capFirst} {
        <#list field.fieldConstants as fieldConstant>
        ${fieldConstant.clazz} ${fieldConstant.key} = ${fieldConstant.value};
        </#list>
        String COMMENT = "${field.comment}";
        Map<Object, String> MAP = new HashMap<Object, String>() {
            {
        <#list field.fieldConstants as fieldConstant>
                put(${fieldConstant.key}, "${fieldConstant.comment}");
        </#list>
            }
        };
    }

   </#if>
</#list>
}
