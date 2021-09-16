package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Notice;
import org.hdcd.repository.NoticeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    @Override
    public void register(Notice notice) {
        noticeRepository.save(notice);
    }

    @Override
    public List<Notice> list() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC,"noticeNo"));
    }

    @Override
    public Notice read(Long noticeNo) {
      return noticeRepository.getOne(noticeNo);
    }

    @Override
    public void edit(Notice notice) {
        Notice noticeEntity = noticeRepository.getOne(notice.getNoticeNo());

        noticeEntity.setTitle(notice.getTitle());
        noticeEntity.setContent(notice.getContent());

        noticeRepository.save(noticeEntity);

    }

    @Override
    public void remove(Long noticeNo) {
        noticeRepository.deleteById(noticeNo);
    }
}
