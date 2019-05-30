package com.util.pageinfoutil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;

/**
 * 专门处理二个字段的分页
 *
 * @author zhengliangzhang
 */
public class PageUtil {

    private Integer page;
    private Integer pageSize;

    public PageUtil(Integer page, Integer pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public PageUtil() {
        super();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public static void page(PageUtil pageUtil) {
        if (null != pageUtil && null != pageUtil.getPage() && null != pageUtil.getPageSize()) {
            PageHelper.startPage(pageUtil.getPage(), pageUtil.getPageSize());
        }
    }

    /**
     * mybatisPlus 分页修改参数
     */

    public static Page getMybatisPusPage(PageUtil pageUtil) {
        Page page = new Page();
        if (null != pageUtil && null != pageUtil.getPage() && null != pageUtil.getPageSize()) {
            page.setSize(pageUtil.getPageSize());
            page.setCurrent(pageUtil.getPage());
        }
        return page;
    }

}
