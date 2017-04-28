package ${packageStr};

import java.util.List;

import ${voType};
import com.winit.common.query.Page;
import com.winit.common.spi.SPIException;
import com.winit.pms.spi.v2.common.PageVo;

/**
* Created by ${author} on ${time}.
* ${entityDesc}管理
*/

public interface ${className} {
    Long add(${voClassName} vo) throws SPIException;
    
    long addBatch(List<${voClassName}> vos) throws SPIException;
    
    long delete(${voClassName} vo) throws SPIException;
    
    long deleteBatch(List<${voClassName}> vos) throws SPIException;
    
    long update(${voClassName} vo) throws SPIException;
    
    long updateBatch(List<${voClassName}> vos) throws SPIException;
    
    ${voClassName} get(${voClassName} vo) throws SPIException;
    
    Page<${voClassName}> find(PageVo pageVo, ${voClassName} vo) throws SPIException;
    
    List<${voClassName}> list(${voClassName} vo) throws SPIException;
}
