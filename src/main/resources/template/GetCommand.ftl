package ${packageStr};

import java.io.Serializable;

import com.winit.common.spi.command.SPICommand;

/**
 * 
 * 单个查询command
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author}    1.0         ${time}     Created
 *
 * </pre>
 * @since 1.
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
