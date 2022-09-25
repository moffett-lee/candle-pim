package com.fast.coding.extension.pagination;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fast.coding.common.utils.RequestContextUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页参数工厂类
 *
 * @author Bamboo
 * @since 2020-03-19
 */
public class PaginationFactory {

    /**
     * 获取LayUI默认传递的分页参数
     * @see Pagination
     *
     * @return ${Pagination}
     */
    public static Pagination getPagination() {
        return PaginationFactory.getPagination("page","limit");
    }


    /**
     * 自定义获取分页参数
     * @see Pagination
     *
     * @param currPageName  当前页的参数name
     * @param pageSizeName  每页记录数的参数name
     * @return ${Pagination}
     */
    public static Pagination getPagination(String currPageName, String pageSizeName) {
        return new Pagination(getCurrPageParameter(currPageName), getPageSizeParameter(pageSizeName));
    }


    /**
     * 获取LayUI默认传递的分页参数
     * @see Page
     *
     * @return ${Page}
     */
    public static Page getPage() {
        return PaginationFactory.getPage("page","limit");
    }

    /**
     * 自定义获取分页参数
     * @see Page
     *
     * @param currPageName 当前页的参数name
     * @param pageSizeName 每页记录数的参数name
     * @return ${Page}
     */
    public static Page getPage(String currPageName, String pageSizeName) {
        return new Page(getCurrPageParameter(currPageName), getPageSizeParameter(pageSizeName));
    }

    /**
     * 获取当前页
     *
     * @param currPageName 当前页的参数name
     * @return ${Integer}
     */
    private static Integer getCurrPageParameter(String currPageName) {
        HttpServletRequest request = RequestContextUtil.getRequest();
        int currPage = 1;
        String pageParam = request.getParameter(currPageName);
        if (StringUtils.isNotEmpty(pageParam)) {
            currPage = Integer.parseInt(pageParam);
        }
        return currPage;
    }

    /**
     * 获取分页下标
     *
     * @param page 分页对象
     * @return limit开始以及结束索引
     */
    public static Pagination getPaginationIndex(Pagination page) {
        // 开始下标
        int pageSize = page.getPageSize();
        int beginIndex = (page.getPageNum() - 1) * pageSize;
        // 结束下标
        int endIndex = beginIndex + pageSize;
        // 设置下标值
        page.setBeginIndex(beginIndex);
        page.setEndIndex(endIndex);
        return page;
    }

    /**
     * 计算分页总页数
     *
     * @param count 总记录数
     * @param size 每页记录数
     * @return 总页数
     */
    public Long getPages(Long count, Long size) {
        if (count == 0 || size == 0) {
            return 0L;
        }
        Long pages = count / size;
        if (count % size != 0) {
            pages++;
        }
        return pages;
    }

    /**
     * 获取每页记录数
     *
     * @param pageSizeName 每页记录数的参数name
     * @return ${Integer}
     */
    private static Integer getPageSizeParameter(String pageSizeName) {
        HttpServletRequest request = RequestContextUtil.getRequest();
        int pageSize = 10;
        String limitParam = request.getParameter(pageSizeName);
        if (StringUtils.isNotEmpty(limitParam)) {
            pageSize = Integer.parseInt(limitParam);
        }
        return pageSize;
    }

}
