package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Board;
import org.hdcd.repository.BoardRepository;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public void register(Board board) {
        boardRepository.save(board);

    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Board> list() {
//        return boardRepository.findAll(Sort.by(Sort.Direction.DESC,"boardNo"));
//    }

    @Override
    @Transactional(readOnly = true)
    public Board read(Long boardNo) {
        return boardRepository.getOne(boardNo);
    }

    @Override
    @Transactional
    public void edit(Board board) {
            Board boardEntity = boardRepository.getOne(board.getBoardNo());

            boardEntity.setTitle(board.getTitle());
            boardEntity.setContent(board.getContent());

            boardRepository.save(boardEntity);
    }

    @Override
    @Transactional
    public void remove(Long boardNo) {
        boardRepository.deleteById(boardNo);

    }

    @Override
    public Page<Board> list(PageRequestVO pageRequestVO) {
        int pageNumber = pageRequestVO.getPage()-1;
        int sizPerPage = pageRequestVO.getSizePerPage();
        String searchType = pageRequestVO.getSearchType();
        String keyword =pageRequestVO.getKeyword();

        Pageable pageRequest = PageRequest.of(pageNumber,sizPerPage,Sort.Direction.DESC,"boardNo");
   //     Page<Board> page = boardRepository.findAll(pageRequest);
        return boardRepository.getSearchPage(searchType,keyword,pageRequest);
    }
}
