package com.hy.cxp.supermarket.util;

public class Page {
	// 总页数
	private int totalPageCount = 1;

	// 页面大小，即每页显示记录数
	private int pageSize = 5;

	// 记录总数
	private int totalCount = 0;

	// 当前页码
	private int currPageNo = 1;

	/**
	 * 设置和获得总页数
	 * 
	 * @return
	 */
	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	/**
	 * 设置和获得页面大小，即每页显示记录数
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获得总个数
	 * 
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总个数 
	 * 通过总个数算出总页数
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
		}
		// 计算总页数
		this.totalPageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize)
				: (totalCount / pageSize + 1);

	}

	/**
	 * 获得当前页
	 * 
	 * @return
	 */
	public int getCurrPageNo() {
		return currPageNo;
	}

	/**
	 * 设置当前页
	 * 
	 * @param currPageNo
	 */
	public void setCurrPageNo(int currPageNo) {
		if (currPageNo > 0) {
			this.currPageNo = currPageNo;
		}

	}

	/**
	 * 带参数的构造方法
	 * 
	 * @param pageSize
	 * @param totalCount
	 * @param currPageNo
	 */

	public Page(int pageSize, int totalCount, int currPageNo) {
		super();
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currPageNo = currPageNo;
	}

	/**
	 * 
	 * 无参构造函数
	 */
	public Page() {
		super();
	}

}
