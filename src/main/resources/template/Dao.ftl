package ${packageStr};

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.winit.common.orm.mybatis.MyBatisPageRepo;
import com.winit.common.orm.mybatis.MyBatisRepo;
import com.winit.common.orm.mybatis.PageBase;
import com.winit.common.query.Searchable;
${importStr}

/**
 * 
 * ${entityDesc}Dao
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author} 	1.0  		${time} 	Created
 *
 * </pre>
 * @since 1.
 */
@MyBatisRepo
public interface ${className} extends MyBatisPageRepo<${entityClassName}, Serializable> {
    /**
     * 单个新增
     * 
     * @param entity
     * @return
     */
    long insertSingle(${entityClassName} entity);
    
    /**
     * 批量新增
     * 
     * @param list
     * @return
     */
    Long insertBatch(@Param("list")List<${entityClassName}> list);
    
    /**
     * 单个更新
     * 
     * @param entity
     * @return
     */
    long updateSingle(${entityClassName} entity);
    
    /**
     * 批量更新
     * 
     * @param list
     * @return
     */
    long updateBatch(@Param("list")List<${entityClassName}> list);
    
    /**
     * 单个删除
     * 
     * @param entity
     * @return
     */
    long deleteSingle(${entityClassName} entity);
    
    /**
     * 批量删除
     * 
     * @param list
     * @return
     */
    long deleteBatch(@Param("list")List<${entityClassName}> list);
    
    /**
     * 分页查询
     * 
     * @param searchable
     * @return
     */
    PageBase<${entityClassName}> findPage(Searchable<${entityClassName}> searchable);
    
    /**
     * 查询列表
     * 
     * @param entity
     * @return
     */
    List<${entityClassName}> findList(${entityClassName} entity);
    
    /**
     * 根据id查询
     * 
     * @param entity
     * @return
     */
    ${entityClassName} get(${entityClassName} entity);
}