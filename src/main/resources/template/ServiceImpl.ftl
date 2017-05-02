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
    public ${entityName}Vo get(Get${entityName}Command command) throws SPIException {
        logger.info("单个查询：{}", command);
        Long id = command.getId();
        ${entityName}Vo vo = new ${entityName}Vo();
        vo.setId(id);
        ${entityName}Vo ${lowerEntityName}Vo = ${lowerEntityName}Manager.get(vo);
        return ${lowerEntityName}Vo;
    }

    @Override
    public Long add(${entityName}Command command) throws SPIException {
        logger.info("单个新增：{}", command);
        ${entityName}Vo vo = command.getVo();
        vo.setCreated(new Date());
        vo.setCreatedby(command.getCtx().getUsername());
        vo.setOrganizationId(command.getCtx().getOrgId());
        vo.setIsActive(PmsConstants.ACTIVE_Y);
        vo.setIsDelete(PmsConstants.DELETE_N);
        return ${lowerEntityName}Manager.add(vo);

    }

    @Override
    public boolean addBatch(${entityName}BatchCommand command) throws SPIException {
        logger.info("批量新增：{}", command);
        List<${entityName}Vo> vos = command.getVos();
        for (${entityName}Vo vo : vos) {
            vo.setCreated(new Date());
            vo.setCreatedby(command.getCtx().getUsername());
            vo.setOrganizationId(command.getCtx().getOrgId());
            vo.setIsActive(PmsConstants.ACTIVE_Y);
            vo.setIsDelete(PmsConstants.DELETE_N);
        }
        long count = ${lowerEntityName}Manager.addBatch(vos);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Get${entityName}Command command) throws SPIException {
        logger.info("单个删除：{}", command);
        Long id = command.getId();
        ${entityName}Vo vo = new ${entityName}Vo();
        vo.setId(id);
        vo.setUpdated(new Date());
        vo.setUpdatedby(command.getCtx().getUsername());
        long count = ${lowerEntityName}Manager.delete(vo);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteBatch(List${entityName}Command command) throws SPIException {
        logger.info("批量删除：{}", command);
        List<Long> ids = command.getIds();
        ${entityName}Vo vo = null;
        List<${entityName}Vo> vos = new ArrayList<${entityName}Vo>();
        for (Long id : ids) {
            vo = new ${entityName}Vo();
            vo.setId(id);
            vo.setUpdated(new Date());
            vo.setUpdatedby(command.getCtx().getUsername());
            vos.add(vo);
        }
        long count = ${lowerEntityName}Manager.deleteBatch(vos);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(${entityName}Command command) throws SPIException {
        logger.info("单个更新：{}", command);
        ${entityName}Vo vo = command.getVo();
        vo.setUpdated(new Date());
        vo.setUpdatedby(command.getCtx().getUsername());
        long count = ${lowerEntityName}Manager.update(vo);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateBatch(${entityName}BatchCommand command) throws SPIException {
        logger.info("批量更新：{}", command);
        List<${entityName}Vo> vos = command.getVos();
        for (${entityName}Vo vo : vos) {
            vo.setUpdated(new Date());
            vo.setUpdatedby(command.getCtx().getUsername());
        }
        long count = ${lowerEntityName}Manager.updateBatch(vos);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<${entityName}Vo> queryPage(${entityName}QueryCommand command) throws SPIException {
        logger.info("分页查询：{}", command);
        ${entityName}Vo vo = command.getVo();
        PageVo pageVo = command.getPageVo();
        return ${lowerEntityName}Manager.find(pageVo, vo);
    }

    @Override
    public List<${entityName}Vo> queryList(${entityName}QueryCommand command) throws SPIException {
        logger.info("查询所有：{}", command);
        ${entityName}Vo vo = command.getVo();
        return ${lowerEntityName}Manager.list(vo);
    }

}
