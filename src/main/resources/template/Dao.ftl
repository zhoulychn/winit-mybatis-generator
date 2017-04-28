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

    long insertSelective(${entityClassName} entity);
    
    Long insertSelectiveBatch(@Param("list")List<${entityClassName}> list);
    
    long updateByPrimaryKey(${entityClassName} entity);
    
    long updateByPrimaryKeyBatch(@Param("list")List<${entityClassName}> list);
    
    long deleteByPrimaryKey(${entityClassName} entity);
    
    long deleteByPrimaryKeyBatch(@Param("list")List<${entityClassName}> list);

    ${entityClassName} selectByPrimaryKey(${entityClassName} entity);

    List<${entityClassName}> selectListByExample(Searchable searchable);

    PageBase<${entityClassName}> selectPageByExample(Searchable searchable);
}
