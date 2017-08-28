package ${packageStr};

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import ${serviceType};
import ${getCommandType};
import ${listCommandType};
import ${batchCommandType};
import ${commandType};
import ${queryCommand};
import ${voType};
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.winit.common.query.Page;
import com.winit.common.query.Sort;
import com.winit.common.spi.context.CommandContext;
import com.winit.pms.spi.v2.common.PageVo;

/**
 * ${entityDesc}接口测试类
 * 
 * @version
 * 
 * <pre>
 * Author	Version		Date		Changes
 * ${author}    1.0  ${time} Created
 * </pre>
 * 
 * @since 1.
 */
@DirtiesContext
@ActiveProfiles("test")
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ${entityName}ServiceTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private ${entityName}Service ${lowerEntityName}Service;
    
    /**
     * 虚拟上下文
     * 
     * @return
     */
    private CommandContext createContext() {
        CommandContext context = new CommandContext();
        context.setUsername("system");
        context.setOrgId(10000L);
        context.setLanguage("en_US");
        CommandContext.setContext(context);
        return context;
    }

    @Test
    @Rollback(false)
    public void testAdd() {
        ${entityName}Command command = new ${entityName}Command();
        command.setCtx(this.createContext());
        ${entityName}Vo vo = new ${entityName}Vo();
        command.setVo(vo);
        Long id = ${lowerEntityName}Service.create${entityName}(command);
        System.out.println("id:" + id);
    }

    @Test
    @Rollback(false)
    public void testAddBatch() {
        ${entityName}BatchCommand command = new ${entityName}BatchCommand();
        command.setCtx(this.createContext());
        ${entityName}Vo v1 = new ${entityName}Vo();
        ${entityName}Vo v2 = new ${entityName}Vo();
        List<${entityName}Vo> list = new ArrayList<${entityName}Vo>();
        list.add(v1);
        list.add(v2);
        command.setVos(list);

        ${lowerEntityName}Service.createBatch${entityName}(command);
    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Get${entityName}Command command = new Get${entityName}Command();
        command.setCtx(this.createContext());
        command.setId(101L);
        ${lowerEntityName}Service.delete${entityName}(command);
    }

    @Test
    @Rollback(false)
    public void testDeleteBatch() {
        List${entityName}Command command = new List${entityName}Command();
        command.setCtx(this.createContext());

        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        command.setIds(ids);
        ${lowerEntityName}Service.deleteBatch${entityName}(command);
    }

    @Test
    @Rollback(false)
    public void testUpdate() {
        ${entityName}Command command = new ${entityName}Command();
        command.setCtx(this.createContext());
        ${entityName}Vo vo = new ${entityName}Vo();
        vo.setId(1L);
        command.setVo(vo);
        ${lowerEntityName}Service.update${entityName}(command);
    }

    @Test
    @Rollback(false)
    public void testUpdateBatch() {
        ${entityName}BatchCommand command = new ${entityName}BatchCommand();
        command.setCtx(this.createContext());
        ${entityName}Vo v1 = new ${entityName}Vo();
        v1.setId(1L);
        ${entityName}Vo v2 = new ${entityName}Vo();
        v2.setId(2L);
        List<${entityName}Vo> list = new ArrayList<${entityName}Vo>();
        list.add(v1);
        list.add(v2);
        command.setVos(list);

        ${lowerEntityName}Service.updateBatch${entityName}(command);
    }

    @Test
    public void testGet() {
        Get${entityName}Command command = new Get${entityName}Command();
        command.setCtx(this.createContext());
        command.setId(1L);
        ${entityName}Vo vo = ${lowerEntityName}Service.get${entityName}(command);
        System.out.println("vo：" + vo);
    }

    @Test
    public void testQueryPage() {
        ${entityName}QueryCommand command = new ${entityName}QueryCommand();
        command.setCtx(this.createContext());
        PageVo pageVo = new PageVo();
        pageVo.setFieldName("id");
        pageVo.setPageNo(1);
        pageVo.setPageSize(8);
        pageVo.setDirection(Sort.Direction.DESC.name());
        command.setPageVo(pageVo);
        ${entityName}Vo vo = new ${entityName}Vo();
        command.setVo(vo);

        Page<${entityName}Vo> page = ${lowerEntityName}Service.query${entityName}Page(command);
        System.out.println("page:" + page);
        if (page == null) return;
        for (${entityName}Vo v : page.getContent()) {
            System.out.println("vo:" + v);
        }
    }

    @Test
    public void testQueryList() {
        ${entityName}QueryCommand command = new ${entityName}QueryCommand();
        command.setCtx(this.createContext());
        ${entityName}Vo vo = new ${entityName}Vo();
        command.setVo(vo);

        List<${entityName}Vo> list = ${lowerEntityName}Service.query${entityName}List(command);
        for (${entityName}Vo v : list) {
            System.out.println("vo:" + v);
        }
    }

}
