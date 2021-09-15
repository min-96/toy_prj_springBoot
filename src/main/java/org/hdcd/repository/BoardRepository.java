package org.hdcd.repository;

import org.hdcd.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>
//,QuerydslPredicateExecutor<Board>{
	//사용자 인터페이스 
		,CustomerBoardRepository{
	//검색 쿼리 JPA
//	@Query("SELECT b FROM Board b WHERE b.boardNo>0 ORDER BY b.boardNo desc")
//	public List<Board> listAll();
//
//	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.boardNo>0 ORDER BY b.boardNo desc")
//	public List<Board>
//	findByTitleContainingOrderByBoardNoDesc(String title);
//	
//	@Query("SELECT b FROM Board b WHERE b.writer LIKE %?1% AND b.boardNo>0 ORDER BY b.boardNo desc")
//	public List<Board>
//	findByWriterContainingOrderByBoardNoDesc(String writer);
//	
//	@Query("SELECT b FROM Board b WHERE b.content LIKE %?1% AND b.boardNo>0 ORDER BY b.boardNo desc")
//	public List<Board>
//	findByContentContainingOrderByBoardNoDesc(String content);
//	
//	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% OR b.content LIKE %?2% ORDER BY b.boardNo desc")
//	public List<Board>
//	findByTitleContainingOrContentContainingOrderByBoardNoDesc(String title,String content);
//	
//	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% OR b.writer LIKE %?2% OR b.content LIKE %?3% ORDER BY b.boardNo desc")
//	public List<Board>
//	findByAllContainingOrderByDesc(String title, String writer,String content);
//	
}
