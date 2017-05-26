package ${packageStr};
import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
${importStr}

/**
* Created by ${author} on ${time}.
* ${entityDesc}实体
*/

public class ${className} implements Serializable {

    private static final long serialVersionUID = ${serialVersionNum};
    
${propertiesStr}
${methodStr}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
