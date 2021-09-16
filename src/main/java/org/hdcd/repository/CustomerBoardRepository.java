package org.hdcd.repository;

import org.hdcd.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//사용자 인터페이스
public interface CustomerBoardRepository {
	
//	public List<Board> listAll();
	//public List<Board>  SearchByTitle(String title);
	//public List<Board> SearchByWriter(String writer);
	//public List<Board> SearchByContent(String content);
//	public List<Board> SearchByTitleOrContent(String title,String content);
//	public List<Board> searchByTitleOrWriterOrContnet(String title,String writer,String content);
	
	public Page<Board> getSearchPage(String searchType,String keyword,Pageable pageable);
}

