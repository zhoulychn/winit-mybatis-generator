package ${packageStr};

import java.util.List;

import ${getCommandType};
import ${listCommandType};
import ${batchCommandType};
import ${commandType};
import ${queryCommandType};
import ${voType};

import com.winit.common.query.Page;
import com.winit.common.spi.SPIException;

/**
 * 
 * ${entityDesc}服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author}    1.0  ${time} Created
 *
 * </pre>
 * @since 1.
 */
public interface ${className} {

    /**
     * 单个新增
     * 
     * @param command
     * @return
     */
    Long add(${entityName}Command command) throws SPIException;

    /**
     * 批量新增
     * 
     * @param command
     * @return
     */
    boolean addBatch(${entityName}BatchCommand command) throws SPIException;

    /**
     * 单个删除
     * 
     * @param command
     * @return
     */
    boolean delete(Get${entityName}Command command) throws SPIException;

    /**
     * 批量删除
     * 
     * @param command
     * @return
     */
    boolean deleteBatch(List${entityName}Command command) throws SPIException;

    /**
     * 单个更新
     * 
     * @param command
     * @return
     */
    boolean update(${entityName}Command command) throws SPIException;

    /**
     * 批量更新
     * 
     * @param command
     * @return
     */
    boolean updateBatch(${entityName}BatchCommand command) throws SPIException;

    /**
     * 单个查询
     * 
     * @param command
     * @return
     */
    ${voClassName} get(Get${entityName}Command command) throws SPIException;

    /**
     * 分页查询
     * 
     * @param command
     * @return
     */
    Page<${voClassName}> queryPage(${entityName}QueryCommand command) throws SPIException;

    /**
     * 查询所有
     * 
     * @param command
     * @return
     */
    List<${voClassName}> queryList(${entityName}QueryCommand command) throws SPIException;
}
