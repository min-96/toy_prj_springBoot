package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Board;
import org.hdcd.repository.BoardRepository;
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

    @Override
    @Transactional(readOnly = true)
    public List<Board> list() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC,"boardNo"));
    }

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
}
