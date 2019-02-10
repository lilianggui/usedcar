package com.llg.usedcar.entity.vo;

import javax.validation.constraints.Min;

/**
 * @author Lange
 * @date 2018-12-10 10:16
 */
public class PageQO {
    private static String ORDER_BY = "create_time";
    private static String ORDER = "desc";

    @Min(value = 1, message = "页码不能小于1")
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    public int getOffset() {
        return (this.pageNum - 1) * this.pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if(pageNum != null){
            this.pageNum = pageNum;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize != null){
            this.pageSize = pageSize;
        }
    }
}
