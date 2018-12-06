/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package per.baomidou.mybatisplus.generator.config.po;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import per.baomidou.mybatisplus.generator.config.StrategyConfig;

/**
 * <p>
 * 表信息，关联到当前字段信息
 * </p>
 *
 * @author YangHu
 * @since 2016/8/30
 */
@Setter
@Getter
public class TableInfo {

    private boolean convert;
    private String name;
    private String comment;

    private String entityName;
    private String mapperName;
    private String xmlName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private String controllerImplName;
    private String voAddName;
    private String voUpdateName;
    private String dtoGetName;
    private String dtoPageName;

    private List<TableField> fields;
    /**
     * 公共字段
     */
    private List<TableField> commonFields;
    private List<String> importPackages = new ArrayList<>();
    private String fieldNames;
    private String constantName;

    public boolean hasConstantField() {
        for (TableField field : fields) {
            if (field.isConstantField()) {
                return true;
            }
        }
        return false;
    }

    public String getPropertyAlias() {
        StringBuilder builder = new StringBuilder(300);
        for (TableField each : fields) {
            builder.append(name)
                    .append("_")
                    .append(each.getName())
                    .append(",\r\n\t\t");
        }
        return builder.substring(0, builder.lastIndexOf(","));
    }

    public String getInsertFields() {
        return fields.stream()
                .map(TableField::getName)
                .filter(e -> !org.apache.commons.lang3.StringUtils.equalsAny(e, "update_time", "create_time", "delete_flag"))
                .reduce((x, y) -> x + "," + y)
                .orElse("");
    }

    public String getInsertFieldProperties() {
        StringBuilder builder = new StringBuilder(300);
        for (TableField each : fields) {
            String field = each.getPropertyName();
            if (org.apache.commons.lang3.StringUtils.equalsAny(field, "updateTime", "createTime", "deleteFlag")) {
                continue;
            }
            builder.append("#{item.").append(field).append("},");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    public boolean isConvert() {
        return convert;
    }

    private void setConvert(StrategyConfig strategyConfig) {
        if (strategyConfig.containsTablePrefix(name)) {
            // 包含前缀
            this.convert = true;
        } else if (strategyConfig.isCapitalModeNaming(name)) {
            // 包含
            this.convert = false;
        } else {
            // 转换字段
            if (StrategyConfig.DB_COLUMN_UNDERLINE) {
                // 包含大写处理
                if (StringUtils.containsUpperCase(name)) {
                    this.convert = true;
                }
            } else if (!entityName.equalsIgnoreCase(name)) {
                this.convert = true;
            }
        }
    }

    public void setConvert(boolean convert) {
        this.convert = convert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null || comment.length() == 0 ? null : comment;
    }

    public String getEntityPath() {
        StringBuilder ep = new StringBuilder();
        ep.append(entityName.substring(0, 1).toLowerCase());
        ep.append(entityName.substring(1));
        return ep.toString();
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(StrategyConfig strategyConfig, String entityName) {
        this.entityName = entityName;
        this.setConvert(strategyConfig);
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public String getControllerImplName() {
        return controllerName + "Impl";
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public void setControllerImplName(String controllerImplName) {
        this.controllerImplName = controllerName;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        if (CollectionUtils.isNotEmpty(fields)) {
            this.fields = fields;
            // 收集导入包信息
            Set<String> pkgSet = new HashSet<>();
            for (TableField field : fields) {
                if (null != field.getColumnType() && null != field.getColumnType().getPkg()) {
                    pkgSet.add(field.getColumnType().getPkg());
                }
                if (field.isKeyFlag()) {
                    // 主键
                    if (field.isConvert() || field.isKeyIdentityFlag()) {
                        pkgSet.add("com.baomidou.mybatisplus.annotations.TableId");
                    }
                    // 自增
                    if (field.isKeyIdentityFlag()) {
                        pkgSet.add("com.baomidou.mybatisplus.enums.IdType");
                    }
                } else if (field.isConvert()) {
                    // 普通字段
                    pkgSet.add("com.baomidou.mybatisplus.annotations.TableField");
                }
                if (null != field.getFill()) {
                    // 填充字段
                    pkgSet.add("com.baomidou.mybatisplus.annotations.TableField");
                    pkgSet.add("com.baomidou.mybatisplus.enums.FieldFill");
                }
            }
            if (!pkgSet.isEmpty()) {
                this.importPackages = new ArrayList<>(Arrays.asList(pkgSet.toArray(new String[]{})));
            }
        }
    }

    public List<TableField> getCommonFields() {
        return commonFields;
    }

    public void setCommonFields(List<TableField> commonFields) {
        this.commonFields = commonFields;
    }

    public List<String> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(String pkg) {
        importPackages.add(pkg);
    }

    /**
     * 逻辑删除
     */
    public boolean isLogicDelete(String logicDeletePropertyName) {
        for (TableField tableField : fields) {
            if (tableField.getName().equals(logicDeletePropertyName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 转换filed实体为xmlmapper中的basecolumn字符串信息
     *
     * @return
     */
    public String getFieldNames() {
        if (StringUtils.isEmpty(fieldNames)) {
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < fields.size(); i++) {
                TableField fd = fields.get(i);
                if (i == fields.size() - 1) {
                    names.append(cov2col(fd));
                } else {
                    names.append(cov2col(fd)).append(", ");
                }
            }
            fieldNames = names.toString();
        }
        return fieldNames;
    }

    /**
     * mapper xml中的字字段添加as
     *
     * @param field 字段实体
     * @return 转换后的信息
     */
    private String cov2col(TableField field) {
        if (null != field) {
            return field.isConvert() ? field.getName() + " AS " + field.getPropertyName() : field.getName();
        }
        return StringUtils.EMPTY;
    }

}
