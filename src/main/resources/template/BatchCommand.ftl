package ${packageStr};

import java.io.Serializable;
import java.util.List;

import ${voType};

import com.winit.common.spi.command.SPICommand;

/**
 * 批量新增command
 * 
 * @version
 * 
 * <pre>
 * Author	Version		Date		Changes
 * ${author}    1.0         ${time}     Created
 * </pre>
 * 
 * @since 1.
 */
public class ${entityName}BatchCommand extends SPICommand implements Serializable {

    private static final long serialVersionUID = ${serialVersionNum};
    private List<${voClassName}>    vos;

    public List<${voClassName}> getVos() {
        return vos;
    }

    public void setVos(List<${voClassName}> vos) {
        this.vos = vos;
    }
}
