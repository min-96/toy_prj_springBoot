package org.hdcd.service;

import org.hdcd.domain.Notice;

import java.util.List;

public interface NoticeService {
    void register(Notice notice);

    public List<Notice> list();

    public Notice read(Long noticeNo);

    void edit(Notice notice);

    void remove(Long noticeNo);
}
