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

import com.baomidou.mybatisplus.toolkit.StringUtils;
import per.baomidou.mybatisplus.generator.config.StrategyConfig;
import per.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 表字段信息
 * </p>
 *
 * @author YangHu
 * @since 2016-12-03
 */
public class TableField {

    private boolean convert;
    private boolean keyFlag;
    /**
     * 主键是否为自增类型
     */
    private boolean keyIdentityFlag;
    private String name;
    /**
     * 数据库字段定义,如: id smallint,则type=smallint; name character varying(100),则type=character varying(100)
     */
    private String type;
    private String propertyName;
    /**
     * DbColumnType 模板field.columnType = DbColumnType的定义名,如DbColumnType.BIGINT,则field.columnType=BIGINT
     */
    private DbColumnType columnType;
    private String comment;
    private String fill;
    /**
     * 自定义查询字段列表
     */
    private Map<String, Object> customMap;

    public boolean isConstantField() {
        return StringUtils.isNotEmpty(comment) && comment.contains("(") && comment.endsWith(")")
                && (comment.contains("-") || comment.contains(":"));
    }

    public List<TableFieldComment> getFieldConstants() {
        // isDelete(YES:1-已删除,NO:0-未删除)  state(ENABLE:可用,DISABLE:不可用)
        String constantString = comment.substring(comment.lastIndexOf('(') + 1, comment.length() - 1);
        String[] constantMap = constantString.split(",");
        List<TableFieldComment> commentConstantList = new ArrayList<>(constantMap.length);
        String clazz = columnType.getCanonicalName();
        // [YES:1-已删除,NO:0-未删除]
        for (String metadata : constantMap) {
            if (metadata.contains(":") && metadata.contains("-")) {
                String key = metadata.split(":")[0];
                int commentSeparator = metadata.indexOf('-');
                String value = metadata.substring(metadata.indexOf(':') + 1, commentSeparator);
                String comment = metadata.substring(commentSeparator + 1);
                commentConstantList.add(new TableFieldComment(key, columnType.valueToString(value), comment, clazz));
            } else if (metadata.contains(":") || metadata.contains("-")) {
                String separator = metadata.contains(":") ? ":" : "-";
                String[] comment = metadata.split(separator);
                commentConstantList.add(new TableFieldComment(comment[0], comment[1], clazz));
            }
        }
        return commentConstantList;
    }

    public boolean isConvert() {
        return convert;
    }

    protected void setConvert(StrategyConfig strategyConfig) {
        if (strategyConfig.isCapitalModeNaming(name)) {
            this.convert = false;
        } else {
            // 转换字段
            if (StrategyConfig.DB_COLUMN_UNDERLINE) {
                // 包含大写处理
                if (StringUtils.containsUpperCase(name)) {
                    this.convert = true;
                }
            } else if (!name.equals(propertyName)) {
                this.convert = true;
            }
        }
    }

    public void setConvert(boolean convert) {
        this.convert = convert;
    }

    public boolean isKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    public boolean isKeyIdentityFlag() {
        return keyIdentityFlag;
    }

    public void setKeyIdentityFlag(boolean keyIdentityFlag) {
        this.keyIdentityFlag = keyIdentityFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(StrategyConfig strategyConfig, String propertyName) {
        this.propertyName = propertyName;
        this.setConvert(strategyConfig);
    }

    public DbColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(DbColumnType columnType) {
        this.columnType = columnType;
    }

    public String getPropertyType() {
        if (null != columnType) {
            return columnType.getType();
        }
        return null;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 按JavaBean规则来生成get和set方法
     *
     * @return 字段首字母大写字符串
     */
    public String getCapitalName() {
        if (propertyName.length() <= 1) {
            return propertyName.toUpperCase();
        }
        String setGetName = propertyName;
        if (DbColumnType.BASE_BOOLEAN.getType().equalsIgnoreCase(columnType.getType())) {
            setGetName = StringUtils.removeIsPrefixIfBoolean(setGetName, Boolean.class);
        }
        // 第一个字母 小写、 第二个字母 大写 ，特殊处理
        String firstChar = setGetName.substring(0, 1);
        if (Character.isLowerCase(firstChar.toCharArray()[0])
                && Character.isUpperCase(setGetName.substring(1, 2).toCharArray()[0])) {
            return firstChar.toLowerCase() + setGetName.substring(1);
        }
        return firstChar.toUpperCase() + setGetName.substring(1);
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public Map<String, Object> getCustomMap() {
        return customMap;
    }

    public void setCustomMap(Map<String, Object> customMap) {
        this.customMap = customMap;
    }
}
