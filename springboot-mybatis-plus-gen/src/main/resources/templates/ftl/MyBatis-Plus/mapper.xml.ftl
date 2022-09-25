<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
    <result column="${field.name}" property="${field.propertyName}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
    </resultMap>

</#if>

    <!-- 通用查询结果列 -->
    <sql id="BaseColumnList">
    <#list table.fields as field>
        <#if field_index != (table.fields?size - 1)>
        ${field.name} as ${field.propertyName},
        <#else>
        ${field.name} as ${field.propertyName}
        </#if>
    </#list>
    </sql>

    <!-- 通用查询条件 -->
    <sql id="BaseWhere">
        <trim prefix="where" prefixOverrides="and | or">
            <#list table.fields as field>
            <if test="${lowerEntityName}.${field.propertyName} != null and ${lowerEntityName}.${field.propertyName} != ''">
                and ${field.name} = <#noparse>#{</#noparse>${lowerEntityName}.${field.propertyName}<#noparse>}</#noparse>
            </if>
            </#list>
        </trim>
    </sql>


    <!-- 分页查询 -->
    <select id="selectPage" resultType="${package.Entity}.${entity}">
        select <include refid="BaseColumnList"/>
        from ${table.name}
        <include refid="BaseWhere"/>
    </select>

</mapper>
