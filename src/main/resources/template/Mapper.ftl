<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${namespace}">
  <resultMap id="BaseResultMap" type="${entityType}">
    <id column="ID" jdbcType="BIGINT" property="id" />
${resultMap}
  </resultMap>
  
  <!-- 基本列 -->
  <sql id="Base_Column_List">
    ${baseColumn}
  </sql>
  
  <!-- 单个插入 -->
  <insert id="add" parameterType="${entityType}" useGeneratedKeys="true" keyProperty="id">
    insert into ${tableName}
    <trim prefix="(" suffix=")" suffixOverrides=",">
${insertIfColumns}
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
${insertIfProps}
    </trim>
  </insert>
  
  <!-- 批量新增 -->
  <insert id="addBatch" parameterType="java.util.List">
    INSERT INTO ${tableName}
    (${insertBatchColumns})
    VALUES
    <foreach collection="list" item="item" index="index" separator=",">
       (${insertBatchProps})
    </foreach>
  </insert>
  
  <!-- 单个更新 -->
  <update id="update" parameterType="${entityType}">
    update ${tableName}
    <set>
${updateColProps}
    </set>
    where ID = <#noparse>#{id,jdbcType=BIGINT}</#noparse>
  </update>
  
  <!-- 批量更新 -->
  <update id="updateBatch" parameterType="java.util.List">
    <foreach collection="list" item="item" index="index" open="" close="" separator=";">  
        UPDATE ${tableName}
        <set>
${updateBatchColProps}
        </set>
        WHERE ID = <#noparse>#{item.id,jdbcType=BIGINT}</#noparse>
    </foreach> 
  </update>
  
  <!-- 删除 -->
  <update id="delete" parameterType="java.lang.Long" >
    update ${tableName} set IS_DELETE = 'Y'
    where ID = <#noparse>#{id,jdbcType=BIGINT}</#noparse>
  </update>
  
  <!-- 批量删除 -->
  <update id="deleteBatch" parameterType="java.util.List" >
    <foreach collection="list" item="item" index="index" open="" close="" separator=";">  
        update ${tableName}
        set IS_DELETE = 'Y'
        where ID = <#noparse>#{item,jdbcType=BIGINT}</#noparse>
    </foreach> 
  </update>
  
  <!-- 查询所有 -->
  <select id="queryList" resultMap="BaseResultMap" parameterType="${entityType}">
    SELECT
    <include refid="Base_Column_List" />
    FROM ${tableName}
    WHERE IS_DELETE = 'N'
${findListConditon}
  </select>
  
  <!-- 分页查询 -->
  <select id="find" resultMap="BaseResultMap" parameterType="com.winit.common.query.Searchable">
    SELECT
    <include refid="Base_Column_List" />
    FROM ${tableName}
    WHERE IS_DELETE = 'N'
  </select>
  
  <!-- 单个查询 -->
  <select id="get" parameterType="java.lang.Long" resultMap="BaseResultMap">
    SELECT
    <include refid="Base_Column_List" />
    FROM ${tableName} 
    WHERE IS_DELETE = 'N' AND IS_ACTIVE='Y' 
      AND id = #{id, jdbcType=BIGINT}
  </select>
</mapper>