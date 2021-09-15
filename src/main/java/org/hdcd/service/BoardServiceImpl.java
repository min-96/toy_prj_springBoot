package org.hdcd.service;


//import org.hdcd.dao.BoardDao;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Board;
import org.hdcd.exception.BoardRecordNotFoundException;
import org.hdcd.repository.BoardRepository;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	//@Autowired
	//private BoardDao dao;

	//private final BoardDao dao;
	
	private final BoardRepository repository;
	
	@Override
	@Transactional
	public void register(Board board) throws Exception {
//		repository.save(board);
		repository.save(board);
	}

//	@Override
//	@Transactional(readOnly=true) // 읽기전용
//	public List<Board> list(PageRequestVO pageRequestVO) throws Exception {
//		//return repository.findAll(Sort.by(Direction.DESC, "boardNo"));
//		//검색
//				String searchType=pageRequestVO.getSearchType();
//				String keyword = pageRequestVO.getKeyword();
//				
//				List<Board> list = null;
//				if(searchType!=null &&searchType.length()>0) {
//					if(searchType.equals("t")) {
//					//JPQL//list=repository.findByTitleContainingOrderByBoardNoDesc(keyword);
//				//Querydsl
//						list=SearchByTitle(keyword);
//					}
//					else if(searchType.equals("c")) {
//				//	list=repository.findByContentContainingOrderByBoardNoDesc(keyword);			
//						list=SearchByContent(keyword);
//					}
//					else if(searchType.equals("w")) {
//						//list=repository.findByWriterContainingOrderByBoardNoDesc(keyword);
//						list=SearchByWriter(keyword);
//					}
//					else if(searchType.equals("tc")) {
//						//list= repository.findByTitleContainingOrContentContainingOrderByBoardNoDesc(keyword, keyword);
//						list =SearchByTitleOrContent(keyword, keyword);
//					}
//					else if(searchType.equals("twc")) {
//						//list=repository.findByAllContainingOrderByDesc(keyword, keyword, keyword);
//						list=searchByTitleOrWriterOrContnet(keyword, keyword, keyword);
//					}
//					else {
//						//list= repository.listAll();
//						list = listAll();
//					}
//				}
//				else {
//					list=listAll();
//				}
//				return list;
//		//return dao.list();
//	}
//	private List<Board> listAll(){
//		QBoard board = QBoard.board;
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		//0부터 
//		builder.and(board.boardNo.gt(0));
//		List<Board> list = new ArrayList<>();
//		//반복문으로 b라는 이름으로 list에 저장
//		repository.findAll(builder).forEach(b->list.add(b));
//		return list;
//	}
//	
//	private List<Board> SearchByTitle(String title){
//		QBoard board = QBoard.board;
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(board.title.like("%"+title+"%"));
//		List<Board> list = new ArrayList<>();
//		repository.findAll(builder).forEach(b->list.add(b));
//		return list;
//	}
//	private List<Board> SearchByWriter(String writer){
//		QBoard board = QBoard.board;
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(board.writer.like("%"+writer+"%"));
//		List<Board> list = new ArrayList<>();
//		repository.findAll(builder).forEach(b->list.add(b));
//		return list;
//	}
//	
//	private List<Board> SearchByContent(String content){
//		QBoard board = QBoard.board;
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(board.content.like("%"+content+"%"));
//		List<Board> list = new ArrayList<>();
//		repository.findAll(builder).forEach(b->list.add(b));
//		return list;
//	}
//	
//	private List<Board> SearchByTitleOrContent(String title,String content){
//		QBoard board = QBoard.board;
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(board.title.like("%"+title+"%"))
//		.or(board.content.like("%"+content+"%"));
//		List<Board> list = new ArrayList<>();
//		repository.findAll(builder).forEach(b->list.add(b));
//		return list;
//	}
//	
//	private List<Board> searchByTitleOrWriterOrContnet(String title,String writer,String content){
//		QBoard board= QBoard.board;
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(board.title.like("%"+title+"%")).or(board.writer.like("%"+writer+"%"))
//		.or(board.content.like("%"+content+"%"));
//		List<Board> list = new ArrayList<Board>();
//		repository.findAll(builder).forEach(b->list.add(b));	
//		return list;
//		
//	}
	
	@Override
	@Transactional(readOnly=true) // 읽기전용
	public Board read(Long boardNo) throws Exception, BoardRecordNotFoundException {
		// TODO Auto-generated method stub

		Board board=null;
		if(repository.existsById(boardNo)){
			board=repository.getOne(boardNo);
		}
		else{
			throw new BoardRecordNotFoundException("Not Found boardNo="+boardNo);
		}
		return repository.getOne(boardNo);
	}

	@Override
	@Transactional
	public void remove(Long boardNo) {
		repository.deleteById(boardNo);
	}

	@Override
	@Transactional
	public void modify(Board board) {
		Board boardEntity = repository.getOne(board.getBoardNo());
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
		//repository.modify(board);
	}

	@Override
	public Page<Board> Plist(PageRequestVO pageRequestVO) {
		String searchType=pageRequestVO.getSearchType();
		String keyword = pageRequestVO.getKeyword();
		int pageNumber =pageRequestVO.getPage()-1;
		
		int sizePerPage = pageRequestVO.getSizePerPage();
		//정렬방향과 속성이 적용된 pageRequest를 생성한다
		Pageable pageRequest = PageRequest.of(pageNumber,sizePerPage, Direction.DESC,"boardNo");
		//Page<Board> page = repository.findAll(pageRequest);
		
		return repository.getSearchPage(searchType, keyword, pageRequest);
	}

}
