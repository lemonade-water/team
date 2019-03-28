package com.sky.team.business.util;

import java.io.Serializable;
import java.util.List;

public class PageHelper<T> implements Serializable {
    private int pageIndex;
    private int page; //当前页数
    private int totalCount;  //总记录数
    private int totalPage;  //总页数
    private int limit;   //每页显示的记录数
    private List<T> list; //每页显示数据记录的集合；
    private String query; //查询条件
    private Integer type;  //查询类型
    private Integer cTecBigType;  //大类
    private Integer cTecSmallType; //小类
    public PageHelper() {
        this.page=1;
        this.limit=8;
    }



    public PageHelper(int page, int totalCount, int totalPage, int limit, List<T> list, String query,Integer cTecBigType,Integer cTecSmallType) {
        this.page = page;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.limit = limit;
        this.list = list;
        this.query=query;
        this.cTecSmallType = cTecSmallType;
        this.cTecBigType=cTecBigType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getcTecBigType() {
        return cTecBigType;
    }

    public void setcTecBigType(Integer cTecBigType) {
        this.cTecBigType = cTecBigType;
    }

    public Integer getcTecSmallType() {
        return cTecSmallType;
    }

    public void setcTecSmallType(Integer cTecSmallType) {
        this.cTecSmallType = cTecSmallType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return (totalCount%limit)==0?(totalCount/limit):(totalCount/limit+1);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageIndex() {
        if(pageIndex==0)
            return (page-1)*limit;
        else
            return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return "PageHelper{" +
                "pageIndex=" + pageIndex +
                ", page=" + page +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", limit=" + limit +
                ", list=" + list +
                ", query='" + query + '\'' +
                '}';
    }
}
