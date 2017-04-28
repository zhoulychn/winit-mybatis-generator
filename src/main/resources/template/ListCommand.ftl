package ${packageStr};

import java.io.Serializable;
import java.util.List;

import com.winit.common.spi.command.SPICommand;

/**
 * 批量删除command
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
public class List${entityName}Command extends SPICommand implements Serializable {

    private static final long serialVersionUID = ${serialVersionNum};

    private List<Long>        ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
