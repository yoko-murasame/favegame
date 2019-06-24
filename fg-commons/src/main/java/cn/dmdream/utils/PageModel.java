package cn.dmdream.utils;

import java.io.Serializable;
import java.util.List;

/*
 * 页面模型,用于封装页面的数据
 * 包含了数据对象
 * 包含了页面的基础信息 比如说 页面大小 当前页面 总页面 总记录条数
 */
public class PageModel<E> implements Serializable {

    //结果
    private List<E> list;
    //记录总条数
    private int totalCount;
    //当前页
    private int currPage;
    //页面大小
    private int pageSize;
    //总页数
    private int totalPage;
    public List<E> getList() {
        return list;
    }
    public void setList(List<E> list) {
        this.list = list;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getCurrPage() {
        if (currPage < 1)
            currPage = 1;
        if (currPage > totalPage)
            currPage = totalPage;
        return currPage;
    }
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    public int getPageSize() {
        if (pageSize < 1)
            pageSize = 1;
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 自动包装计算PageModel
     * @param currPage 设定默认的page首页/开始页信息
     * @param pageSize 设定默认的pageSize信息
     * @param totalCount 设定总记录数
     * @param list 数据集
     * @param pageModel 要包装的PageModel
     */
    public static void wrapPageModel(int currPage , int pageSize,int totalCount,List list,PageModel<?> pageModel){
        //计算总页数
        int totalPage = totalCount%pageSize > 0 ? totalCount/pageSize + 1: totalCount/pageSize;
        //封装分页参数
        pageModel.setCurrPage(currPage);
        pageModel.setPageSize(pageSize);
        pageModel.setTotalCount(totalCount);
        pageModel.setTotalPage(totalPage);
        pageModel.setList(list);
    }
    @Override
    public String toString() {
        return "PageModel [list=" + list + ", totalCount=" + totalCount + ", currPage=" + currPage + ", pageSize="
                + pageSize + ", totalPage=" + totalPage + "]";
    }

}