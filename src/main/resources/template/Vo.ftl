package ${packageStr};
import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
${importStr}

/**
 * 
 * ${entityDesc}vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author} 	1.0  ${time} Created
 *
 * </pre>
 * @since 1.
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