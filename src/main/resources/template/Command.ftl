package ${packageStr};

import java.io.Serializable;

import ${voType};

import com.winit.common.spi.command.SPICommand;

/**
* Created by ${author} on ${time}.
* 新增|更新command
*/

public class ${entityName}Command extends SPICommand implements Serializable {

    private static final long serialVersionUID = ${serialVersionNum};
    private ${voClassName}          vo;

    public ${voClassName} getVo() {
        return vo;
    }

    public void setVo(${voClassName} vo) {
        this.vo = vo;
    }
}
