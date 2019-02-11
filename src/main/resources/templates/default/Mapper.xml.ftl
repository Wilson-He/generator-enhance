<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">
<#if baseResultMap>
  <!-- 通用查询映射结果 -->
  <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
    <#list table.fields as field>
            <#if field.keyFlag><#--生成主键排在第一位-->
    <id column="${table.name}_${field.name}" property="${field.propertyName}" jdbcType="${field.columnType}"/>
            </#if>
        </#list>
<#list table.commonFields as field><#--生成公共字段 -->
  <result column="${table.name}_${field.name}" property="${field.propertyName}" jdbcType="${field.columnType}"/>

</#list>
<#list table.fields as field>
    <#if !field.keyFlag><#--生成普通字段 -->
    <result column="${table.name}_${field.name}" property="${field.propertyName}" jdbcType="${field.columnType}"/>
    </#if>
</#list>
  </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
  <sql id="tableProperty">
    ${table.propertyAlias}
  </sql>
</#if>
</mapper>
