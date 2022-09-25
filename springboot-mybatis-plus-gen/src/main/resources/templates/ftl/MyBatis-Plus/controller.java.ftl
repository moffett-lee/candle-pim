package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fast.coding.extension.pagination.LayPage;
import com.fast.coding.extension.resp.ApiResult;
import com.fast.coding.common.constant.SystemConst;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


/**
 * <p>
 * ${table.comment!}前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Controller
@RequestMapping("/${lowerEntityName}")
public class ${table.controllerName} {

    private static final String PREFIX = SystemConst.VIEW_PREFIX + "${lowerEntityName}";

    @Autowired
    private ${table.serviceName} ${lowerServiceName};

    /**
    * 跳转到主页面
    */
    @GetMapping
    public String index() {
        return PREFIX + "/${lowerEntityName}";
    }

    /**
    * 跳转到新增页面
    */
    @GetMapping("/add")
    public String add() {
        return PREFIX + "/${lowerEntityName}_add";
    }

    /**
    * 跳转到修改页面
    */
    @GetMapping("/edit")
    public String edit() {
        return PREFIX + "/${lowerEntityName}_edit";
    }

    /**
    * 查询列表
    * @param ${lowerEntityName}
    * @param limit
    * @param page
    * @return
    */
    @RequestMapping("/list")
    @ResponseBody
    public LayPage pageList(${entity} ${lowerEntityName}, int limit, int page) {
      return ${lowerServiceName}.selectPageList(${lowerEntityName},limit,page);
    }

    /**
    * 新增数据
    * @param ${lowerEntityName}
    * @return
    */
    @RequestMapping("/add${entity}")
    @ResponseBody
    public ApiResult add${entity}(${entity} ${lowerEntityName}) {
        ${lowerServiceName}.add${entity}(${lowerEntityName});
        return ApiResult.success();
    }

    /**
    * 修改数据
    * @param ${lowerEntityName}
    * @return
    */
    @RequestMapping("/edit${entity}")
    @ResponseBody
    public ApiResult edit${entity}(${entity} ${lowerEntityName}) {
        ${lowerServiceName}.edit${entity}(${lowerEntityName});
        return ApiResult.success();
    }

    /**
    * 查看详情
    * @param ${keyPropertyName}
    * @return
    */
    @RequestMapping("/detail")
    @ResponseBody
    public ApiResult detail(${keyPropertyType} ${keyPropertyName}) {
        ${entity} ${lowerEntityName} = ${lowerServiceName}.getDetailsById(${keyPropertyName});
        return ApiResult.success(${lowerEntityName});
    }

    /**
    * 根据ID删除记录
    * @param ${keyPropertyName}
    * @return
    */
    @RequestMapping("/delete")
    @ResponseBody
    public ApiResult delete(${keyPropertyType} ${keyPropertyName}) {
        ${lowerServiceName}.deleteById(${keyPropertyName});
        return ApiResult.success();
    }

    /**
    * 批量删除数据
    * @param ids ID集合
    * @return
    */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public ApiResult deleteBatch(@RequestParam(value = "ids[]",required = true) List<Long> ids) {
        ${lowerServiceName}.deleteBatch(ids);
        return ApiResult.success();
    }

}
