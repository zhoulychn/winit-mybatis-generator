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
* Created by ${author} on ${time}.
* 工具类：根据属性文件替换页面上的中文为国际化key
* ${entityDesc}Dao
*/

@MyBatisRepo
public interface ${className} extends MyBatisPageRepo<${entityClassName}, Serializable> {

    ${entityClassName} selectByPrimaryKey(${entityClassName} entity);

    List<${entityClassName}> selectListByExample(${entityClassName} entity);

    PageBase<${entityClassName}> selectPageByExample(Searchable searchable);

    long insertSelective(${entityClassName} entity);
    
    Long insertSelectiveBatch(@Param("list")List<${entityClassName}> list);
    
    long updateByPrimaryKey(${entityClassName} entity);
    
    long updateByPrimaryKeyBatch(@Param("list")List<${entityClassName}> list);
    
    long deleteByPrimaryKey(${entityClassName} entity);
    
    long deleteByPrimaryKeyBatch(@Param("list")List<${entityClassName}> list);
}
