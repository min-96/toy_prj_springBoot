package org.hdcd.repository;

import com.querydsl.jpa.JPQLQuery;
import org.hdcd.domain.Board;
import org.hdcd.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

//사용자 인터페이스
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements CustomerBoardRepository {

	public BoardRepositoryImpl() {
		super(Board.class);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public List<Board> listAll() {
//		QBoard board =QBoard.board;
//		JPQLQuery<Board> query = from(board);
//		query.where(board.boardNo.gt(0L));
//		query.orderBy(board.boardNo.desc());
//		return query.fetch();
//	}

//	@Override
//	public List<Board> SearchByTitle(String title) {
//		QBoard board= QBoard.board;
//		JPQLQuery<Board> query = from(board);
//		query.where(board.title.like("%"+title+"%"));
//		query.orderBy(board.boardNo.desc());
//		return query.fetch();
//	}
//
//	@Override
//	public List<Board> SearchByWriter(String writer) {
//		QBoard board= QBoard.board;
//		JPQLQuery<Board> query = from(board);
//		query.where(board.writer.like("%"+writer+"%"));
//		query.orderBy(board.boardNo.desc());
//		return query.fetch();
//	}
//
//	@Override
//	public List<Board> SearchByContent(String content) {
//		QBoard board= QBoard.board;
//		JPQLQuery<Board> query = from(board);
//		query.where(board.content.like("%"+content+"%"));
//		query.orderBy(board.boardNo.desc());
//		return query.fetch();
//	}
//
//	@Override
//	public List<Board> SearchByTitleOrContent(String title, String content) {
//		QBoard board= QBoard.board;
//		JPQLQuery<Board> query = from(board);
//		query.where(board.title.like("%"+title+"%").or(board.content.like("%"+content+"%")));
//		query.orderBy(board.boardNo.desc());
//		return query.fetch();
//	}
//
//	@Override
//	public List<Board> searchByTitleOrWriterOrContnet(String title, String writer, String content) {
//		QBoard board= QBoard.board;
//		JPQLQuery<Board> query = from(board);
//		query.where(board.title.like("%"+title+"%").or(board.content.like("%"+content+"%").or(board.writer.like("%"+writer+"%"))));
//		query.orderBy(board.boardNo.desc());
//		return query.fetch();
//	}
//	
	public Page<Board> getSearchPage(String searchType,String keyword,Pageable pageable){
		String title = keyword;
		String writer = keyword;
		String content= keyword;
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		if(searchType!=null &&searchType.length()>0) {
			if(searchType.equals("t")) {
				query.where(board.title.like("%"+title+"%"));
				query.orderBy(board.boardNo.desc());
			}
			else if(searchType.equals("c")) {
				query.where(board.content.like("%"+content+"%"));
				query.orderBy(board.boardNo.desc());
		
			}
			else if(searchType.equals("w")) {
				query.where(board.writer.like("%"+writer+"%"));
				query.orderBy(board.boardNo.desc());
			}
			else if(searchType.equals("tc")) {
				query.where(board.title.like("%"+title+"%").or(board.content.like("%"+content+"%")));
				query.orderBy(board.boardNo.desc());			}
			else if(searchType.equals("twc")) {
				query.where(board.title.like("%"+title+"%").or(board.content.like("%"+content+"%").or(board.writer.like("%"+writer+"%"))));
				query.orderBy(board.boardNo.desc());
			}
			else {
				query.where(board.boardNo.gt(0L));
				query.orderBy(board.boardNo.desc());
			}
		}
		else {
			query.where(board.boardNo.gt(0L));
			query.orderBy(board.boardNo.desc());
		}
		query.offset(pageable.getOffset());
		query.limit(pageable.getPageSize());
		
		List<Board> resultList=query.fetch();
		
		long total = query.fetchCount();
		
		
		return new PageImpl<>(resultList,pageable,total);
		
	}

}
