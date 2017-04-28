<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${namespace}">
  <resultMap id="BaseResultMap" type="${entityType}">
    <id column="ID" jdbcType="BIGINT" property="id" />
${resultMap}
  </resultMap>
  
  <sql id="Base_Column_List">
    ${baseColumn}
  </sql>

  <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="${entityType}">
    SELECT
    <include refid="Base_Column_List" />
    FROM ${tableName}
    WHERE IS_DELETE = 'N'
    AND id = <#noparse>#{id, jdbcType=BIGINT}</#noparse>
  </select>

  <select id="selectListByExample" resultMap="BaseResultMap" parameterType="${entityType}">
    SELECT
    <include refid="Base_Column_List" />
    FROM ${tableName}
    where IS_DELETE = 'N'
${findListConditon}
  </select>

  <select id="selectPageByExample" resultMap="BaseResultMap" parameterType="com.winit.common.query.Searchable">
    SELECT
    <include refid="Base_Column_List" />
    FROM ${tableName}
    WHERE IS_DELETE = 'N'
  </select>

  <insert id="insertSelective" parameterType="${entityType}" useGeneratedKeys="true" keyProperty="id">
    INSERT INTO ${tableName}
    <trim prefix="(" suffix=")" suffixOverrides=",">
${insertIfColumns}
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
${insertIfProps}
    </trim>
  </insert>
  
  <insert id="insertSelectiveBatch" parameterType="java.util.List">
    INSERT INTO ${tableName}
    (${insertBatchColumns})
    VALUES
    <foreach collection="list" item="item" index="index" separator=",">
       (${insertBatchProps})
    </foreach>
  </insert>
  
  <update id="updateByPrimaryKey" parameterType="${entityType}">
    update ${tableName}
    <set>
${updateColProps}
    </set>
    where ID = <#noparse>#{id,jdbcType=BIGINT}</#noparse>
  </update>
  
  <update id="updateByPrimaryKeyBatch" parameterType="java.util.List">
    <foreach collection="list" item="item" index="index" open="" close="" separator=";">  
        UPDATE ${tableName}
        <set>
${updateBatchColProps}
        </set>
        WHERE ID = <#noparse>#{item.id,jdbcType=BIGINT}</#noparse>
    </foreach> 
  </update>
  
  <update id="deleteByPrimaryKey" parameterType="${entityType}" >
    update ${tableName} set UPDATED = <#noparse>#{updated,jdbcType=TIMESTAMP}</#noparse>,UPDATEDBY = <#noparse>#{updatedby,jdbcType=VARCHAR}</#noparse>, IS_DELETE = 'Y'
    where ID = <#noparse>#{id,jdbcType=BIGINT}</#noparse>
  </update>
  
  <update id="deleteByPrimaryKeyBatch" parameterType="java.util.List" >
    <foreach collection="list" item="item" index="index" open="" close="" separator=";">  
        update ${tableName}
        set UPDATED = <#noparse>#{item.updated,jdbcType=TIMESTAMP}</#noparse>,UPDATEDBY = <#noparse>#{item.updatedby,jdbcType=VARCHAR}</#noparse>, IS_DELETE = 'Y'
        where ID = <#noparse>#{item.id,jdbcType=BIGINT}</#noparse>
    </foreach> 
  </update>
</mapper>
