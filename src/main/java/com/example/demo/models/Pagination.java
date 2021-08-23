package com.example.demo.models;

public class Pagination {
public int limit;
public int page;
public int totalPage;
public Pagination(int limit, int page, int totalPage) {
	super();
	this.limit = limit;
	this.page = page;
	this.totalPage = totalPage;
}
public int getLimit() {
	return limit;
}
public void setLimit(int limit) {
	this.limit = limit;
}
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public int getTotalPage() {
	return totalPage;
}
public void setTotalPage(int totalPage) {
	this.totalPage = totalPage;
}
@Override
public String toString() {
	return "Pagination [limit=" + limit + ", page=" + page + ", totalPage=" + totalPage + "]";
}

}
