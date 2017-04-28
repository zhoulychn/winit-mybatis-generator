package com.winit.generator.handler.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.MapperInfo;

public class MapperHandler extends BaseHandler<MapperInfo> {
    public MapperHandler(String ftlName, MapperInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("mapperXml.path")
                + File.separator + info.getFileName() + ".xml";
        
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void combileParams(MapperInfo info) {
      //<result column="SU_ROUTE_CODE" jdbcType="VARCHAR" property="suRouteCode" />
        this.param.put("namespace", info.getNamespace());
        this.param.put("entityType", info.getEntityInfo().getPackageClassName());
        this.param.put("tableName", info.getEntityInfo().getTableName());
        this.param.put("entityName", info.getEntityInfo().getEntityName());
        
        StringBuilder resultMap = new StringBuilder();
        StringBuilder baseColumn = new StringBuilder();
        StringBuilder insertIfColumns = new StringBuilder();
        StringBuilder insertIfProps = new StringBuilder();
        StringBuilder insertBatchColumns = new StringBuilder();
        StringBuilder insertBatchProps = new StringBuilder();
        StringBuilder updateColProps = new StringBuilder();
        StringBuilder updateBatchColProps = new StringBuilder();
        StringBuilder findListConditon = new StringBuilder();

        List<String> baseColumns = (List<String>) this.context.getAttribute("baseColumns");
        
        //resultMap
        Map<String, String> propJdbcTypes = info.getEntityInfo().getPropJdbcTypes();
        for (Entry<String, String> entry : info.getEntityInfo().getPropNameColumnNames().entrySet()) {
            String propName = entry.getKey();
            String columnName = entry.getValue();
            
            if (!("id".equals(propName))) {
                resultMap.append("    <result column=\"").append(columnName).append("\" jdbcType=\"")
                .append(propJdbcTypes.get(propName)).append("\" property=\"").append(propName)
                .append("\" />\r\n");
                
                if ((!("created".equals(propName))) && !("createdby".equals(propName))) {
                    /**
                     * <if test="code != null">
                        CODE = #{code,jdbcType=VARCHAR},
                      </if>
                     */
                    updateColProps.append("      <if test=\"").append(propName).append(" != null\">\r\n        ").append(columnName).append("=#{")
                    .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},\r\n").append("      </if>\r\n");
                
                    /**
                     * <if test="item.isDelete != null">
                        IS_DELETE = #{item.isDelete,jdbcType=VARCHAR},
                      </if>
                     */
                    updateBatchColProps.append("        <if test=\"item.").append(propName).append(" != null\">\r\n          ").append(columnName).append("=#{item.")
                    .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},\r\n").append("        </if>\r\n");
                    
                    /**
                     * <if test="isDelete != null">
                        AND IS_DELETE = #{isDelete,jdbcType=VARCHAR}
                      </if>
                     */


                }

            }
            if(!columnName.equals("IS_DELETE")){
                findListConditon.append("    <if test=\"").append(propName).append(" != null\">\r\n      AND ").append(columnName).append("=#{")
                        .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("}\r\n").append("    </if>\r\n");
            }
            baseColumn.append(columnName).append(",");

            if (!("updated".equals(propName)) && !("updatedby".equals(propName))) {
                /**
                 * <if test="id != null">
                     ID,
                   </if>
                 */
                insertIfColumns.append("      <if test=\"").append(propName).append(" != null\">\r\n        ")
                    .append(columnName).append(",\r\n").append("      </if>\r\n");
                /**
                 * <if test="id != null">
                    #{id,jdbcType=BIGINT},
                  </if>
                 */
                insertIfProps.append("      <if test=\"").append(propName).append(" != null\">\r\n        #{")
                .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},\r\n").append("      </if>\r\n");
                
                insertBatchColumns.append(columnName).append(",");
                
                /**
                 * #{item.id,jdbcType=BIGINT},
                 */
                insertBatchProps.append("#{item.")
                .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},");
            }
            
        }
        this.param.put("resultMap", resultMap.toString());
        this.param.put("baseColumn", baseColumn.substring(0, baseColumn.length() - 1));
        this.param.put("insertIfColumns", insertIfColumns.toString());
        this.param.put("insertIfProps", insertIfProps.toString());
        this.param.put("insertBatchColumns", insertBatchColumns.substring(0, insertBatchColumns.length() - 1));
        this.param.put("insertBatchProps", insertBatchProps.substring(0, insertBatchProps.length() - 1));
        this.param.put("updateColProps", updateColProps.toString());
        this.param.put("updateBatchColProps", updateBatchColProps.toString());
        this.param.put("findListConditon", findListConditon.substring(0,findListConditon.length()-2));
    }

}
