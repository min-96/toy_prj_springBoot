package org.hdcd.dto;

import groovy.util.logging.Slf4j;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;


//DB의 데이터를 Service나 Controller 등으로 보낼 때 사용하는 객체를 말한다.
//즉, DB의 데이터가 Presentation Logic Tier로 넘어올때는 DTO로 변환되어 오고가는 것이다.
//로직을 갖고 있지 않는 순수한 데이터 객체이며, getter/setter 메서드만을 갖는다.
//또한 Controller Layer에서 Response DTO 형태로 Client에 전달한다.


@Slf4j
@Getter
public class PaginationDTO<T> {
	private Page<T> page;
	private Pageable prevPageable;
	private Pageable nextPageable;
	private Pageable currentPageable;

	private int totalPageCount;
	private int currentPageNumber;

	private List<Pageable> pageableList;
	

	public PaginationDTO(Page<T> page) {
		this.page = page;
		
		//페이지를요청하는데 사용된 Pageable을 반환한다.
		this.currentPageable=page.getPageable();
		
//		getPageNumber() 반환할 페이지를 반환합니다.
		this.currentPageNumber=this.currentPageable.getPageNumber()+1;
		//1
		//총 페이지를 반환
		this.totalPageCount=page.getTotalPages();
		//2
		this.pageableList=new ArrayList<>();
		
		calcPages();
	}


	private void calcPages() {
		//올림정수
		int endPageNumber=(int)(Math.ceil(this.currentPageNumber/10.0)*10);
		//10
		int startPageNumber = endPageNumber-9;
		//1
		Pageable startPageable=this.currentPageable;
		for(int i=startPageNumber; i<this.currentPageNumber;i++) {
			
			//previousOrFirst() ->현재페이지가 이미 첫번째인 경우 이전페이지 지정 또는 Pageable반환
			startPageable=startPageable.previousOrFirst();
		}
		this.prevPageable= startPageable.getPageNumber()<=0?null:startPageable.previousOrFirst();
			
		if(this.totalPageCount<endPageNumber) {
			endPageNumber= this.totalPageCount;
			//2
			this.nextPageable=null;
		}
		
		for(int i=startPageNumber; i<=endPageNumber;i++) {
			pageableList.add(startPageable);
			startPageable=startPageable.next();
		}
		this.nextPageable=startPageable.getPageNumber()+1<
				totalPageCount?startPageable:null;
	}

//		public String makeQuery(int page) {
//			
//			//Path나 query에 해당하는 문자열들을 추가해서 원하는 URI를 생성할 때 사용한다.
//
//			UriComponents uriComponents= UriComponentsBuilder.newInstance()
//					.queryParam("page",page)
//					.build();
//			return uriComponents.toUriString();
//		}
}
