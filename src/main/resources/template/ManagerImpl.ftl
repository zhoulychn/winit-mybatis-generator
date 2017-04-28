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
import com.winit.common.spi.SPIException;
import com.winit.pms.spi.v2.common.PageVo;
import com.winit.pms.utils.BeanUtils;
import com.winit.pms.utils.SearchableUtil;

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
    public ${voClassName} get(${voClassName} vo) throws SPIException {
        logger.info("单个查询：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        if (vo != null) {
            BeanUtils.copyProperties(vo, entity);
        }
        ${entityClassName} newEntity = ${daoVar}.get(entity);
        ${voClassName} newVo = new ${voClassName}();
        if (newEntity != null) {
            BeanUtils.copyProperties(newEntity, newVo);
        } else {
            return null;
        }
        return newVo;
    }

    @Override
    public Long add(${voClassName} vo) throws SPIException {
        logger.info("新增：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        if (vo != null) {
            BeanUtils.copyProperties(vo, entity);
        }

        // TODO: 是否需要校验已存在

        ${daoVar}.insertSingle(entity);
        return entity.getId();
    }

    @Override
    public long addBatch(List<${voClassName}> vos) throws SPIException {
        logger.info("批量新增：{}", vos);
        List<${entityClassName}> list = new ArrayList<${entityClassName}>();
        if (vos != null) {
            list = BeanUtils.copyList(vos, ${entityClassName}.class);
        }
        return ${daoVar}.insertBatch(list);
    }

    @Override
    public long delete(${voClassName} vo) throws SPIException {
        logger.info("删除：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        if (vo != null) {
            BeanUtils.copyProperties(vo, entity);
        }
        return ${daoVar}.deleteSingle(entity);
    }

    @Override
    public long deleteBatch(List<${voClassName}> vos) throws SPIException {
        logger.info("批量删除：{}", vos);
        List<${entityClassName}> list = new ArrayList<${entityClassName}>();
        if (vos != null) {
            list = BeanUtils.copyList(vos, ${entityClassName}.class);
        }
        return ${daoVar}.deleteBatch(list);
    }

    @Override
    public long update(${voClassName} vo) throws SPIException {
        logger.info("更新：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        if (vo != null) {
            BeanUtils.copyProperties(vo, entity);
        }
        return ${daoVar}.updateSingle(entity);
    }

    @Override
    public long updateBatch(List<${voClassName}> vos) throws SPIException {
        logger.info("批量更新：{}", vos);
        List<${entityClassName}> list = new ArrayList<${entityClassName}>();
        if (vos != null) {
            list = BeanUtils.copyList(vos, ${entityClassName}.class);
        }
        return ${daoVar}.updateBatch(list);
    }

    @Override
    public Page<${voClassName}> find(PageVo pageVo, ${voClassName} vo) throws SPIException {
        logger.info("分页查询：{}, vo:{}", pageVo, vo);
        Searchable<${entityClassName}> searchable = this.buildSearchable(pageVo, vo);
        PageBase<${entityClassName}> page = ${daoVar}.findPage(searchable);
        Page<${voClassName}> pages = BeanUtils.copyPageList(page, ${voClassName}.class);
        return pages;
    }

    @Override
    public List<${voClassName}> list(${voClassName} vo) throws SPIException {
        logger.info("查询所有：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        if (vo != null) {
            BeanUtils.copyProperties(vo, entity);
        }
        List<${entityClassName}> list = ${daoVar}.findList(entity);
        List<${voClassName}> listVo = BeanUtils.copyList(list, ${voClassName}.class);
        return listVo;
    }

    @SuppressWarnings("unchecked")
    private Searchable<${entityClassName}> buildSearchable(PageVo pageVo, ${voClassName} vo) {
        Searchable<${entityClassName}> searchable = SearchableUtil.getSearchable(pageVo);

        // TODO:添加条件 searchable.addSearchFilter("USERNAME", SearchOperator.like,
        // vo.getUsername());

        return searchable;
    }
}
