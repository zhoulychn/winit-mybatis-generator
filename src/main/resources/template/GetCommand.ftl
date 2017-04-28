package ${packageStr};

import java.io.Serializable;

import com.winit.common.spi.command.SPICommand;

/**
* Created by ${author} on ${time}.
* 单个查询command
*/

public class Get${entityName}Command extends SPICommand implements Serializable {

    private static final long serialVersionUID = ${serialVersionNum};
    private Long              id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
