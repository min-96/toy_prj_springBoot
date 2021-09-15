package org.hdcd.service;

import org.hdcd.domain.Board;
import org.hdcd.exception.BoardRecordNotFoundException;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;

public interface BoardService {

	public void register(Board board) throws Exception;
	
	//목록화면
//	public List<Board> list(PageRequestVO pageRequestVO) throws Exception;


	//public Board read(Integer boardNo) throws Exception;

	public void remove(Long boardNo) throws Exception;

	public void modify(Board board) throws Exception;

	public Board read(Long boardNo) throws Exception, BoardRecordNotFoundException;

	public Page<Board> Plist(PageRequestVO pageRequestVO);

}
