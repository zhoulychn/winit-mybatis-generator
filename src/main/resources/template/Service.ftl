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
    Long create${entityName}(${entityName}Command command) throws SPIException;

    /**
     * 批量新增
     * 
     * @param command
     * @return
     */
    boolean createBatch${entityName}(${entityName}BatchCommand command) throws SPIException;

    /**
     * 单个删除
     * 
     * @param command
     * @return
     */
    boolean delete${entityName}(Get${entityName}Command command) throws SPIException;

    /**
     * 批量删除
     * 
     * @param command
     * @return
     */
    boolean deleteBatch${entityName}(List${entityName}Command command) throws SPIException;

    /**
     * 单个更新
     * 
     * @param command
     * @return
     */
    boolean update${entityName}(${entityName}Command command) throws SPIException;

    /**
     * 批量更新
     * 
     * @param command
     * @return
     */
    boolean updateBatch${entityName}(${entityName}BatchCommand command) throws SPIException;

    /**
     * 单个查询
     * 
     * @param command
     * @return
     */
    ${voClassName} get${entityName}(Get${entityName}Command command) throws SPIException;

    /**
     * 分页查询
     * 
     * @param command
     * @return
     */
    Page<${voClassName}> query${entityName}Page(${entityName}QueryCommand command) throws SPIException;

    /**
     * 查询所有
     * 
     * @param command
     * @return
     */
    List<${voClassName}> query${entityName}List(${entityName}QueryCommand command) throws SPIException;
}
