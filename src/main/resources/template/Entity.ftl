package ${packageStr};
import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
${importStr}

/**
* Created by ${author} on ${time}.
* 工具类：根据属性文件替换页面上的中文为国际化key
* ${entityDesc}实体
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