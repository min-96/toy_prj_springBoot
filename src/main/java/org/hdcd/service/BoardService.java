package org.hdcd.service;

import org.hdcd.domain.Board;

import java.util.List;

public interface BoardService {
    public void register(Board board)throws Exception;

    public List<Board> list();

    public Board read(Long boardNo);

    public void edit(Board board);

    public void remove(Long boardNo);
}
