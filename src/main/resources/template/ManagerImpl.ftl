package ${packageStr};

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import ${daoType};
import ${entityType};
import ${managerType};
import ${voType};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.winit.common.orm.mybatis.PageBase;
import com.winit.common.query.Page;
import com.winit.common.query.Searchable;
import com.winit.pms.spi.v2.common.PageVo;
import com.winit.pms.utils.SearchableUtil;
import com.winit.common.query.Sort.Direction;

/**
 * ${entityDesc}managerImpl实现类
 * 
 * @version
 * 
 * <pre>
 * Author	Version		Date		Changes
 * ${author}    1.0  ${time} Created
 * </pre>
 * 
 * @since 1.
 */
@Component("${annotationName}")
public class ${className} implements ${managerClassName} {

    private Logger    logger = LoggerFactory.getLogger(${className}.class);

    @Resource
    private ${daoClassName} ${daoVar};

    @Override
    public ${voClassName} get${entityName}(Long id) {
        logger.info("单个查询：{}", id);
        ${entityClassName} entity = ${daoVar}.get(id);
        ${voClassName} newVo = null;
        if (entity != null) {
            newVo = new ${voClassName}();
            this.copyEntityToVo(entity, newVo);
        }
        return newVo;
    }

    @Override
    public Long create${entityName}(${voClassName} vo) {
        logger.info("新增：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        this.copyVoToEntity(vo, entity);

        // TODO: 是否需要校验已存在
        ${daoVar}.add(entity);
        return entity.getId();
    }

    @Override
    public void createBatch${entityName}(List<${voClassName}> vos) {
        logger.info("批量新增：{}", vos);
        List<${entityClassName}> list = new ArrayList<${entityClassName}>();
        for (${entityName}Vo vo : vos) {
            ${entityName}Entity entity = new ${entityName}Entity();
            this.copyVoToEntity(vo, entity);
            list.add(entity);
        }
        ${daoVar}.addBatch(list);
    }

    @Override
    public void delete${entityName}(Long id) {
        logger.info("删除：{}", id);
        ${daoVar}.delete(id);
    }

    @Override
    public void deleteBatch${entityName}(List<Long> ids) {
        logger.info("批量删除：{}", ids);
        ${daoVar}.deleteBatch(ids);
    }

    @Override
    public void update${entityName}(${voClassName} vo) {
        logger.info("更新：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        this.copyVoToEntity(vo, entity);
        return ${daoVar}.updateSingle(entity);
    }

    @Override
    public long updateBatch${entityName}(List<${voClassName}> vos) {
        logger.info("批量更新：{}", vos);
        List<${entityClassName}> list = new ArrayList<${entityClassName}>();
        for (${entityName}Vo vo : vos) {
            ${entityName}Entity entity = new ${entityName}Entity();
            this.copyVoToEntity(vo, entity);
            list.add(entity);
        }
        ${daoVar}.updateBatch(list);
    }

    @Override
    public Page<${voClassName}> find${entityName}Page(PageVo pageVo, ${voClassName} vo) {
        logger.info("分页查询：{}, vo:{}", pageVo, vo);
        Searchable<${entityClassName}> searchable = this.buildSearchable(pageVo, vo);
        PageBase<${entityClassName}> page = ${daoVar}.findPage(searchable);
        List<${entityName}Vo> vos = new ArrayList<${entityName}Vo>();
        for (${entityName}Entity entity : page) {
            ${entityName}Vo newVo = new ${entityName}Vo();
            this.copyEntityToVo(entity, newVo);
            vos.add(newVo);
        }
        return new Page<${entityName}Vo>(vos, page.getPageable(), page.getTotalElements());
    }

    @Override
    public List<${voClassName}> query${entityName}List(${voClassName} vo) {
        logger.info("查询所有：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        this.copyVoToEntity(vo, entity);
        List<${entityClassName}> list = ${daoVar}.findList(entity);;
        List<${voClassName}> listVo = new ArrayList<${voClassName}>();
        for (${entityName}Entity newEntity : list) {
            ${entityName}Vo newVo = new ${entityName}Vo();
            this.copyEntityToVo(newEntity, newVo);
            listVo.add(newVo);
        }
        return listVo;
    }

    @SuppressWarnings("unchecked")
    private Searchable<${entityClassName}> buildSearchable(PageVo pageVo, ${voClassName} vo) {
        Searchable<${entityClassName}> searchable = SearchableUtil.getSearchable(pageVo);
        
        if (searchable.getSort() == null) {
            searchable.addSort(Direction.DESC, "CREATED");
        }
        
        if (vo == null) {
            return searchable;
        }
        
        // TODO:添加条件 searchable.addSearchFilter("USERNAME", SearchOperator.like,
        // "%" + vo.getUsername() + "%");
        
        return searchable;
    }
    
    /**
     * 将实体属性拷贝到vo
     */
    private void copyEntityToVo(${entityName}Entity entity, ${entityName}Vo vo) {
        if (entity == null) return;
${entityToVos}
    }
    
    /**
     * 将vo属性拷贝到实体
     */
    private void copyVoToEntity(${entityName}Vo vo, ${entityName}Entity entity) {
        if (vo == null) return;
${voToEntitys}
    }
}
