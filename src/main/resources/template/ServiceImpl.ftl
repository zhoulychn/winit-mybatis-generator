package ${packageStr};

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ${managerType};
import ${serviceType};
import ${getCommandType};
import ${listCommandType};
import ${batchCommandType};
import ${commandType};
import ${queryCommand};
import ${voType};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.winit.pms.spi.v2.common.PmsConstants;

import com.winit.common.query.Page;
import com.winit.common.spi.SPIException;
import com.winit.pms.spi.v2.common.PageVo;

/**
 * 
 * ${entityDesc}service服务类
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author}    1.0  ${time} Created
 *
 * </pre>
 * @since 1.
 */
@Service("${lowerEntityName}Service")
public class ${entityName}ServiceImpl implements ${entityName}Service {

    private Logger        logger = LoggerFactory.getLogger(${entityName}ServiceImpl.class);

    @Resource
    private ${entityName}Manager ${lowerEntityName}Manager;

    @Override
    public ${entityName}Vo get${entityName}(Get${entityName}Command command) throws SPIException {
        logger.info("单个查询：{}", command);
        Long id = command.getId();
        
        if (id == null) {
            logger.info("id不能为空");
            throw new SPIException("");
        }
        
        ${entityName}Vo vo = new ${entityName}Vo();
        vo.setId(id);
        ${entityName}Vo ${lowerEntityName}Vo = ${lowerEntityName}Manager.get${entityName}(vo);
        return ${lowerEntityName}Vo;
    }

    @Override
    public Long create${entityName}(${entityName}Command command) throws SPIException {
        logger.info("单个新增：{}", command);
        ${entityName}Vo vo = command.getVo();
        
        if (vo == null) {
            logger.info("vo不能为空");
            throw new SPIException("");
        }
        
        vo.setCreated(new Date());
        vo.setCreatedby(command.getCtx().getUsername());
        vo.setOrganizationId(command.getCtx().getOrgId());
        vo.setIsActive(PmsConstants.ACTIVE_Y);
        vo.setIsDelete(PmsConstants.DELETE_N);
        return ${lowerEntityName}Manager.create${entityName}(vo);

    }

    @Override
    public boolean createBatch${entityName}(${entityName}BatchCommand command) throws SPIException {
        logger.info("批量新增：{}", command);
        List<${entityName}Vo> vos = command.getVos();
        
        if (vos == null || vos.size() == 0) {
            logger.info("vos不能为空");
            throw new SPIException("");
        }
        
        for (${entityName}Vo vo : vos) {
            vo.setCreated(new Date());
            vo.setCreatedby(command.getCtx().getUsername());
            vo.setOrganizationId(command.getCtx().getOrgId());
            vo.setIsActive(PmsConstants.ACTIVE_Y);
            vo.setIsDelete(PmsConstants.DELETE_N);
        }
        long count = ${lowerEntityName}Manager.createBatch${entityName}(vos);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete${entityName}(Get${entityName}Command command) throws SPIException {
        logger.info("单个删除：{}", command);
        Long id = command.getId();
        
        if (id == null) {
            logger.info("id不能为空");
            throw new SPIException("");
        }
        
        ${entityName}Vo vo = new ${entityName}Vo();
        vo.setId(id);
        vo.setUpdated(new Date());
        vo.setUpdatedby(command.getCtx().getUsername());
        long count = ${lowerEntityName}Manager.delete${entityName}(vo);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteBatch${entityName}(List${entityName}Command command) throws SPIException {
        logger.info("批量删除：{}", command);
        List<Long> ids = command.getIds();
        
        if (ids == null || ids.size() == 0) {
            logger.info("ids不能为空");
            throw new SPIException("");
        }
        
        ${entityName}Vo vo = null;
        List<${entityName}Vo> vos = new ArrayList<${entityName}Vo>();
        for (Long id : ids) {
            vo = new ${entityName}Vo();
            vo.setId(id);
            vo.setUpdated(new Date());
            vo.setUpdatedby(command.getCtx().getUsername());
            vos.add(vo);
        }
        long count = ${lowerEntityName}Manager.deleteBatch${entityName}(vos);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update${entityName}(${entityName}Command command) throws SPIException {
        logger.info("单个更新：{}", command);
        ${entityName}Vo vo = command.getVo();
        
        if (vo == null) {
            logger.info("vo不能为空");
            throw new SPIException("");
        }
        
        vo.setUpdated(new Date());
        vo.setUpdatedby(command.getCtx().getUsername());
        long count = ${lowerEntityName}Manager.update${entityName}(vo);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateBatch${entityName}(${entityName}BatchCommand command) throws SPIException {
        logger.info("批量更新：{}", command);
        List<${entityName}Vo> vos = command.getVos();
        
        if (vos == null || vos.size() == 0) {
            logger.info("vos不能为空");
            throw new SPIException("");
        }
        
        for (${entityName}Vo vo : vos) {
            vo.setUpdated(new Date());
            vo.setUpdatedby(command.getCtx().getUsername());
        }
        long count = ${lowerEntityName}Manager.updateBatch${entityName}(vos);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<${entityName}Vo> query${entityName}Page(${entityName}QueryCommand command) throws SPIException {
        logger.info("分页查询：{}", command);
        ${entityName}Vo vo = command.getVo();
        PageVo pageVo = command.getPageVo();
        return ${lowerEntityName}Manager.find${entityName}(pageVo, vo);
    }

    @Override
    public List<${entityName}Vo> query${entityName}List(${entityName}QueryCommand command) throws SPIException {
        logger.info("查询所有：{}", command);
        ${entityName}Vo vo = command.getVo();
        return ${lowerEntityName}Manager.list${entityName}(vo);
    }

}
