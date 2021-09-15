package org.hdcd.vo;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageRequestVO {
	
	private int page;
	private int sizePerPage;
	//검색유형과 검색어를 필드로 선언
	private String searchType;
	private String keyword;

	
	PageRequestVO() {
		this.page=1;
		this.sizePerPage=10;
	}
	
	public void setPage(int page) {
		if(page<=0) {
			this.page=1;
			return;
		}
		this.page=page;
	}
	
	public void setSizePerPage(int size) {
		if(size<=0 ||size>100) {
			this.sizePerPage=10;
			return;
		}
		this.sizePerPage=size;
	}
	
	
	public int getPage() {
		
		return page;
	}

	public int getSizePerPage() {
		return this.sizePerPage;
	}

	
	public int getPageStart() {
		return(this.page-1)*sizePerPage;
	}
	
	public String toUriStringByPage(int page) {
		//Path나 query에 해당하는 문자열들을 추가해서 원하는 URI를 생성할 때 사용한다.
		UriComponents uriComponents= UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("size", this.sizePerPage)
				.queryParam("searchType", this.searchType)
				.queryParam("keyword", this.keyword)
				.build();
		
		return uriComponents.toUriString();
		
	}
	
	
	public String toUriString() {
		return toUriStringByPage(this.page);
	}
	
	
	public String toUringStringForSearchAction(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.build();
		return uriComponents.toUriString();
		
	}

	public String getSearchType() {
		return searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	

	
}
