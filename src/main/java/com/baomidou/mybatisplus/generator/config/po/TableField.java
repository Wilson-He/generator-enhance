/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.generator.config.po;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RegExUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 表字段信息
 *
 * @author YangHu
 * @since 2016-12-03
 */
@Data
@Accessors(chain = true)
public class TableField {

    private boolean convert;
    private boolean keyFlag;
    /**
     * 主键是否为自增类型
     */
    private boolean keyIdentityFlag;
    private String name;
    private String type;
    private String propertyName;
    private IColumnType columnType;
    private String comment;
    /**
     * 字段枚举值
     */
    private List<TableFieldComment> fieldEnums;
    private String fieldEnumsString;
    private String fill;
    /**
     * 自定义查询字段列表
     */
    private Map<String, Object> customMap;

    /**
     * 正则匹配常量注释,如：(1-已删除YES, 0-未删除NO),(1:已删除YES, 0:未删除NO)
     */
    private static final String COMMENT_REGEX_SUFFIX = "((\\W+|\\w+)(-|:)\\W+\\w+,?\\s?)+";
    /**
     * 正则匹配常量注释,如：(YES:已删除, NO:未删除, D:未删除),(YES-已删除, NO-未删除)
     */
    private static final String COMMENT_REGEX_PURE = "((\\W+|\\w+)(-|:)(\\W+|\\w+,?\\s?)+)";
    private static final String COMMENT_REGEX = COMMENT_REGEX_PURE + "|" + COMMENT_REGEX_SUFFIX;

    public boolean isConstantField() {
        if (null == comment) {
            return false;
        }
        fieldEnumsString = substringBetween(comment, "(", ")");
        if (null == fieldEnumsString || !Pattern.matches(COMMENT_REGEX, fieldEnumsString)) {
            return false;
        }
        if (null == fieldEnums) {
            initCommentConstantList();
        }
        return true;
    }

    private void initCommentConstantList() {
        // 范例：isDelete(YES:1-已删除,NO:0-未删除)  state(ENABLE:可用,DISABLE:不可用)
        String filterCommentPattern = "[^\\W+]|-|\\s+";
        String filterNamePattern = "[^a-zA-Z_]";
        String filterValuePattern = "\\D+";
        List<String> varComments = Arrays.asList(RegExUtils.removePattern(fieldEnumsString, filterCommentPattern).split(","));
        List<String> varNames = Arrays.stream(fieldEnumsString.replaceAll(filterNamePattern, ",").split(","))
                .filter(e -> e.matches("\\w+"))
                .collect(Collectors.toList());
        List<String> varValues = Arrays.stream(fieldEnumsString.replaceAll(filterValuePattern, ",").split(","))
                .filter(e -> e.matches("\\d+"))
                .collect(Collectors.toList());
        if (varValues.isEmpty()) {
            varValues = varNames;
        }
        int constantNum = varComments.size();
        fieldEnums = new ArrayList<>(constantNum);
        String clazz = columnType.getType();
        for (int i = 0; i < constantNum; i++) {
            fieldEnums.add(new TableFieldComment(varNames.get(i), varValues.get(i), varComments.get(i), clazz));
        }
    }

    public TableField setConvert(boolean convert) {
        this.convert = convert;
        return this;
    }

    protected TableField setConvert(StrategyConfig strategyConfig) {
        if (strategyConfig.isEntityTableFieldAnnotationEnable()) {
            this.convert = true;
            return this;
        }
        if (strategyConfig.isCapitalModeNaming(name)) {
            this.convert = false;
        } else {
            // 转换字段
            if (NamingStrategy.underline_to_camel == strategyConfig.getColumnNaming()) {
                // 包含大写处理
                if (StringUtils.containsUpperCase(name)) {
                    this.convert = true;
                }
            } else if (!name.equals(propertyName)) {
                this.convert = true;
            }
        }
        return this;
    }

    public TableField setPropertyName(StrategyConfig strategyConfig, String propertyName) {
        this.propertyName = propertyName;
        this.setConvert(strategyConfig);
        return this;
    }

    public String getPropertyType() {
        if (null != columnType) {
            return columnType.getType();
        }
        return null;
    }

    /**
     * 按JavaBean规则来生成get和set方法
     *
     * @return capitalName
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

    private static String substringBetween(final String str, final String open, final String close) {
        final int INDEX_NOT_FOUND = -1;
        if (str == null || open == null || close == null) {
            return null;
        }
        final int start = str.indexOf(open);
        if (start != INDEX_NOT_FOUND) {
            final int end = str.indexOf(close, start + open.length());
            if (end != INDEX_NOT_FOUND) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }
}
