package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fast.coding.extension.pagination.LayPage;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    /**
    * 分页查询列表
    * @param ${lowerEntityName}
    * @return
    */
    @Override
    public LayPage selectPageList(${entity} ${lowerEntityName}, int limit, int page)  {
        Page pageContext = new Page(page,limit);
        Page<${entity}> pageList = this.baseMapper.selectPage(pageContext, ${lowerEntityName});
        return LayPage.buildPagination(pageList);
    }

    /**
    * 新增数据
    * @param ${lowerEntityName}
    */
    @Override
    public int add${entity}(${entity} ${lowerEntityName}) {
        return baseMapper.insert(${lowerEntityName});
    }

    /**
    * 修改数据
    * @param ${lowerEntityName}
    */
    @Override
    public int edit${entity}(${entity} ${lowerEntityName}) {
        return baseMapper.updateById(${lowerEntityName});
    }

    /**
    * 查看详情
    * @param ${keyPropertyName}
    * @return
    */
    @Override
    public ${entity} getDetailsById(${keyPropertyType} ${keyPropertyName}) {
        return baseMapper.selectById(${keyPropertyName});
    }

    /**
    * 删除数据
    * @param ${keyPropertyName}
    * @return
    */
    @Override
    public int deleteById(${keyPropertyType} ${keyPropertyName}) {
        return baseMapper.deleteById(${keyPropertyName});
    }

    /**
    * 批量删除
    * @param ids
    * @return
    */
    @Override
    public int deleteBatch(List<Long> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

}

