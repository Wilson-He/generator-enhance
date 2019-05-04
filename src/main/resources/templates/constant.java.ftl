package ${package.Constant};

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ${entity}Constant
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity}Constant {
<#list table.fields as field>
    <#if field.constantField>
    /**
     * ${field.comment}
     */
    @AllArgsConstructor
    @Getter
    enum ${field.propertyName?capFirst} {
        <#list field.fieldEnums as fieldEnum>
        /**
         * ${fieldEnum.comment}
         */
            <#if fieldEnum_has_next>
        ${fieldEnum.key}(${fieldEnum.value}),
            <#else >
        ${fieldEnum.key}(${fieldEnum.value});
            </#if>
        </#list>
        private ${field.columnType.type} value;
    }

    </#if>
</#list>
}
