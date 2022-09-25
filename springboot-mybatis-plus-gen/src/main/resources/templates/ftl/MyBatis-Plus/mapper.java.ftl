package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    /**
    * 分页查询
    * @param pageContext
    * @param ${lowerEntityName}
    * @return
    */
    Page<${entity}> selectPage(@Param("page")Page pageContext, @Param("${lowerEntityName}")${entity} ${lowerEntityName});

}

