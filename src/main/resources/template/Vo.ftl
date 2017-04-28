package ${packageStr};
import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
${importStr}

/**
* Created by ${author} on ${time}.
* ${entityDesc}
*/

public class ${className} implements Serializable {
    private static final long serialVersionUID = ${serialVersionNum};
    
${propertiesStr}
${methodStr}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
