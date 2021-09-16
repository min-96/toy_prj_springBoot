package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;
}
