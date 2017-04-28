package ${packageStr};

import java.io.Serializable;

import ${voType};

import com.winit.common.spi.command.SPICommand;
import com.winit.pms.spi.v2.common.PageVo;

/**
 * 
 * 查询所有、分页查询command
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author}    1.0         ${time}     Created
 *
 * </pre>
 * @since 1.
 */
public class ${entityName}QueryCommand extends SPICommand implements Serializable {

    private static final long serialVersionUID = ${serialVersionNum};

    private PageVo            pageVo;

    private ${voClassName}          vo;

    public PageVo getPageVo() {
        return pageVo;
    }

    public void setPageVo(PageVo pageVo) {
        this.pageVo = pageVo;
    }

    public ${voClassName} getVo() {
        return vo;
    }

    public void setVo(${voClassName} vo) {
        this.vo = vo;
    }
}
