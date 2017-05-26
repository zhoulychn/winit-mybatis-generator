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
import com.winit.pms.spi.v2.common.PmsExceptionCode;
import com.winit.pms.utils.BeanUtils;
import com.winit.pms.utils.SearchableUtil;
import com.winit.common.query.Sort.Direction;

/**
* Created by ${author} on ${time}.
* ${entityDesc}管理
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
        try {
            entity = ${daoVar}.get(entity);
        } catch (RuntimeException e) {
            logger.error("单个查询异常", e);
            e.printStackTrace();
            throw new SPIException(PmsExceptionCode.SYS_01001200000);
        }
        ${voClassName} newVo = null;
        if (entity != null) {
            newVo = new ${voClassName}();
            BeanUtils.copyProperties(entity, newVo);
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
        
        try {
            ${daoVar}.insertSingle(entity);
        } catch (RuntimeException e) {
            logger.error("新增异常", e);
            e.printStackTrace();
            throw new SPIException(PmsExceptionCode.SYS_01001200000);
        }
        return entity.getId();
    }

    @Override
    public long addBatch(List<${voClassName}> vos) throws SPIException {
        logger.info("批量新增：{}", vos);
        List<${entityClassName}> list = new ArrayList<${entityClassName}>();
        if (vos != null) {
            list = BeanUtils.copyList(vos, ${entityClassName}.class);
        }
        long count = 0L;
        try {
            count = ${daoVar}.insertBatch(list);
        } catch (RuntimeException e) {
            logger.error("批量新增异常", e);
            e.printStackTrace();
            throw new SPIException(PmsExceptionCode.SYS_01001200000);
        }
        return count;
    }

    @Override
    public long delete(${voClassName} vo) throws SPIException {
        logger.info("删除：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        if (vo != null) {
            BeanUtils.copyProperties(vo, entity);
        }
        long count = 0L;
        try {
            count = ${daoVar}.deleteSingle(entity);
        } catch (RuntimeException e) {
            logger.error("删除异常", e);
            e.printStackTrace();
            throw new SPIException(PmsExceptionCode.SYS_01001200000);
        }
        return count;
    }

    @Override
    public long deleteBatch(List<${voClassName}> vos) throws SPIException {
        logger.info("批量删除：{}", vos);
        List<${entityClassName}> list = new ArrayList<${entityClassName}>();
        if (vos != null) {
            list = BeanUtils.copyList(vos, ${entityClassName}.class);
        }
        long count = 0L;
        try {
            count = ${daoVar}.deleteBatch(list);
        } catch (RuntimeException e) {
            logger.error("批量删除异常", e);
            e.printStackTrace();
            throw new SPIException(PmsExceptionCode.SYS_01001200000);
        }
        return count;
    }

    @Override
    public long update(${voClassName} vo) throws SPIException {
        logger.info("更新：{}", vo);
        ${entityClassName} entity = new ${entityClassName}();
        if (vo != null) {
            BeanUtils.copyProperties(vo, entity);
        }
        long count = 0L;
        try {
            count = ${daoVar}.updateSingle(entity);
        } catch (RuntimeException e) {
            logger.error("更新异常", e);
            e.printStackTrace();
            throw new SPIException(PmsExceptionCode.SYS_01001200000);
        }
        return count;
    }

    @Override
    public long updateBatch(List<${voClassName}> vos) throws SPIException {
        logger.info("批量更新：{}", vos);
        List<${entityClassName}> list = new ArrayList<${entityClassName}>();
        if (vos != null) {
            list = BeanUtils.copyList(vos, ${entityClassName}.class);
        }
        long count = 0L;
        try {
            count = ${daoVar}.updateBatch(list);
        } catch (RuntimeException e) {
            logger.error("批量更新异常", e);
            e.printStackTrace();
            throw new SPIException(PmsExceptionCode.SYS_01001200000);
        }
        return count;
    }

    @Override
    public Page<${voClassName}> find(PageVo pageVo, ${voClassName} vo) throws SPIException {
        logger.info("分页查询：{}, vo:{}", pageVo, vo);
        Searchable<${entityClassName}> searchable = this.buildSearchable(pageVo, vo);
        PageBase<${entityClassName}> page = null;
        try {
            page = ${daoVar}.findPage(searchable);
        } catch (RuntimeException e) {
            logger.error("分页查询异常", e);
            e.printStackTrace();
            throw new SPIException(PmsExceptionCode.SYS_01001200000);
        }
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
        List<${entityClassName}> list = null;
        try {
            list = ${daoVar}.findList(entity);
        } catch (RuntimeException e) {
            logger.error("查询所有异常", e);
            e.printStackTrace();
            throw new SPIException(PmsExceptionCode.SYS_01001200000);
        }
        List<${voClassName}> listVo = BeanUtils.copyList(list, ${voClassName}.class);
        return listVo;
    }

    @SuppressWarnings("unchecked")
    private Searchable<${entityClassName}> buildSearchable(PageVo pageVo, ${voClassName} vo) {
        Searchable<${entityClassName}> searchable = SearchableUtil.getSearchable(pageVo);

        // TODO:添加条件 searchable.addSearchFilter("USERNAME", SearchOperator.like,
        // "%" + vo.getUsername() + "%");
        
        if (searchable.getSort() == null) {
            searchable.addSort(Direction.DESC, "CREATED");
        }
        
        return searchable;
    }
}
