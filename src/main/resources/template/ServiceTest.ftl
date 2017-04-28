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

    @Test
    @Rollback(false)
    public void testAdd() {
        ${entityName}Command command = new ${entityName}Command();
        CommandContext context = new CommandContext();
        context.setUsername("system");
        context.setOrgId(10000L);
        command.setCtx(context);
        ${entityName}Vo vo = new ${entityName}Vo();
        command.setVo(vo);
        Long id = ${lowerEntityName}Service.add(command);
        System.out.println("id:" + id);
    }

    @Test
    @Rollback(false)
    public void testAddBatch() {
        ${entityName}BatchCommand command = new ${entityName}BatchCommand();
        CommandContext context = new CommandContext();
        context.setUsername("system");
        context.setOrgId(10000L);
        command.setCtx(context);
        ${entityName}Vo v1 = new ${entityName}Vo();
        ${entityName}Vo v2 = new ${entityName}Vo();
        List<${entityName}Vo> list = new ArrayList<${entityName}Vo>();
        list.add(v1);
        list.add(v2);
        command.setVos(list);

        boolean isSuccess = ${lowerEntityName}Service.addBatch(command);
        System.out.println("isSuccess:" + isSuccess);
    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Get${entityName}Command command = new Get${entityName}Command();
        CommandContext context = new CommandContext();
        context.setUsername("system");
        command.setCtx(context);
        command.setId(101L);
        boolean isSuccess = ${lowerEntityName}Service.delete(command);
        System.out.println("是否成功：" + isSuccess);
    }

    @Test
    @Rollback(false)
    public void testDeleteBatch() {
        List${entityName}Command command = new List${entityName}Command();
        CommandContext context = new CommandContext();
        context.setUsername("system");
        command.setCtx(context);

        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        command.setIds(ids);
        boolean isSuccess = ${lowerEntityName}Service.deleteBatch(command);
        System.out.println("是否成功：" + isSuccess);
    }

    @Test
    @Rollback(false)
    public void testUpdate() {
        ${entityName}Command command = new ${entityName}Command();
        CommandContext context = new CommandContext();
        context.setUsername("system");
        command.setCtx(context);
        ${entityName}Vo vo = new ${entityName}Vo();
        vo.setId(1L);
        command.setVo(vo);
        boolean isSuccess = ${lowerEntityName}Service.update(command);
        System.out.println("是否成功：" + isSuccess);
    }

    @Test
    @Rollback(false)
    public void testUpdateBatch() {
        ${entityName}BatchCommand command = new ${entityName}BatchCommand();
        CommandContext context = new CommandContext();
        context.setUsername("system");
        command.setCtx(context);
        ${entityName}Vo v1 = new ${entityName}Vo();
        v1.setId(1L);
        ${entityName}Vo v2 = new ${entityName}Vo();
        v2.setId(2L);
        List<${entityName}Vo> list = new ArrayList<${entityName}Vo>();
        list.add(v1);
        list.add(v2);
        command.setVos(list);

        boolean isSuccess = ${lowerEntityName}Service.updateBatch(command);
        System.out.println("isSuccess:" + isSuccess);
    }

    @Test
    public void testGet() {
        Get${entityName}Command command = new Get${entityName}Command();
        command.setId(1L);
        ${entityName}Vo vo = ${lowerEntityName}Service.get(command);
        System.out.println("vo：" + vo);
    }

    @Test
    public void testQueryPage() {
        ${entityName}QueryCommand command = new ${entityName}QueryCommand();
        PageVo pageVo = new PageVo();
        pageVo.setFieldName("id");
        pageVo.setPageNo(1);
        pageVo.setPageSize(8);
        pageVo.setDirection(Sort.Direction.DESC.name());
        command.setPageVo(pageVo);
        ${entityName}Vo vo = new ${entityName}Vo();
        command.setVo(vo);

        Page<${entityName}Vo> page = ${lowerEntityName}Service.queryPage(command);
        System.out.println("page:" + page);
        if (page == null) return;
        for (${entityName}Vo v : page.getContent()) {
            System.out.println("vo:" + v);
        }
    }

    @Test
    public void testQueryList() {
        ${entityName}QueryCommand command = new ${entityName}QueryCommand();
        ${entityName}Vo vo = new ${entityName}Vo();
        command.setVo(vo);

        List<${entityName}Vo> list = ${lowerEntityName}Service.queryList(command);
        for (${entityName}Vo v : list) {
            System.out.println("vo:" + v);
        }
    }

}
