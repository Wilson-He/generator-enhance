package ${package.Constant};

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    @AllArgsConstructor
    @Getter
    enum ${field.propertyName?capFirst} {
        <#list field.fieldEnums as fieldEnum>
        /**
         * ${fieldEnum.comment}
         */
            <#if fieldEnum_has_next>
        ${fieldEnum.key}(${fieldEnum.value}, "${fieldEnum.comment}"),
            <#else >
        ${fieldEnum.key}(${fieldEnum.value}, "${fieldEnum.comment}");
            </#if>
        </#list>
        private ${field.columnType.type} value;
        private String comment;
        public static final Map<${field.columnType.type}, String> MAP = Collections.unmodifiableMap(Arrays.stream(${field.propertyName?capFirst}.values())
            .collect(Collectors.toMap(constant -> constant.value, constant -> constant.comment)));

        public static String getComment(${field.columnType.type} value) {
            return MAP.get(value);
        }

        public boolean equalsVal(${field.columnType.type} val) {
            return this.value.equals(val);
        }
    }

    </#if>
</#list>
}
