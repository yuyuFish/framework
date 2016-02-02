package org.framework.authorize.base.utils;

import java.io.Serializable;
import java.util.List;
/**
 * 分页工具类
 * @author ztgoto
 * @version  
 * @date  2016年2月2日 下午2:59:45
 * @param <T>
 */
public class Pager<T> implements Serializable
{
	private long curPage = 0;   //当前页
	private long totalCount = 0;  //总数据条数
	private long pageSize = 10; //每页显示行数
	private long totalPage = 0;  //总页数
	private long startIndex;	//开始数据索引 0 开始
	private List<T> data;  //数据集
	private boolean hasPre; //是否还有前一页
	private boolean hasNext; //是否还有后一页
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public boolean isHasPre() {
		return hasPre;
	}
	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	public long getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}
	
	
	
	@Override
	public String toString() {
		return "Pager [curPage=" + curPage + ", totalCount=" + totalCount
				+ ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", startIndex=" + startIndex + ", data=" + data + ", hasPre="
				+ hasPre + ", hasNext=" + hasNext + "]";
	}
	/**
	 * 初始化数据
	 * @param page
	 */
	public void init(){
		if(this.curPage<=0){
			this.curPage=1;
		}
		this.totalCount=0;
		if(this.pageSize<=0){
			this.pageSize=10;
		}
		this.startIndex=this.curPage<=1?0:((this.curPage-1)*this.pageSize);
		this.totalPage=0;
		this.data=null;
		this.hasPre=false;
		this.hasNext=false;
	}
	
	/**
	 * 设置数据后更新相关状态信息
	 */
	public void update(){
		this.totalPage=this.totalCount<=0?0:((this.totalCount-1)/this.pageSize+1);
		
		if(this.totalPage<=0){
			this.curPage=0;
		}else if(this.totalPage>0&&this.curPage>this.totalPage){
			this.curPage=this.totalPage;
		}
		this.startIndex=this.curPage<=1?0:((this.curPage-1)*this.pageSize);
		
		this.hasPre=totalPage>1&&this.curPage>1;
		this.hasNext=totalPage>1&&this.curPage<totalPage;
	}
	
}
